package com.frame.template.service.system.repository;

import com.frame.template.service.system.pojo.entity.User;
import com.gstdev.cloud.data.core.repository.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, String> {
    /**
     * 查询
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndDeletedFalse(String email);
}
