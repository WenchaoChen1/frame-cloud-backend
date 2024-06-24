package com.gstdev.cloud.service.system.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.rest.core.controller.ResultController;
import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
import com.gstdev.cloud.service.system.domain.pojo.sysAttribute.AttributeManageAssignedPermissionIO;
import com.gstdev.cloud.service.system.domain.pojo.sysAttribute.AttributeManageDetailVo;
import com.gstdev.cloud.service.system.domain.pojo.sysAttribute.AttributeManageQO;
import com.gstdev.cloud.service.system.domain.pojo.sysAttribute.UpdateAttributeManageIO;
import com.gstdev.cloud.service.system.mapper.SysAttributeMapper;
import com.gstdev.cloud.service.system.service.SysAttributeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * <p>Description: SysAttributeController </p>
 */

@RestController
@RequestMapping("/v1/attribute")
//@Tags({
//    @Tag(name = "元数据管理接口"),
//})
public class SysAttributeController implements ResultController {

    @Resource
    private SysAttributeService sysAttributeService;

    @Resource
    private SysAttributeMapper sysAttributeMapper;

    public SysAttributeService getService() {
        return sysAttributeService;
    }
    public SysAttributeMapper getMapper() {
        return sysAttributeMapper;
    }
    // ********************************* Attribute Manage *****************************************

    @Tag(name = "Attribute Manage")
    @GetMapping("/get-attribute-manage-page")
    @Operation(summary = "get-attribute-manage-page")
    public Result<Map<String, Object>> getAttributeManagePage(AttributeManageQO sysAttributeAttributeManageQO, Pageable pageable) {
        return this.result(this.getMapper().toAttributeManagePageVo(this.getService().findByPage((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, sysAttributeAttributeManageQO, criteriaBuilder), pageable)));
    }

    @Tag(name = "Attribute Manage")
    @GetMapping("/get-attribute-manage-detail/{id}")
    @Operation(summary = "get-attribute-manage-detail")
    public Result<AttributeManageDetailVo> getAttributeManageDetail(@NotBlank @PathVariable String id) {
        return result(getMapper().toAttributeManageDetailVo(getService().findById(id)));
    }

//    @Tag(name = "Attribute Manage")
//    @PostMapping("/insert-attribute-manage")
//    @Operation(summary = "insert-attribute-manage")
//    public Result insertAttributeManage(@RequestBody @Validated InsertAttributeManageIO insertAttributeManageIO) {
//        this.getService().insertAndUpdate(attributeVoMapper.toEntity(insertAttributeManageIO));
//        return result();
//    }

    @Tag(name = "Attribute Manage")
    @PutMapping("/update-attribute-manage")
    @Operation(summary = "update-attribute-manage")
    public Result updateAttributeManage(@RequestBody @Validated UpdateAttributeManageIO updateAttributeManageIO) {
        SysAttribute sysAttribute = this.getService().findById(updateAttributeManageIO.getAttributeId());
        getMapper().copy(updateAttributeManageIO, sysAttribute);
        this.getService().insertAndUpdate(sysAttribute);
        return result();
    }

    @Tag(name = "Attribute Manage")
    @PostMapping("/attribute-manage-assigned-permission")
    public Result attributeManageAssignedPermission(@RequestBody AttributeManageAssignedPermissionIO attributeManageAssignedPermissionIO) {
        this.getService().attributeManageAssignedPermission(attributeManageAssignedPermissionIO);
        return result();
    }

    @Tag(name = "Attribute Manage")
    @PostMapping("/get-attribute-permission-id-by-attribute-id/{id}")
    public Result<Set<String>> getAttributePermissionIdByAttributeId(@PathVariable String id) {
        return result(this.getService().getAttributePermissionIdByAttributeId(id));
    }

//    @Tag(name = "Attribute Manage")
//    @Operation(summary = "delete-attribute-manage")
//    @DeleteMapping("/delete-attribute-manage/{id}")
//    public Result deleteAttributeManage(@PathVariable String id) {
//        getService().deleteById(id);
//        return result();
//    }
//
//    @Tag(name = "Attribute Manage")
//    @Operation(summary = "/delete-all-attribute-manage")
//    @DeleteMapping("/delete-all-attribute-manage")
//    public Result deleteAllAttributeManage(List<String> id) {
//        getService().deleteAllById(id);
//        return result();
//    }
}
