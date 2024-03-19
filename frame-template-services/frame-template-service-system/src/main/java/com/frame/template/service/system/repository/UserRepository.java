package com.frame.template.service.system.repository;

import com.frame.template.common.base.BaseRepository;
import com.frame.template.service.system.pojo.domain.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
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
