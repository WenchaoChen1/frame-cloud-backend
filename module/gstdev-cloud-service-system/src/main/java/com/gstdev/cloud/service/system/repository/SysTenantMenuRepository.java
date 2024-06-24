// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.repository;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.service.system.domain.entity.SysMenu;
import com.gstdev.cloud.service.system.domain.entity.SysTenantMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SysTenantMenuRepository extends JpaRepository<SysTenantMenu, String>, JpaSpecificationExecutor<SysTenantMenu>, BaseRepository<SysTenantMenu, String> {

    SysTenantMenu findByTenantIdAndMenu(String tenantId, SysMenu menu);

    /**
     * 查询菜单主键
     *
     * @param tenantId
     * @return
     */
    List<SysTenantMenu> findByTenantId(String tenantId);

    /**
     * 删除
     *
     * @param tenantId
     */
    void deleteByTenantId(String tenantId);

    SysTenantMenu findByMenuIdAndTenantId(String id, String roleId);

//  List<RTenantMenu> findAllByTenantIdAndInMenuId(String tenantId, List<String> menuIds);

    List<SysTenantMenu> findAllByTenantIdAndMenuIdIn(String tenantId, List<String> menuIds);
}

