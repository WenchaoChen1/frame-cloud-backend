package com.frame.template.service.system.controller;

import com.frame.template.service.system.mapper.vo.DictVoMapper;
import com.frame.template.service.system.pojo.base.dict.*;
import com.frame.template.service.system.service.DictService;
import com.gstdev.cloud.commons.ass.definition.domain.Result;
import com.frame.template.common.base.baseTree.BaseTreeController;
import com.frame.template.service.system.pojo.base.dict.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

import java.util.List;

@RestController
@RequestMapping("/v1/dict")
public class DictController extends BaseTreeController<DictService, DictVoMapper, DictVo, DictDto, DictInsertInput, DictUpdateInput, DictPageQueryCriteria, DictFindAllByQueryCriteria> {

  @Resource
  private DictService dictService;

  @Resource
  private DictVoMapper dictVoMapper;

  public DictController(DictService dictService, DictVoMapper dictVoMapper) {
    super(dictService, dictVoMapper);
    this.dictService = dictService;
    this.dictVoMapper = dictVoMapper;
  }

  @GetMapping("/get-all-dict-to-tree")
  @Operation(summary = "获取所有数据，返回树状结构")
  public Result<List<DictVo>> findAllByQueryCriteriaToTree() {
    DictFindAllByQueryCriteria dictFindAllByQueryCriteria = new DictFindAllByQueryCriteria();
    return findAllByQueryCriteriaToResultToTree(dictFindAllByQueryCriteria);
  }

  @GetMapping("/get-by-id")
  @Operation(summary = "根据id获取实体数据")
  public Result<DictVo> getById(String id) {
    return findByIdToResult(id);
  }

  @PostMapping
  @Operation(summary = "新增一条数据")
  public Result<DictVo> insert(@RequestBody DictInsertInput dictInsertInput) {
    return insertToResult(dictInsertInput);
  }

  @PutMapping
  @Operation(summary = "修改一条数据")
  public Result<DictVo> update(@RequestBody DictUpdateInput dictUpdateInput) {
    return updateToResult(dictUpdateInput);
  }

  @Operation(summary = "")
  @DeleteMapping
  public Result<DictVo> deleteById(String id) {
    return deleteByIdToResult(id);
  }

  /*------------------------------------------以上是系统访问控制代码--------------------------------------------*/

}
