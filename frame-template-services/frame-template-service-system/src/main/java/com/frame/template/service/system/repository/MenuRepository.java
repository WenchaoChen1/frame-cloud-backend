package com.frame.template.service.system.repository;


import com.frame.template.service.system.pojo.entity.Menu;
import com.gstdev.cloud.data.core.repository.BaseTreeRepository;

import java.util.List;

public interface MenuRepository extends BaseTreeRepository<Menu, String> {
    /**
     * 父级主键查询
     *
     * @param parentId
     * @return
     */
    List<Menu> findAllByParentIdOrderBySort(String parentId);

    /**
     * 通过parentid 和小于指定的排序查询
     *
     * @param parentId
     * @param sort
     * @return
     */
    List<Menu> findAllByParentIdAndSortLessThanOrderBySort(String parentId, int sort);

    /**
     * 通过parentid 和大于指定的排序查询
     *
     * @param parentId
     * @param sort
     * @return
     */
    List<Menu> findAllByParentIdAndSortGreaterThanOrderBySort(String parentId, int sort);


    List<Menu> findByStatusAndParentIdAndType(Integer status, String parentId, Integer type);
}
