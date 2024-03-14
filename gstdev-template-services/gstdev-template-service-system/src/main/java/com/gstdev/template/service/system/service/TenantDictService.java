package com.gstdev.template.service.system.service;

import com.gstdev.template.service.system.pojo.vo.TenantDict.TenantDictDto;
import com.gstdev.template.service.system.pojo.vo.TenantDict.TenantDictModifyInput;
import com.gstdev.template.service.system.pojo.vo.TenantDict.TenantDictSaveInput;

import java.util.List;

/**
 * @author zhucy
 */
public interface TenantDictService {
  /**
   * 字典保存
   *
   * @param tenantDictSaveInput
   * @return
   */
  TenantDictDto save(TenantDictSaveInput tenantDictSaveInput);

  /**
   * 字典修改
   *
   * @param tenantDictModifyInput
   * @return
   */
  TenantDictDto modify(TenantDictModifyInput tenantDictModifyInput);

  /**
   * 主键查询
   *
   * @param id
   * @return
   */
  TenantDictDto findById(String id);

  /**
   * 删除
   *
   * @param id
   * @return
   */
  void deleteById(String id);

  /**
   * 查询所有树状
   *
   * @return
   */
  List<TenantDictDto> getAllTree(String tenantDictId);
}
