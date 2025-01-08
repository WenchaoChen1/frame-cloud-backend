package com.frame.template.service.basic.controller;

import com.frame.template.service.basic.domain.MusicianManageDetailVo;
import com.frame.template.service.basic.domain.MusicianManageQO;
import com.frame.template.service.basic.mapper.MusicianMapper;
import com.frame.template.service.basic.service.MusicianService;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.utils.BasePage;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.rest.core.controller.ResultController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/musician")
public class MusicianController  implements ResultController {

    @Resource
    private MusicianService musicianService;

    @Resource
    private MusicianMapper musicianMapper;

    public MusicianService getService() {
        return musicianService;
    }

    public MusicianMapper getMapper() {
        return musicianMapper;
    }
    @Tag(name = "Musician Manage")
    @GetMapping("/get-musician-manage-page")
    @Operation(summary = "get-musician-manage-page")
    public Result<Map<String, Object>> getMusicianManagePage(MusicianManageQO musicianManageQO, BasePage pageable) {
        return this.result(this.getMapper().toMusicianManagePageVo(this.getService().findByPage((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, musicianManageQO, criteriaBuilder), pageable)));
    }

    @Tag(name = "Musician Manage")
    @GetMapping("/get-musician-manage-detail/{id}")
    @Operation(summary = "get-musician-manage-detail")
    public Result<MusicianManageDetailVo> getMusicianManageDetail(@PathVariable String id) {
        return result(this.getMapper().toMusicianDetailVo(getService().findById(id)));
    }

}
