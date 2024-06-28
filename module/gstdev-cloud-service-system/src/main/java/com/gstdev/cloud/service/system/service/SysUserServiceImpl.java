package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.oauth2.core.definition.domain.FrameGrantedAuthority;
import com.gstdev.cloud.oauth2.core.utils.SecurityUtils;
import com.gstdev.cloud.service.system.domain.base.user.UserDto;
import com.gstdev.cloud.service.system.domain.converter.SysUserToSecurityUserConverter;
import com.gstdev.cloud.service.system.domain.entity.*;
import com.gstdev.cloud.service.system.domain.enums.SysAccountType;
import com.gstdev.cloud.service.system.domain.pojo.sysAccount.InsertAccountManageInitializationIO;
import com.gstdev.cloud.service.system.domain.pojo.sysUser.InsertUserManageInitializationIO;
import com.gstdev.cloud.service.system.domain.vo.user.AccountListDto;
import com.gstdev.cloud.service.system.feign.service.IdentityFeignService;
import com.gstdev.cloud.service.system.feign.vo.IdentitySaveDto;
import com.gstdev.cloud.service.system.mapper.SysUserMapper;
import com.gstdev.cloud.service.system.repository.SysTenantMenuRepository;
import com.gstdev.cloud.service.system.repository.SysUserRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Transactional(readOnly = true)
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, String, SysUserRepository> implements SysUserService {

    private static final String SPECIAL_CHARS = "! @#$%^&＊_=+-/";
    @Resource
    @Lazy
    private SysAccountService accountService;
    @Resource
    @Lazy
    private SysMenuService menuService;

    @Resource
    @Lazy
    private IdentityFeignService identityFeignService;
    @Resource
    private SysUserRepository userRepository;
    @Resource
    private SysTenantMenuRepository sysTenantMenuRepository;
    //    @Resource
    private SysUserMapper userMapper;

    public SysUserServiceImpl(SysUserRepository userRepository, SysUserMapper userMapper) {
        super(userRepository);
        this.userMapper = userMapper;
    }

    private static char nextChar(Random rnd) {
        switch (rnd.nextInt(4)) {
            case 0:
                return (char) ('a' + rnd.nextInt(26));
            case 1:
                return (char) ('A' + rnd.nextInt(26));
            case 2:
                return (char) ('0' + rnd.nextInt(10));
            default:
                return SPECIAL_CHARS.charAt(rnd.nextInt(SPECIAL_CHARS.length()));
        }
    }

    public static String randomPassword() {
        char[] chars = new char[8];
        Random rnd = new Random();
        for (int i = 0; i < 8; i++) {
            chars[i] = nextChar(rnd);
        }
        return new String(chars);
    }

    @Override
    public SysUserRepository getRepository() {
        return userRepository;
    }

    @Override
    @Transactional
    public SysUser save(SysUser user) {
        if (ObjectUtils.isEmpty(user.getPassword())) {
            user.setPassword(SecurityUtils.encrypt(randomPassword()));
        }
        return super.save(user);
    }

    @Override
    @Transactional
    public SysUser insertUserManageInitialization(InsertUserManageInitializationIO userInsertInput) {
        SysUser user = userMapper.toEntity(userInsertInput);
        SysUser insert = insert(user);
        InsertAccountManageInitializationIO accountInsertInput = new InsertAccountManageInitializationIO();
        accountInsertInput.setTenantId(userInsertInput.getTenantId());
        accountInsertInput.setUserId(insert.getId());
        accountInsertInput.setType(userInsertInput.getType());
        accountService.insertAccountManageInitialization(accountInsertInput);
        // 同步到identity模块
        IdentitySaveDto identitySaveDto = new IdentitySaveDto();
        identitySaveDto.setUserId(insert.getId());
        identitySaveDto.setEmail(insert.getEmail());
        identitySaveDto.setUsername(insert.getUsername());
        identitySaveDto.setPassword(insert.getPassword());
        identityFeignService.save(identitySaveDto);
        return insert;
    }

    @Override
    public void resetPassword(String originalPassword, String newPassword) {
        SysUser user = getRepository().findById(SecurityUtils.getUserId()).orElseGet(SysUser::new);
        if (!SecurityUtils.matches(originalPassword, user.getPassword())) {
            throw new PlatformRuntimeException("The original password is incorrect");
        }
        user.setPassword(SecurityUtils.encrypt(newPassword));
        save(user);
    }

    /**
     * 新增用户并且创建账户角色，关联部门
     *
     * @param userInsertInput
     * @return
     */
    @Transactional
    public UserDto insertUserManageInitializationToDto(InsertUserManageInitializationIO userInsertInput) {
        SysUser sysUser = insertUserManageInitialization(userInsertInput);
        return userMapper.toDto(sysUser);
    }

    @Override
    public DefaultSecurityUser signInFindByUsername(String username) {
        // 根据用户名查找用户
        SysUser user = getRepository().findByUsername(username);
        if (user == null) {
            return null;
        }

        // 初始化权限集合
        Set<FrameGrantedAuthority> authorities = new HashSet<>();

        // 获取用户的账号列表
        List<SysAccount> accounts = user.getAccount().stream()
                .filter(account -> account.getStatus().equals(DataItemStatus.ENABLE)).toList();
        // 添加超级管理员的特殊权限
        accounts.stream()
                .filter(account -> account.getType().equals(SysAccountType.SUPER))
                .forEach(account -> authorities.add(new FrameGrantedAuthority("5ef5ef0364b6939c4ca61f34b393f7b368d1be8619647aaf83d5b395919ab629")));

        // 初始化租户菜单相关集合
        List<SysTenantMenu> allByTenantIdIn = null;
        Map<String, Set<String>> attributeMaps = new HashMap<>();
        Set<String> adminAccountTenantIds = new HashSet<>();

        // 处理用户的租户账号
        accounts.forEach(account -> {
            if (account.getType().equals(SysAccountType.ADMIN)) {
                adminAccountTenantIds.add(account.getTenantId());
            }
        });

        // 根据租户ID获取菜单
        List<SysMenu> menus = new ArrayList<>();
        if (!adminAccountTenantIds.isEmpty()) {
            allByTenantIdIn = sysTenantMenuRepository.findAllByTenantIdIn(adminAccountTenantIds);
            menus = allByTenantIdIn.stream().map(SysTenantMenu::getMenu).toList();
        }
        // 处理用户的user account
        accounts.forEach(account -> {
            if (account.getType().equals(SysAccountType.USER)) {
                adminAccountTenantIds.add(account.getTenantId());
            }
        });


        // 处理菜单根据menuId去重
        menus.stream().collect(Collectors.toMap(SysMenu::getId, menu -> menu, (key1, key2) -> key1));
        // 处理菜单的属性并进行分组
        menus.forEach(sysMenu -> {
            sysMenu.getAttributes().stream()
                    .collect(Collectors.groupingBy(SysAttribute::getServiceId))
                    .forEach((serviceId, attributes) -> {
                        Set<String> sysAttributes = attributeMaps.getOrDefault(serviceId, new HashSet<>());
                        sysAttributes.addAll(attributes.stream().map(SysAttribute::getAttributeId).collect(Collectors.toSet()));
                        attributeMaps.put(serviceId, sysAttributes);
                    });
        });
        // 将分组后的属性ID集合生成权限并添加到权限集合中
        for (Set<String> value : attributeMaps.values()) {
            authorities.add(new FrameGrantedAuthority(generateKey(new ArrayList<>(value))));
        }
        // 将SysUser对象转换为DefaultSecurityUser对象
        SysUserToSecurityUserConverter sysUserToSecurityUserConverter = new SysUserToSecurityUserConverter();
        return sysUserToSecurityUserConverter.convert(user,authorities);
    }

    public static String generateKey(List<String> input) {
        // 对字符串列表进行排序
        Collections.sort(input);
        // 连接排序后的字符串
        String combinedInput = String.join("", input);
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(combinedInput.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }
    //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////

    public List<AccountListDto> getByIdToAccount(String id) {
        SysUser user = getRepository().findById(id).orElseGet(SysUser::new);
        return userMapper.accountListToDto(user.getAccount());
    }

    @Override
    @Transactional()
    public void deleteById(String id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new PlatformRuntimeException("The primary key cannot be empty");
        }
        if (id.equals(SecurityUtils.getUserId())) {
            throw new PlatformRuntimeException("You cannot delete your own data");
        }
        getRepository().deleteById(id);
    }
}
