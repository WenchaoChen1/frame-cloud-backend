package com.gstdev.cloud.service.system.repository;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.service.system.domain.entity.SysRRoleBusinessPermission;
import com.gstdev.cloud.service.system.domain.generator.SysRRoleBusinessPermissionEmbeddablePK;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SysRRoleBusinessPermissionRepository extends BaseRepository<SysRRoleBusinessPermission, SysRRoleBusinessPermissionEmbeddablePK> {



    void deleteAllByRoleId(String roleIds);
}


