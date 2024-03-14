package com.gstdev.template.service.system.repository;

import com.gstdev.template.common.base.baseTree.BaseTreeRepository;
import com.gstdev.template.service.system.pojo.domain.Dict;

import java.util.List;

/**
 * @author zhucy
 */
public interface DictRepository extends BaseTreeRepository<Dict> {
  /**
   * 父级主键查询
   *
   * @param parentId
   * @return
   */
  List<Dict> findAllByParentIdOrderBySort(String parentId);

  /**
   * 通过parentid 和小于指定的排序查询
   *
   * @param parentId
   * @param sort
   * @return
   */
  List<Dict> findAllByParentIdAndSortLessThanOrderBySort(String parentId, int sort);

  /**
   * 通过parentid 和大于指定的排序查询
   *
   * @param parentId
   * @param sort
   * @return
   */
  List<Dict> findAllByParentIdAndSortGreaterThanOrderBySort(String parentId, int sort);

  /**
   * 通过code查询
   *
   * @param code
   * @return
   */
  List<Dict> findByCode(String code);
}
