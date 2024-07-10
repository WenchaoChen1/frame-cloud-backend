//package com.frame.template.autoconfigure.service.system.service;
//
//import cn.hutool.json.JSONArray;
//import com.frame.template.common.exception.CommonException;
//import com.frame.template.common.redis.currentLoginInformation.CurrentLoginInformation;
//import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformation;
//import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformationInput;
//import com.gstdev.cloud.base.definition.domain.Result;
//import com.gstdev.cloud.oauth2.core.utils.SecurityUtils;
//import com.gstdev.cloud.service.system.domain.entity.SysAccount;
//import com.gstdev.cloud.service.system.service.SysAccountService;
//import com.gstdev.cloud.service.system.service.SysMenuService;
//import com.gstdev.cloud.service.system.service.SysSecurityService;
//import jakarta.annotation.Resource;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.stereotype.Service;
//import org.springframework.util.ObjectUtils;
//
//import java.util.Collections;
//import java.util.List;
//
//@Service
//public class RedisCurrentLoginInformationServiceImpl implements RedisCurrentLoginInformationService {
//
//
//    @Resource
//    private RedisCurrentLoginInformation redisCurrentLoginInformation;
//
//    @Resource
//    private SysAccountService accountService;
//    @Resource
//    @Lazy
//    private SysMenuService menuService;
//    @Resource
//    @Lazy
//    private SysSecurityService sysSecurityService;
////    @Resource
////    private SysTenantService tenantService;
//
//    @Override
//    public Result<Object> addByTokenCurrentLoginInformation(RedisCurrentLoginInformationInput redisCurrentLoginInformationInput) {
////        SysAccount account = null;
////        if (!ObjectUtils.isEmpty(redisCurrentLoginInformationInput.getAccountId())) {
////            account = accountService.findById(redisCurrentLoginInformationInput.getAccountId());
////        }
////        if (account == null || account.getAccountId() == null) {
////            List<SysAccount> accounts = accountService.findAllByUserId(SecurityUtils.getUserId());
////            if (ObjectUtils.isEmpty(accounts)) {
////                throw new CommonException("No account was found, please log in again");
////            }
////            account = accounts.get(0);
////        }
////        CurrentLoginInformation currentLoginInformation = new CurrentLoginInformation();
////        currentLoginInformation.setUserId(account.getUser().getUserId());
////        currentLoginInformation.setUserName(account.getUser().getUsername());
////        currentLoginInformation.setAccountId(account.getAccountId());
////        currentLoginInformation.setAccountName(account.getName());
////        currentLoginInformation.setTenantId(account.getTenantId());
////        currentLoginInformation.setType(account.getType().getValue());
////        currentLoginInformation.setTenant(new JSONObject(tenantService.findById(account.getTenantId())));
////        currentLoginInformation.setCurrentLoginAccount(new JSONObject(account));
////        currentLoginInformation.setCurrentLoginAccountUserPermissions(new JSONArray(menuService.getAccountMenuPermissions(account.getAccountId())));
//        CurrentLoginInformation currentLoginInformation = new CurrentLoginInformation();
//        com.gstdev.cloud.service.system.domain.pojo.sysSecurity.CurrentLoginInformation accountCurrentLoginInformation = sysSecurityService.getAccountCurrentLoginInformation(currentLoginInformation.getAccountId());
//        currentLoginInformation.setUserId(accountCurrentLoginInformation.getUserId());
//        currentLoginInformation.setUserName(accountCurrentLoginInformation.getUserName());
//        currentLoginInformation.setAccountId(accountCurrentLoginInformation.getAccountId());
//        currentLoginInformation.setAccountName(accountCurrentLoginInformation.getAccountName());
//        currentLoginInformation.setTenantId(accountCurrentLoginInformation.getTenantId());
//        currentLoginInformation.setType(accountCurrentLoginInformation.getType());
//        currentLoginInformation.setTenant(accountCurrentLoginInformation.getTenant());
//        currentLoginInformation.setCurrentLoginAccount(accountCurrentLoginInformation.getCurrentLoginAccount());
//        currentLoginInformation.setCurrentLoginAccountUserPermissions(accountCurrentLoginInformation.getCurrentLoginAccountUserPermissions());
//        currentLoginInformation.setLeftAndTopRoutes(Collections.singletonList(accountCurrentLoginInformation.getLeftAndTopRoutes()));
//        currentLoginInformation.setRightRoutes(Collections.singletonList(accountCurrentLoginInformation.getRightRoutes()));
//        currentLoginInformation.setFunctionPermissionCode(accountCurrentLoginInformation.getFunctionPermissionCode());
//        currentLoginInformation.setPagePathAccessPermission(accountCurrentLoginInformation.getPagePathAccessPermission());
//        currentLoginInformation.setCurrentLoginAccountUserPermissions(new JSONArray(menuService.getAccountMenuPermissions(accountCurrentLoginInformation.getAccountId())));
//        redisCurrentLoginInformation.addByTokenCurrentLoginInformation(currentLoginInformation);
//        return Result.success(currentLoginInformation);
//    }
//
//    @Override
//    public Result<Object> updateByTokenCurrentLoginInformation(RedisCurrentLoginInformationInput redisCurrentLoginInformationInput) {
//        CurrentLoginInformation currentLoginInformation = new CurrentLoginInformation();
//        currentLoginInformation.setAccountId(redisCurrentLoginInformationInput.getAccountId());
//        redisCurrentLoginInformation.updateByTokenCurrentLoginInformation(currentLoginInformation);
//        return Result.success();
//    }
//
//    @Override
//    public Result<Object> deleteByTokenCurrentLoginInformation() {
//        redisCurrentLoginInformation.deleteByTokenCurrentLoginInformation();
//        return Result.success();
//    }
//
//    @Override
//    public Result<CurrentLoginInformation> getCurrentLoginInformation() {
//        CurrentLoginInformation currentLoginInformation = redisCurrentLoginInformation.getCurrentLoginInformation();
//        return Result.success(currentLoginInformation);
//    }
//
//
//}
