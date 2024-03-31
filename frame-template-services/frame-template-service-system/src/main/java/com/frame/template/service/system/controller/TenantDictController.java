//package com.gstdev.template.service.system.controller;
//
//import com.gstdev.cloud.commons.ass.definition.domain.Result;
//import com.frame.template.service.system.service.TenantDictService;
//import com.frame.template.service.system.pojo.vo.TenantDict.TenantDictDto;
//import com.frame.template.service.system.pojo.vo.TenantDict.TenantDictModifyInput;
//import com.frame.template.service.system.pojo.vo.TenantDict.TenantDictSaveInput;
//import com.frame.template.service.system.pojo.vo.menu.MenuDto;
//import io.swagger.annotations.ApiOperation;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * @author zhucy
// */
//@RestController
//@RequestMapping("/tenantDict")
//@RequiredArgsConstructor
//public class TenantDictController {
//  private final TenantDictService tenantDictService;
//
//  /**
//   * 新增
//   *
//   * @param tenantDictSaveInput
//   * @return
//   */
//  @PostMapping
//  @ApiOperation("save")
//  public Result<String> save(@RequestBody TenantDictSaveInput tenantDictSaveInput) {
//    try {
//      tenantDictService.save(tenantDictSaveInput);
//      return Result.success("Save successfully");
//    } catch (Exception e) {
//      e.printStackTrace();
//      return Result.failure("Save failed");
//    }
//  }
//
//  /**
//   * 主键查询详情
//   *
//   * @param id
//   * @return
//   */
//  @GetMapping
//  @ApiOperation("findById")
//  public Result<TenantDictDto> findById(String id) {
//    try {
//      return Result.success(tenantDictService.findById(id));
//    } catch (Exception e) {
//      e.printStackTrace();
//      return Result.failure("Query failed");
//    }
//  }
//
//  /**
//   * 编辑
//   *
//   * @param tenantDictModifyInput
//   * @return
//   */
//  @PutMapping
//  @ApiOperation("modify")
//  public Result<MenuDto> modify(@RequestBody TenantDictModifyInput tenantDictModifyInput) {
//    try {
//      tenantDictService.modify(tenantDictModifyInput);
//      return Result.success("Save successfully");
//    } catch (Exception e) {
//      e.printStackTrace();
//      return Result.failure("Save failed");
//    }
//  }
//
//  /**
//   * 删除
//   *
//   * @param id
//   * @return
//   */
//  @ApiOperation("")
//  @DeleteMapping
//  public Result<Object> deleteById(String id) {
//    try {
//      tenantDictService.deleteById(id);
//      return Result.success("Delete successfully");
//    } catch (Exception e) {
//      e.printStackTrace();
//      return Result.failure("Delete failed");
//    }
//  }
//
//  /**
//   * 查询树状菜单
//   *
//   * @return
//   */
//  @ApiOperation("Get all tree")
//  @GetMapping("/getAllTree")
//  public Result<List<TenantDictDto>> getAllTree(String tenantDictId) {
//    try {
//      return Result.success(tenantDictService.getAllTree(tenantDictId));
//    } catch (Exception e) {
//      e.printStackTrace();
//      return Result.failure("Query failed");
//    }
//  }
//
//}
