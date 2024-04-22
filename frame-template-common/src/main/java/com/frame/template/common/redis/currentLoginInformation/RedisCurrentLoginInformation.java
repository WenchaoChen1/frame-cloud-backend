package com.frame.template.common.redis.currentLoginInformation;

import com.gstdev.cloud.base.definition.domain.Result;
import com.frame.template.common.base.BaseRedisCurrentLoginInformation;

public interface RedisCurrentLoginInformation extends BaseRedisCurrentLoginInformation {
  Result<Object> addByTokenCurrentLoginInformation(CurrentLoginInformation currentLoginInformation);

  Result<Object> updateByTokenCurrentLoginInformation(CurrentLoginInformation currentLogIninformation);

  Result<Object> deleteByTokenCurrentLoginInformation();

  CurrentLoginInformation getCurrentLoginInformation();

  String getCurrentLoginAccountId();

  String getCurrentLoginTenantId();
}
