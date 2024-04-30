package com.gstdev.cloud.service.system.repository;


import com.gstdev.cloud.service.system.pojo.entity.Dict;
import com.gstdev.cloud.data.core.repository.BaseTreeRepository;

import java.util.List;


public interface DictRepository extends BaseTreeRepository<Dict, String> {
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
