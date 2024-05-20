package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.service.system.converter.SysUserToSecurityUserConverter;
import com.gstdev.cloud.service.system.feign.service.IdentityFeignService;
import com.gstdev.cloud.service.system.feign.vo.IdentitySaveDto;
import com.gstdev.cloud.service.system.mapper.UserMapper;
import com.gstdev.cloud.service.system.pojo.base.account.AccountInsertInput;
import com.gstdev.cloud.service.system.pojo.base.user.UserDto;
import com.gstdev.cloud.service.system.pojo.base.user.UserFindAllByQueryCriteria;
import com.gstdev.cloud.service.system.pojo.base.user.UserPageQueryCriteria;
import com.gstdev.cloud.service.system.pojo.entity.SysAttribute;
import com.gstdev.cloud.service.system.pojo.entity.SysUser;
import com.gstdev.cloud.service.system.pojo.vo.user.UserInsertInput;
import com.gstdev.cloud.service.system.pojo.vo.user.UserUpdateInput;
import com.gstdev.cloud.service.system.pojo.vo.user.AccountListDto;
import com.gstdev.cloud.service.system.repository.SysPermissionRepository;
import com.gstdev.cloud.service.system.repository.UserRepository;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.service.BasePOJOServiceImpl;
import com.gstdev.cloud.oauth2.core.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import jakarta.annotation.Resource;

import java.util.*;

@Slf4j
@Transactional(readOnly = true)
public class UserServiceImpl extends BasePOJOServiceImpl<SysUser, String, UserRepository, UserMapper, UserDto, UserInsertInput, UserUpdateInput, UserPageQueryCriteria, UserFindAllByQueryCriteria> implements UserService {

    private static final String SPECIAL_CHARS = "! @#$%^&＊_=+-/";
    @Resource
    private AccountService accountService;

    @Resource
    private IdentityFeignService identityFeignService;
    @Resource
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        super(userRepository, userMapper);
    }

    public UserRepository getRepository() {
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
    public Result<UserDto> insertToResult(UserInsertInput varInsertUpdate) {
        return super.insertToResult(varInsertUpdate);
    }

    @Override
    @Transactional
    public SysUser insert(SysUser user) {
        if (ObjectUtils.isEmpty(user.getPassword())) {
            user.setPassword(randomPassword());
        }
        user.setPassword((SecurityUtils.encrypt(user.getPassword())));
        return super.insert(user);
    }

    @Transactional
    public SysUser insertUserInitialization(UserInsertInput userInsertInput) {
        SysUser user = toEntityInsert(userInsertInput);
        SysUser insert = insert(user);
        AccountInsertInput accountInsertInput = new AccountInsertInput();
        accountInsertInput.setTenantId(userInsertInput.getTenantId());
        accountInsertInput.setUserId(insert.getId());
        accountInsertInput.setAccountTypeConstants(userInsertInput.getAccountTypeConstants());
        accountService.insertAccountInitialization(accountInsertInput);
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
    public Result<UserDto> insertUserInitializationToResult(UserInsertInput userInsertInput) {
        SysUser user = insertUserInitialization(userInsertInput);
        return Result.success(getMapper().toDto(user));
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

//  @Transactional
//  public Result<UserDto> insertCustomerUser(UserInsertInput userInsertInput) {
//    //TODO 新增一个当前账户的角色
//    Role role=new Role();
//    role.setParentId(userInsertInput.getTenantId());
//    role.setTenantId(userInsertInput.getTenantId());
//    role.setCode(UUID.randomUUID().toString());
//    role.setRoleName(userInsertInput.getFirstName());
//    Role save = roleRepository.save(role);
//
//    ArrayList<String> roleIds = new ArrayList();
//    roleIds.add(save.getId());
//    userInsertInput.setRoleIds(roleIds);
//    User user = insertUserInitialization(userInsertInput);
//    //TODO 发送当前用户的邀请邮件
//    this.create(getMapper().toDto(user),userInsertInput.getTenantId());
//    return Result.success(getMapper().toDto(user));
//  }

//  @Override
//  @Transactional
//  public Result<UserDto> updateCustomerUser(UserLoginInferiorUpdateInput userLoginInferiorUpdateInput) {
//    Optional<User> byId = userRepository.findById(userLoginInferiorUpdateInput.getId());
//    User user = byId.get();
//    List<Account> account1 = user.getAccount();
//    account1.forEach(account -> {
//      account.setType(userLoginInferiorUpdateInput.getAccountTypeConstants().getCode());
//    });
//    user.setAccount(account1);
//    userMapper.copyModify(userLoginInferiorUpdateInput,user);
//    User update = update(user);
//    return Result.success(getMapper().toDto(update));
//  }
//
//  @Override
//  public List<UserDto> findAllByQueryCriteria(UserFindAllByQueryCriteria queryCriteria,String userId) {
//    List<UserDto> allByQueryCriteriaToDto = findAllByQueryCriteriaToDto(new UserFindAllByQueryCriteria());
//
//    return  allByQueryCriteriaToDto;
//  }
//


//
//  @Override
//  public String checkIfUserExist(String emailAddress){
//    String validationMessage = null;
//    Optional<User> user = userRepository.findByEmailAndDeletedFalse(emailAddress);
//    if(user.isPresent() && !ObjectUtils.isEmpty(user)){
//      validationMessage = "The email address already exists";
//    }
//    return validationMessage;
//  }
//
//  public List<Role> getRoleEntity(List<String> list) {
//    List<Role> roleList = new ArrayList<Role>();
//    if (list != null && list.size() > 0) {
//      for (String item : list) {
//        Role role = new Role();
//        role.setId(item);
//        roleList.add(role);
//      }
//    }
//    return roleList;
//  }
//

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
