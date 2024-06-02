// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.repository;

import com.gstdev.cloud.service.system.pojo.entity.Menu;
import com.gstdev.cloud.service.system.pojo.entity.RTenantMenu;
import com.gstdev.cloud.data.core.repository.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SysRTenantMenuRepository extends JpaRepository<RTenantMenu, String>, JpaSpecificationExecutor<RTenantMenu>, BaseRepository<RTenantMenu, String> {

    RTenantMenu findByTenantIdAndMenu(String tenantId, Menu menu);

    /**
     * 查询菜单主键
     *
     * @param tenantId
     * @return
     */
    List<RTenantMenu> findByTenantId(String tenantId);

    /**
     * 删除
     *
     * @param tenantId
     */
    void deleteByTenantId(String tenantId);

    RTenantMenu findByMenuIdAndTenantId(String id, String roleId);

//  List<RTenantMenu> findAllByTenantIdAndInMenuId(String tenantId, List<String> menuIds);

    List<RTenantMenu> findAllByTenantIdAndMenuIdIn(String tenantId, List<String> menuIds);
}

