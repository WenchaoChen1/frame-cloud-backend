package com.frame.template.service.system.service;

import com.frame.template.service.system.feign.service.IdentityFeignService;
import com.frame.template.service.system.feign.vo.IdentitySaveDto;
import com.frame.template.service.system.mapper.UserMapper;
import com.frame.template.service.system.message.ServiceErrorMessage;
import com.frame.template.service.system.pojo.base.account.AccountInsertInput;
import com.frame.template.service.system.pojo.base.user.UserDto;
import com.frame.template.service.system.pojo.base.user.UserFindAllByQueryCriteria;
import com.frame.template.service.system.pojo.base.user.UserPageQueryCriteria;
import com.frame.template.service.system.pojo.domain.Account;
import com.frame.template.service.system.pojo.domain.User;
import com.frame.template.service.system.pojo.domain.UserStatus;
import com.frame.template.service.system.pojo.vo.UserInsertInput;
import com.frame.template.service.system.pojo.vo.UserUpdateInput;
import com.frame.template.service.system.pojo.vo.user.AccountListDto;
import com.frame.template.service.system.repository.RoleRepository;
import com.frame.template.service.system.repository.UserRepository;
import com.frame.template.service.system.util.TokenUtils;
import com.gstdev.cloud.commons.exception.BadRequestException;
import com.gstdev.cloud.commons.ass.definition.domain.Result;
import com.frame.template.common.base.BaseServiceImpl;
import com.frame.template.common.constant.ServiceConstants;
import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformation;
import com.frame.template.common.utils.CryptoUtils;
import com.frame.template.service.system.pojo.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import jakarta.annotation.Resource;
import java.util.*;

@Slf4j
@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends BaseServiceImpl<UserRepository, UserMapper, User, UserDto, UserInsertInput, UserUpdateInput, UserPageQueryCriteria, UserFindAllByQueryCriteria, RedisCurrentLoginInformation> implements UserService {

  @Resource
  private UserRepository userRepository;
  @Resource
  private UserMapper userMapper;
  @Resource
  private RedisCurrentLoginInformation redisCurrentLoginInformation;

  @Resource
  private EmailService emailService;

  @Resource
  private AccountService accountService;

  @Resource
  private IdentityFeignService identityFeignService;
//
//  @Resource
//  private TenantFeignService tenantFeignService;
  @Resource
  private RoleRepository roleRepository;

//  @Resource
//  private EmailFeignService emailFeignService;

  @Value(value = "${spring.mail.email}")
  private String senderEmil;

  public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RedisCurrentLoginInformation redisCurrentLoginInformation) {
    super(userRepository, userMapper, redisCurrentLoginInformation);
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.redisCurrentLoginInformation = redisCurrentLoginInformation;
  }

  @Override
  public Result<UserDto> insertToResult(UserInsertInput varInsertUpdate) {
    return super.insertToResult(varInsertUpdate);
  }

  @Override
  @Transactional
  public User insert(User user) {
    user.setPassword(Base64.getEncoder().encodeToString(CryptoUtils.asymEncrypt(user.getPassword(), ServiceConstants.ASYM_PUBLIC_KEY)));
    User insert = super.insert(user);
    return insert;
  }

  @Transactional
  public User insertUserInitialization(UserInsertInput userInsertInput) {
    User user = toEntityInsert(userInsertInput);
    User insert = insert(user);
    AccountInsertInput accountInsertInput = new AccountInsertInput();
    accountInsertInput.setTenantId(userInsertInput.getTenantId());
    accountInsertInput.setUserId(insert.getId());
    accountInsertInput.setAccountTypeConstants(userInsertInput.getAccountTypeConstants());
    Account insertAccount = accountService.insertAccountInitialization(accountInsertInput);
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
    User user = insertUserInitialization(userInsertInput);
    return Result.success(getMapper().toDto(user));
  }

  @Override
  public List<AccountListDto> getByIdToAccount(String id) {
    User user = userRepository.findById(id).orElseGet(User::new);
    List<Account> account = user.getAccount();
    List<AccountListDto> accountListDtos = userMapper.accountListToDto(account);
    return accountListDtos;
  }

  @Override
  @Transactional()
  public Result<UserDto> deleteById(String id) {
    if (id == null || id.length() == 0) {
      throw new BadRequestException("The primary key cannot be empty");
    }
    if(id.equals(redisCurrentLoginInformation.getCurrentLoginInformation().getUserId())){
      throw new BadRequestException("You cannot delete your own data");
    }
    getRepository().deleteById(id);
    return Result.success(null);
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

  @Override
  @Transactional(rollbackFor = ServiceException.class)
  public UserDto create(UserDto userDto,String tenentId) {

    User user = userMapper.toEntity(userDto);

//    this.checkPassword(user.getPassword());
    if (StringUtils.isEmpty(user.getUsername())) {
      user.setUsername(user.getEmail());
    }
    user.setStatus(UserStatus.INVITED.getValue());
    user.setActivateToken(TokenUtils.getInstance().encode());
    String password = randomPassword();
    user.setPassword(Base64.getEncoder().encodeToString(CryptoUtils.asymEncrypt(password, ServiceConstants.ASYM_PUBLIC_KEY)));
    try {
      user = this.userRepository.save(user);

//      com.gstdev.template.service.system.feign.vo.UserDto feignUserDto = new com.gstdev.template.service.system.feign.vo.UserDto();
//      feignUserDto.setEmail(user.getEmail());
//      feignUserDto.setFirstName(user.getFirstName());
//      feignUserDto.setLastName(user.getLastName());
//      feignUserDto.setPassword(password);
//
//      if(ObjectUtil.isNotEmpty(tenentId)){
//        TenantDto tenantDto = tenantFeignService.findById(tenentId);
//        if(ObjectUtil.isNotEmpty(tenantDto)){
//          feignUserDto.setCustomerName(tenantDto.getTenantName());
//        }
//      }
//
//      emailFeignService.inviteUser(feignUserDto);
//      Email email = new Email();
//      email.setType(2);
//      email.setReceiverEmail(user.getEmail());
//      email.setSenderEmail(senderEmil);
//      email.setSubject("Welcome");
//      email.setBody("Welcome");
//      email.setToken(user.getActivateToken());
//
//      Context context = new Context();
//      context.setVariable("token", user.getActivateToken());
//      context.setVariable("userEmail", user.getEmail());
//      context.setVariable("username", user.getFirstName()+ user.getLastName());
//      context.setVariable("emailAddress", senderEmil);
//      context.setVariable("url", "/user/registered?");
//      emailFeignService.sendEmail(email, context, "Welcome", EmailTypeEnum.Welcome);
    } catch (Exception e) {
      log.info(e.getMessage(), e);
      throw new ServiceException(ServiceErrorMessage.USER_ADD_FAILED.getMessage());
    }

    return userMapper.toDto(user);
  }
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


    private static final String SPECIAL_CHARS = "! @#$%^&＊_=+-/";

    private static char nextChar(Random rnd){
      switch(rnd.nextInt(4)){
        case 0:
          return (char)('a'+rnd.nextInt(26));
        case 1:
          return (char)('A'+rnd.nextInt(26));
        case 2:
          return     (char)('0'+rnd.nextInt(10));
        default:
          return SPECIAL_CHARS.charAt(rnd.nextInt(SPECIAL_CHARS.length()));
      }
    }
    public static String randomPassword(){
      char[] chars = new char[8];
      Random rnd = new Random();
      for(int i=0; i<8; i++){
        chars[i] = nextChar(rnd);
      }
      return new String(chars);
    }

}
