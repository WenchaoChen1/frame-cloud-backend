package com.frame.template.service.system.repository;

import com.frame.template.service.system.pojo.domain.TenantDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author zhucy
 */
public interface TenantDictRepository extends JpaRepository<TenantDict, String>, JpaSpecificationExecutor<TenantDict> {
    /**
     * 父级主键查询
     *
     * @param parentId
     * @return
     */
    List<TenantDict> findAllByParentIdOrderBySort(String parentId);

    /**
     * 通过code查询
     *
     * @param code
     * @return
     */
    List<TenantDict> findByCode(String code);

    List<TenantDict> findAllByParentIdAndTenantIdOrderBySort(String parentId, String tenantId);
}
