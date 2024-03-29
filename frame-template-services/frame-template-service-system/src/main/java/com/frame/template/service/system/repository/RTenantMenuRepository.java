// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.repository;

import com.frame.template.common.base.BaseRepository;
import com.frame.template.service.system.pojo.domain.Menu;
import com.frame.template.service.system.pojo.domain.RTenantMenu;

import java.util.List;

public interface RTenantMenuRepository extends BaseRepository<RTenantMenu> {

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

