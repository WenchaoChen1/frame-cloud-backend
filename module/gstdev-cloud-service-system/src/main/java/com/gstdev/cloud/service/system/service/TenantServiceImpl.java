// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.service;


import cn.hutool.core.lang.UUID;
import com.gstdev.cloud.service.system.mapper.SysPermissionMapper;
import com.gstdev.cloud.service.system.mapper.TenantMapper;
import com.gstdev.cloud.service.system.pojo.base.tenant.*;
import com.gstdev.cloud.service.system.pojo.entity.Tenant;
import com.gstdev.cloud.service.system.repository.SysPermissionRepository;
import com.gstdev.cloud.service.system.repository.TenantRepository;
import com.gstdev.cloud.data.core.service.BaseTreeServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(readOnly = true)
public class TenantServiceImpl extends BaseTreeServiceImpl<Tenant, String, TenantRepository, TenantMapper, TenantDto> implements TenantService {

    @Resource
    private AccountService accountService;
    @Resource
    private TenantRepository tenantRepository;
    public TenantServiceImpl(TenantRepository tenantRepository, TenantMapper tenantMapper) {
        super(tenantRepository, tenantMapper);
        this.tenantRepository=tenantRepository;
    }


    public TenantRepository getRepository() {
        return tenantRepository;
    }


    @Override
    @Transactional
    public Tenant insert(Tenant tenant) {
        if (tenant.getTenantCode() == null) {
            tenant.setTenantCode(UUID.fastUUID().toString());
        }
        Tenant save = super.insert(tenant);
        //新增租户的时候默认向组织里插入一条数据
//    DepartSaveInput departSaveInput = new DepartSaveInput();
//    departSaveInput.setName(save.getTenantName());
//    departSaveInput.setCode(save.getTenantCode());
//    departSaveInput.setParentId("1");
//    departSaveInput.setSort(1);
//    departSaveInput.setTenantId(save.getId());
//    DepartDto insert = systemDepartService.insert(departSaveInput);
//
        return save;
    }

    @Override
    public void deleteById(String id) {
        super.deleteById(id);
        accountService.deleteByTenantId(id);
    }

//  /**
//   * 获取当前租户id  返回本级和所有子集
//   * 树查询
//   * 返回用户数量
//   *
//   * @return
//   */
//  @Override
//  public Result<List<TenantDto>> getTenantTree() {
//    List<TenantDto> tenantDtos = findByParentIdToDto(redisCurrentLoginInformation.getCurrentLoginInformation().getTenantId());
//    if (ObjectUtil.isNotEmpty(tenantDtos)) {
//      for (TenantDto tenantDto : tenantDtos) {
//        Integer users = systemAccountService.findByTenantId(tenantDto.getId());
//        tenantDto.setUsers(users);
//      }
//    }
//    return Result.success(tenantDtos);
//  }
//
//  @Override
//  @Transactional
//  public Result<TenantDto> updateCurrentLoginInferiorSonTenant(TenantLoginInferiorUpdateInput tenantLoginInferiorUpdateInput) {
//    Tenant tenant = findById(tenantLoginInferiorUpdateInput.getId());
//    getMapper().copyModify(tenantLoginInferiorUpdateInput, tenant);
//    update(tenant);
//    return Result.success(getMapper().toDto(tenant));
//  }


    public List<TenantDto> findAllByIds(List<String> tenantIds) {
        return getMapper().toDto(getRepository().findAllById(tenantIds));
    }


}

