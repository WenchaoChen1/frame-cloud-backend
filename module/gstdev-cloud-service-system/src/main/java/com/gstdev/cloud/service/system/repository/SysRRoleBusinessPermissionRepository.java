package com.gstdev.cloud.service.system.repository;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.service.system.domain.entity.SysRAttributeMenu;
import com.gstdev.cloud.service.system.domain.entity.SysRRoleBusinessPermission;
import com.gstdev.cloud.service.system.domain.generator.SysRAttributeMenuEmbeddablePK;
import com.gstdev.cloud.service.system.domain.generator.SysRRoleBusinessPermissionEmbeddablePK;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Set;

@Transactional
public interface SysRRoleBusinessPermissionRepository extends BaseRepository<SysRRoleBusinessPermission, SysRRoleBusinessPermissionEmbeddablePK> {


}


