package com.gstdev.template.service.system.repository;

import com.gstdev.template.common.base.BaseRepository;
import com.gstdev.template.service.system.pojo.domain.Account;

import java.util.List;

public interface AccountRepository extends BaseRepository<Account> {
  /**
   * 通过登录名查账号和用户信息
   *
   * @param identity
   * @return
   */
  Account findByIdentity(String identity);

  /**
   * 查询
   *
   * @param userId
   * @param tenantId
   * @return
   */

  Account findByUserIdAndTenantId(String userId, String tenantId);

  Account findAccountByType(String userId);

  List<Account> findAllByUserId(String userId);

  List<Account> findAllByTenantId(String tenantId);
}
