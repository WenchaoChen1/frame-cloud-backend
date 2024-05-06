package com.gstdev.cloud.service.system.repository;

import com.gstdev.cloud.service.system.pojo.entity.SysUser;
import com.gstdev.cloud.data.core.repository.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<SysUser, String> {
    /**
     * 查询
     *
     * @param username
     * @return
     */
    SysUser findByUsername(String username);

    Optional<SysUser> findByEmail(String email);

    Optional<SysUser> findByEmailAndDeletedFalse(String email);
}