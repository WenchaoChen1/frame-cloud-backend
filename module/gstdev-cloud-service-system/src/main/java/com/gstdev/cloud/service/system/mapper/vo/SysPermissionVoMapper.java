// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.mapper.vo;

import com.gstdev.cloud.data.core.mapper.BaseVoMapper;
import com.gstdev.cloud.service.system.domain.base.SysPermission.SysPermissionDto;
import com.gstdev.cloud.service.system.domain.base.SysPermission.SysPermissionVo;
import com.gstdev.cloud.service.system.domain.entity.SysPermission;
import com.gstdev.cloud.service.system.domain.pojo.sysPermission.InsertPermissionManageIO;
import com.gstdev.cloud.service.system.domain.pojo.sysPermission.UpdatePermissionManageIO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface SysPermissionVoMapper extends BaseVoMapper<SysPermissionDto,SysPermissionVo> {
//public interface SysPermissionVoMapper {
//    SysPermissionVo entityToVo(SysPermission entity);

    List<SysPermissionVo> entityToVo(List<SysPermission> entity);
    SysPermission toEntity(InsertPermissionManageIO insertPermissionManageIO);
    SysPermissionVo toVo(SysPermission entity);
    void copy(UpdatePermissionManageIO updatePermissionManageIO, @MappingTarget SysPermission sysPermission);

//    default Page<SysPermissionVo> entityToVo(Page<SysPermission> page) {
//        List<SysPermissionVo> responses = this.entityToVo(page.getContent());
//        return new PageImpl<>(responses, page.getPageable(), page.getTotalElements());
//    }

}

