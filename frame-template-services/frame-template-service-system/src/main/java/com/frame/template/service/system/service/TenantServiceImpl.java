// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.service;


import cn.hutool.core.lang.UUID;
import com.frame.template.service.system.mapper.TenantMapper;
import com.frame.template.service.system.pojo.base.tenant.*;
import com.frame.template.service.system.pojo.domain.Tenant;
import com.frame.template.service.system.repository.TenantRepository;
import com.gstdev.cloud.base.definition.domain.Result;
import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformation;

import com.gstdev.cloud.data.core.service.BaseTreeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class TenantServiceImpl extends BaseTreeServiceImpl<Tenant, String, TenantRepository, TenantMapper, TenantDto, TenantInsertInput, TenantUpdateInput, TenantPageQueryCriteria, TenantFindAllByQueryCriteria> implements TenantService {

    private TenantRepository tenantRepository;
    private TenantMapper tenantMapper;
    //  @Resource
//  private SystemDepartService systemDepartService;
    private RedisCurrentLoginInformation redisCurrentLoginInformation;

    //  @Resource
//  private SystemAccountService systemAccountService;
    private AccountService accountService;

    @Autowired
    public TenantServiceImpl(TenantRepository tenantRepository, TenantMapper tenantMapper, RedisCurrentLoginInformation redisCurrentLoginInformation) {
        super(tenantRepository, tenantMapper);
        this.tenantRepository = tenantRepository;
        this.tenantMapper = tenantMapper;
        this.redisCurrentLoginInformation = redisCurrentLoginInformation;
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
        return tenantMapper.toDto(tenantRepository.findAllById(tenantIds));
    }


}

