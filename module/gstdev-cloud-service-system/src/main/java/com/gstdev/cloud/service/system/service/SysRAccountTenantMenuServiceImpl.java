package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.system.domain.entity.SysRAccountTenantMenu;
import com.gstdev.cloud.service.system.domain.generator.SysRAccountTenantMenuEmbeddablePK;
import com.gstdev.cloud.service.system.repository.SysRAccountTenantMenuRepository;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class SysRAccountTenantMenuServiceImpl extends BaseServiceImpl<SysRAccountTenantMenu, SysRAccountTenantMenuEmbeddablePK, SysRAccountTenantMenuRepository> implements SysRAccountTenantMenuService {

    @Resource
    private SysRAccountTenantMenuRepository repository;
    @Resource
    @Lazy
    private SysRAccountTenantMenuServiceImpl service;

    public SysRAccountTenantMenuServiceImpl(SysRAccountTenantMenuRepository sysRAccountTenantMenuRepository) {
        super(sysRAccountTenantMenuRepository);
    }

    @Override
    public SysRAccountTenantMenuRepository getRepository() {
        return repository;
    }

    @Override
    public SysRAccountTenantMenuServiceImpl getService() {
        return service;
    }


    @Override
    @Transactional
    public void updateAccountAssignedTenantMenu(String roleIds, List<String> businessPermissionIds) {
        getRepository().deleteAllByAccountId(roleIds);
        if (businessPermissionIds.isEmpty()) {
            return;
        }
        saveAllAndFlush(toEntityList(roleIds, businessPermissionIds));
    }

    @Override
    public List<String> getAllTenantMenuIdByAccountId(String accountId) {
        return getRepository().findAllByAccountId(accountId).stream().map(SysRAccountTenantMenu::getTenantMenuId).toList();
    }

    List<SysRAccountTenantMenu> toEntityList(String roleId, List<String> businessPermissionIds) {
        return businessPermissionIds.stream().map(businessPermissionId -> {
            SysRAccountTenantMenu sysRAccountTenantMenu = new SysRAccountTenantMenu();
            sysRAccountTenantMenu.setTenantMenuId(businessPermissionId);
            sysRAccountTenantMenu.setAccountId(roleId);
            return sysRAccountTenantMenu;
        }).toList();
    }

    List<SysRAccountTenantMenu> toEntityList(List<String> roleIds, String businessPermissionId) {
        return roleIds.stream().map(roleId -> {
            SysRAccountTenantMenu sysRAccountTenantMenu = new SysRAccountTenantMenu();
            sysRAccountTenantMenu.setTenantMenuId(businessPermissionId);
            sysRAccountTenantMenu.setAccountId(roleId);
            return sysRAccountTenantMenu;
        }).toList();
    }



}
