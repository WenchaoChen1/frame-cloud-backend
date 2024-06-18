package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseDtoServiceImpl;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.service.system.mapper.vo.SysUserMapper;
import com.gstdev.cloud.service.system.domain.converter.SysUserToSecurityUserConverter;
import com.gstdev.cloud.service.system.feign.service.IdentityFeignService;
import com.gstdev.cloud.service.system.feign.vo.IdentitySaveDto;
import com.gstdev.cloud.service.system.domain.base.user.UserDto;
import com.gstdev.cloud.service.system.domain.entity.SysUser;
import com.gstdev.cloud.service.system.domain.pojo.sysAccount.InsertAccountManageInitializationIO;
import com.gstdev.cloud.service.system.domain.pojo.sysUser.InsertUserManageInitializationIO;
import com.gstdev.cloud.service.system.domain.vo.user.AccountListDto;
import com.gstdev.cloud.service.system.repository.SysUserRepository;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;
import com.gstdev.cloud.oauth2.core.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import jakarta.annotation.Resource;

import java.util.*;

@Slf4j
@Transactional(readOnly = true)
public class SysUserServiceImpl extends BaseDtoServiceImpl<SysUser, String, SysUserRepository, SysUserMapper, UserDto> implements SysUserService {

    private static final String SPECIAL_CHARS = "! @#$%^&＊_=+-/";
    @Resource
    private SysAccountService accountService;

    @Resource
    private IdentityFeignService identityFeignService;
    @Resource
    private SysUserRepository userRepository;
    @Resource
    private SysUserMapper userMapper;

    public SysUserServiceImpl(SysUserRepository userRepository, SysUserMapper userMapper) {
        super(userRepository, userMapper);
    }

    @Override
    public SysUserRepository getRepository() {
        return userRepository;
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
    @Transactional
    public SysUser save(SysUser user) {
        if (ObjectUtils.isEmpty(user.getPassword())) {
            user.setPassword(SecurityUtils.encrypt(randomPassword()));
        }
        return super.save(user);
    }
//    @Override
//    @Transactional
//    public SysUser insert(SysUser user) {
//        if (ObjectUtils.isEmpty(user.getPassword())) {
//            user.setPassword(randomPassword());
//        }
//        user.setPassword((SecurityUtils.encrypt(user.getPassword())));
//        return super.insert(user);
//    }

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

    /**
     * 新增用户并且创建账户角色，关联部门
     *
     * @param userInsertInput
     * @return
     */
    @Override
    @Transactional
    public UserDto insertUserManageInitializationToDto(InsertUserManageInitializationIO userInsertInput) {
        SysUser sysUser = insertUserManageInitialization(userInsertInput);
        return getMapper().toDto(sysUser);
    }

    @Override
    public DefaultSecurityUser signInFindByUsername(String username) {
        SysUser byUsername = getRepository().findByUsername(username);
        if (byUsername == null) {
            return null;
        }
        SysUserToSecurityUserConverter sysUserToSecurityUserConverter = new SysUserToSecurityUserConverter();
        return sysUserToSecurityUserConverter.convert(byUsername);
    }

    //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////

    @Override
    public List<AccountListDto> getByIdToAccount(String id) {
        SysUser user = getRepository().findById(id).orElseGet(SysUser::new);
        return getMapper().accountListToDto(user.getAccount());
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
