package com.gstdev.cloud.service.system.repository;


import com.gstdev.cloud.service.system.pojo.entity.Depart;
import com.gstdev.cloud.data.core.repository.BaseTreeRepository;

import java.util.List;

public interface DepartRepository extends BaseTreeRepository<Depart, String> {
    /**
     * 父级主键查询
     *
     * @param parentId
     * @return
     */
    List<Depart> findAllByParentIdOrderBySort(String parentId);

    /**
     * 通过parentid 和小于指定的排序查询
     *
     * @param parentId
     * @param sort
     * @return
     */
    List<Depart> findAllByParentIdAndSortLessThanOrderBySort(String parentId, int sort);

    /**
     * 通过parentid 和大于指定的排序查询
     *
     * @param parentId
     * @param sort
     * @return
     */
    List<Depart> findAllByParentIdAndSortGreaterThanOrderBySort(String parentId, int sort);

    List<Depart> findByTenantId(String tenantId);
}
