
package com.gstdev.cloud.service.system.configuration;

import com.gstdev.cloud.service.system.mapper.*;
import com.gstdev.cloud.service.system.repository.*;
import com.gstdev.cloud.service.system.service.*;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <p>Description:  模块配置 </p>
 */
@Configuration(proxyBeanMethods = false)
public class FrameSystemServiceConfiguration {

    private static final Logger log = LoggerFactory.getLogger(FrameSystemServiceConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[GstDev Cloud] |- SDK [Frame System Service Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public TenantService sysTenantService(TenantRepository tenantRepository, TenantMapper tenantMapper) {
        log.debug("[GstDev Cloud] |- Frame Configure Tenant Service");
        return new TenantServiceImpl(tenantRepository, tenantMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public UserService sysUserService(UserRepository userRepository, UserMapper userMappe) {
        log.debug("[GstDev Cloud] |- Frame Configure User Service");
        return new UserServiceImpl(userRepository, userMappe);
    }

    @Bean
    @ConditionalOnMissingBean
    public AccountService sysAccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        log.debug("[GstDev Cloud] |- Frame Configure Account Service");
        return new AccountServiceImpl(accountRepository, accountMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public RoleService sysRoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        log.debug("[GstDev Cloud] |- Frame Configure Role Service");
        return new RoleServiceImpl(roleRepository, roleMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public MenuService sysMenuService(MenuRepository menuRepository, MenuMapper menuMapper) {
        log.debug("[GstDev Cloud] |- Frame Configure Menu Service");
        return new MenuServiceImpl(menuRepository, menuMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public RTenantMenuService sysRTenantMenuService(RTenantMenuRepository rTenantMenuRepository, RTenantMenuMapper rTenantMenuMapper) {
        log.debug("[GstDev Cloud] |- Frame Configure R Tenant Menu Service");
        return new RTenantMenuServiceImpl(rTenantMenuRepository, rTenantMenuMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public SysPermissionService sysPermissionService(SysPermissionRepository sysPermissionRepository, SysPermissionMapper sysPermissionMapper) {
        log.debug("[GstDev Cloud] |- Frame Configure Permission Service");
        return new SysPermissionServiceImpl(sysPermissionRepository, sysPermissionMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public SysInterfaceService sysInterfaceService(SysInterfaceRepository sysInterfaceRepository) {
        log.debug("[GstDev Cloud] |- Frame Configure Interface Service");
        return new SysInterfaceServiceImpl(sysInterfaceRepository);
    }

    @Bean
    @ConditionalOnMissingBean
    public SysAttributeService sysAttributeService(SysAttributeRepository sysAttributeRepository) {
        log.debug("[GstDev Cloud] |- Frame Configure Attribute Service");
        return new SysAttributeServiceImpl(sysAttributeRepository);
    }
    @Bean
    @ConditionalOnMissingBean
    public DepartService sysDepartService(DepartRepository departRepository, DepartMapper departMappe) {
        log.debug("[GstDev Cloud] |- Frame Configure Depart Service");
        return new DepartServiceImpl(departRepository,departMappe);
    }

    @Bean
    @ConditionalOnMissingBean
    public DictService sysDictService(DictRepository dictRepository, DictMapper dictMapper) {
        log.debug("[GstDev Cloud] |- Frame Configure Dict Service");
        return new DictServiceImpl(dictRepository, dictMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public TenantDictService sysTenantDictService(TenantDictMapper tenantDictMapper, TenantDictRepository tenantDictRepository) {
        log.debug("[GstDev Cloud] |- Frame Configure Tenant Dict Service");
        return new TenantDictServiceImpl(tenantDictMapper, tenantDictRepository);
    }
}
