package com.frame.template.service.system.repository;

import com.frame.template.common.base.baseTree.BaseTreeRepository;
import com.frame.template.service.system.pojo.domain.Role;

import java.util.List;


public interface RoleRepository extends BaseTreeRepository<Role> {



  /*------------------------------------------以上是系统访问控制自定义代码--------------------------------------------*/


  /**
   * 查询子集
   *
   * @param parentId
   * @return
   */
  List<Role> findAllByParentIdOrderBySort(String parentId);

}
