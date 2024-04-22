package com.frame.template.service.system.repository;

import com.frame.template.service.system.pojo.domain.User;
import com.gstdev.cloud.data.core.repository.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

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
