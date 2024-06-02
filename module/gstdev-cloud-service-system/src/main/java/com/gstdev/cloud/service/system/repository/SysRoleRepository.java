package com.gstdev.cloud.service.system.repository;


import com.gstdev.cloud.service.system.pojo.entity.SysRole;
import com.gstdev.cloud.data.core.repository.BaseTreeRepository;

import java.util.List;


public interface SysRoleRepository extends BaseTreeRepository<SysRole, String> {



    /*------------------------------------------以上是系统访问控制自定义代码--------------------------------------------*/


    /**
     * 查询子集
     *
     * @param parentId
     * @return
     */
    List<SysRole> findAllByParentIdOrderBySort(String parentId);

}
