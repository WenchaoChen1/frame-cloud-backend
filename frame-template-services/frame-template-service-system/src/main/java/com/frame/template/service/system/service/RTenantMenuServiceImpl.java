// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.service;


import com.frame.template.service.system.pojo.base.rTenantMenu.*;
import com.gstdev.cloud.commons.ass.definition.domain.Result;
import com.frame.template.common.base.BaseServiceImpl;
import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformation;
import com.frame.template.service.system.pojo.base.rTenantMenu.*;
import com.frame.template.service.system.pojo.domain.RTenantMenu;
import com.frame.template.service.system.mapper.RTenantMenuMapper;
import com.frame.template.service.system.repository.MenuRepository;
import com.frame.template.service.system.repository.RTenantMenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class RTenantMenuServiceImpl extends BaseServiceImpl<RTenantMenuRepository, RTenantMenuMapper, RTenantMenu, RTenantMenuDto, RTenantMenuInsertInput, RTenantMenuUpdateInput, RTenantMenuPageQueryCriteria, RTenantMenuFindAllByQueryCriteria, RedisCurrentLoginInformation> implements RTenantMenuService {

  @Resource
  private RTenantMenuRepository rTenantMenuRepository;
  @Resource
  private RTenantMenuMapper rTenantMenuMapper;
  @Resource
  private RedisCurrentLoginInformation redisCurrentLoginInformation;
  @Resource
  private MenuRepository MenuRepository;

  public RTenantMenuServiceImpl(RTenantMenuRepository rTenantMenuRepository, RTenantMenuMapper rTenantMenuMapper, RedisCurrentLoginInformation redisCurrentLoginInformation) {
    super(rTenantMenuRepository, rTenantMenuMapper, redisCurrentLoginInformation);
    this.rTenantMenuRepository = rTenantMenuRepository;
    this.rTenantMenuMapper = rTenantMenuMapper;
    this.redisCurrentLoginInformation = redisCurrentLoginInformation;
  }

  public List<RTenantMenu> findAllByTenantId(String tenantId) {
    return getRepository().findByTenantId(tenantId);
  }


  @Override
  @Transactional
  public Result<String> insertTenantMenu(RTenantMenuInsertInput rTenantMenuInsertInput) {
    List<RTenantMenu> allByTenantId = findAllByTenantId(rTenantMenuInsertInput.getTenantId());
    List<String> menuIds = rTenantMenuInsertInput.getMenuIds();
    List<RTenantMenu> rTenantMenus = new ArrayList<>();
    allByTenantId.forEach(rTenantMenu -> {
      if (menuIds.contains(rTenantMenu.getMenu().getId())) {
        menuIds.remove(rTenantMenu.getMenu().getId());
        rTenantMenus.add(rTenantMenu);
      } else {
        getRepository().delete(rTenantMenu);
      }
    });
    if (menuIds.size() > 0) {
      menuIds.forEach(id -> {
        RTenantMenu rTenantMenu = new RTenantMenu();
        rTenantMenu.setTenantId(rTenantMenuInsertInput.getTenantId());
        rTenantMenu.setMenu(MenuRepository.findById(id).get());
        rTenantMenus.add(rTenantMenu);
      });
    }
    updateAll(rTenantMenus);
    return Result.success();
  }

  //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////


}
