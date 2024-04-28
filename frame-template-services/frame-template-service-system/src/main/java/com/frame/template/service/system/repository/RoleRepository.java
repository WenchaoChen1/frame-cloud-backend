package com.frame.template.service.system.repository;


import com.frame.template.service.system.pojo.entity.Role;
import com.gstdev.cloud.data.core.repository.BaseTreeRepository;

import java.util.List;


public interface RoleRepository extends BaseTreeRepository<Role, String> {



    /*------------------------------------------以上是系统访问控制自定义代码--------------------------------------------*/


    /**
     * 查询子集
     *
     * @param parentId
     * @return
     */
    List<Role> findAllByParentIdOrderBySort(String parentId);

}
