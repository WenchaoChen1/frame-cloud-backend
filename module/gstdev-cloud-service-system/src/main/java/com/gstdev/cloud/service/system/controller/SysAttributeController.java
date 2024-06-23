package com.gstdev.cloud.service.system.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.rest.core.annotation.AccessLimited;
import com.gstdev.cloud.rest.core.controller.Controller;
import com.gstdev.cloud.service.system.mapper.vo.SysAttributeVoMapper;
import com.gstdev.cloud.service.system.domain.base.SysAttribute.SysAttributeVo;
import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
import com.gstdev.cloud.service.system.service.SysAttributeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>Description: SysAttributeController </p>
 */

@RestController
@RequestMapping("/v1/attribute")
@Tags({
    @Tag(name = "元数据管理接口"),
})
public class SysAttributeController implements Controller<SysAttribute, String> {

    @Resource
    private SysAttributeService sysAttributeService;

    @Resource
    private SysAttributeVoMapper sysAttributeVoMapper;

    @Override
    public SysAttributeService getService() {
        return sysAttributeService;
    }

    @Override
    @AccessLimited
    @Operation(summary = "获取全部元数据", description = "获取全部元数据列表",
        responses = {
            @ApiResponse(description = "全部数据列表", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "204", description = "查询成功，未查到数据"),
            @ApiResponse(responseCode = "500", description = "查询失败")
        })
    @GetMapping("/page")
    public Result<Map<String, Object>> findByPage(Pageable pageable) {
        Page<SysAttribute> byPage = getService().findByPage(pageable);
        Page<SysAttributeVo> sysAttributeVos = sysAttributeVoMapper.entityToVo(byPage);
        return Result.success((Map<String, Object>) sysAttributeVos);
    }
}
