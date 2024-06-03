package com.gstdev.cloud.service.system.controller;

import com.gstdev.cloud.data.core.utils.BasePage;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.rest.core.annotation.Idempotent;
import com.gstdev.cloud.rest.core.controller.DtoController;
import com.gstdev.cloud.service.system.mapper.vo.SysPermissionVoMapper;
import com.gstdev.cloud.service.system.pojo.base.SysPermission.SysPermissionDto;
import com.gstdev.cloud.service.system.pojo.base.SysPermission.SysPermissionPageQueryCriteria;
import com.gstdev.cloud.service.system.pojo.base.SysPermission.SysPermissionVo;
import com.gstdev.cloud.service.system.pojo.entity.SysPermission;
import com.gstdev.cloud.service.system.pojo.entity.SysPermission;
import com.gstdev.cloud.service.system.service.SysPermissionService;
import com.gstdev.cloud.service.system.service.SysPermissionService;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.rest.core.annotation.AccessLimited;
import com.gstdev.cloud.rest.core.controller.BaseController;
import com.gstdev.cloud.rest.core.controller.Controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>Description: SysPermissionController </p>
 */
//@ResponseBody
@RestController
@RequestMapping("/v1/permission")
//@Tags({
//    @Tag(name = "用户安全管理接口"),
//    @Tag(name = "系统权限管理接口")
//})
public class SysPermissionController implements DtoController<SysPermission, String, SysPermissionDto> {

    @Resource
    private SysPermissionService sysPermissionService;

    @Resource
    private SysPermissionVoMapper sysPermissionVoMapper;

    @Override
    public SysPermissionService getService() {
        return sysPermissionService;
    }

    @AccessLimited
    @Operation(summary = "获取全部权限", description = "获取全部权限数据列表",
        responses = {
            @ApiResponse(description = "全部数据列表", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "204", description = "查询成功，未查到数据"),
            @ApiResponse(responseCode = "500", description = "查询失败")
        })
    @GetMapping("/page")
    public Result<Page<SysPermissionVo>> findByPageToResult(SysPermissionPageQueryCriteria sysPermissionPageQueryCriteria, BasePage pageable) {
        Page<SysPermissionDto> byPageToDto = getService().findByPageToDto((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, sysPermissionPageQueryCriteria, criteriaBuilder), pageable);
        Page<SysPermissionVo> SysPermissionVos = sysPermissionVoMapper.toVo(byPageToDto);
        return Result.success(SysPermissionVos);
    }

    @Idempotent
    @Operation(summary = "保存或更新数据", description = "接收JSON数据，转换为实体，进行保存或更新",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json")),
        responses = {@ApiResponse(description = "已保存数据", content = @Content(mediaType = "application/json"))})
    @Parameters({
        @Parameter(name = "domain", required = true, description = "可转换为实体的json数据")
    })
    @PostMapping
    public Result<SysPermissionVo> saveOrUpdatea(@RequestBody SysPermission domain) {
//        SysPermission sysPermission=new SysPermission();
//        sysPermission.setPermissionId(domain.getPermissionId());
//        sysPermission.setPermissionName(domain.getPermissionName());
//        sysPermission.setPermissionCode(domain.getPermissionCode());
//        sysPermission.setPermissionType(domain.getPermissionType());
//        sysPermission.setStatus(domain.getStatus());
        return result(sysPermissionVoMapper.toVo(getService().saveToDto(domain)));
    }

    @Idempotent
    @Operation(summary = "删除数据", description = "根据实体ID删除数据，以及相关联的关联数据",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json")),
        responses = {@ApiResponse(description = "操作消息", content = @Content(mediaType = "application/json"))})
    @Parameters({
        @Parameter(name = "id", required = true, in = ParameterIn.PATH, description = "实体ID，@Id注解对应的实体属性")
    })
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable String id) {
        return DtoController.super.delete(id);
    }

    @Operation(summary = "permissionType",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json")),
        responses = {@ApiResponse(description = "操作消息", content = @Content(mediaType = "application/json"))})
    @Parameters({
    })
    @GetMapping("/permissionType")
    public Result<List<String>> findDistinctPermissionTypes() {
        return result(getService().findDistinctPermissionTypes());
    }
}
