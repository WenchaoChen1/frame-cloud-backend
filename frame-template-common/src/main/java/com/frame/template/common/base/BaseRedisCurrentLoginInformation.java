package com.frame.template.common.base;

import com.frame.template.common.redis.currentLoginInformation.CurrentLoginInformation;

public interface BaseRedisCurrentLoginInformation {

  String getCurrentLoginAccountId();

  String getCurrentLoginTenantId();

  CurrentLoginInformation getCurrentLoginInformation();
}
