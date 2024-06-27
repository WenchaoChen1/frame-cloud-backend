package com.gstdev.cloud.service.system.mapper;

import com.gstdev.cloud.service.system.TreeUtils;
import com.gstdev.cloud.service.system.domain.entity.SysBusinessPermission;
import com.gstdev.cloud.service.system.domain.pojo.sysBusinessPermission.BusinessPermissionManageTreeVo;
import com.gstdev.cloud.service.system.domain.pojo.sysBusinessPermission.InsertBusinessPermissionManageIO;
import com.gstdev.cloud.service.system.domain.pojo.sysBusinessPermission.UpdateBusinessPermissionManageIO;
import com.gstdev.cloud.service.system.domain.pojo.sysPermission.PermissionManageDetailVo;
import org.mapstruct.*;

import java.util.Comparator;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface SysBusinessPermissionMapper {

    SysBusinessPermission toEntity(InsertBusinessPermissionManageIO insertBusinessPermissionManageIO);

    PermissionManageDetailVo toBusinessPermissionManageDetailVo(SysBusinessPermission sysBusinessPermission);


    void copy(UpdateBusinessPermissionManageIO updateBusinessPermissionManageIO, @MappingTarget SysBusinessPermission sysBusinessPermission);



    List<BusinessPermissionManageTreeVo> toBusinessPermissionManageTreeVo(List<SysBusinessPermission> byPage);


    default List<BusinessPermissionManageTreeVo> toBusinessPermissionManageTreeVoToTree(List<SysBusinessPermission> byPage) {
        List<BusinessPermissionManageTreeVo> businessPermissionManageTreeVo = toBusinessPermissionManageTreeVo(byPage);
        return TreeUtils.buildTree(
                businessPermissionManageTreeVo,
                BusinessPermissionManageTreeVo::getId,
                BusinessPermissionManageTreeVo::getParentId,
                Comparator.comparingInt((BusinessPermissionManageTreeVo item) ->
                        item.getSort() != null ? item.getSort() : Integer.MAX_VALUE)
        );
    }
}
