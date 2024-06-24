//package com.gstdev.cloud.service.system.service;
//
//import com.gstdev.cloud.service.system.repository.SysRRoleTenantMenuRepository;
//import jakarta.annotation.Resource;
//import org.springframework.transaction.annotation.Transactional;
//
//@Transactional
//public class SysRRoleTenantMenuServiceImpl implements SysRRoleTenantMenuService {
//
//    @Resource
//    private SysRRoleTenantMenuRepository sysRRoleRTenantMenuRepository;
//
//    public SysRRoleTenantMenuRepository getRepository() {
//        return sysRRoleRTenantMenuRepository;
//    }
//
//    public SysRRoleTenantMenuServiceImpl(SysRRoleTenantMenuRepository sysRRoleRTenantMenuRepository) {
//        this.sysRRoleRTenantMenuRepository = sysRRoleRTenantMenuRepository;
//    }
//
//    @Override
//    public void deleteByTenantMenuId(String rTenantMenuId) {
//        sysRRoleRTenantMenuRepository.deleteByTenantMenuId(rTenantMenuId);
//    }
//}
