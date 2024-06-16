// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.mapper.vo;

import com.gstdev.cloud.data.core.mapper.BaseTreeMapper;
import com.gstdev.cloud.service.system.pojo.base.menu.MenuDto;
import com.gstdev.cloud.service.system.pojo.base.menu.MenuInsertInput;
import com.gstdev.cloud.service.system.pojo.base.menu.MenuUpdateInput;
import com.gstdev.cloud.service.system.pojo.base.menu.MenuVo;
import com.gstdev.cloud.service.system.pojo.entity.SysAccount;
import com.gstdev.cloud.service.system.pojo.entity.SysMenu;
import com.gstdev.cloud.service.system.pojo.o.sysAccount.InsertAccountManageInitializationIO;
import com.gstdev.cloud.service.system.pojo.o.sysMenu.InsertMenuManageIO;
import com.gstdev.cloud.service.system.pojo.o.sysMenu.UpdateMenuManageIO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface SysMenuMapper extends BaseTreeMapper<SysMenu, MenuDto, MenuVo, MenuInsertInput, MenuUpdateInput> {
    void copy(UpdateMenuManageIO updateMenuManageIO, @MappingTarget SysMenu sysMenu);
    SysMenu toEntity(InsertMenuManageIO insertMenuManageIO);

}

