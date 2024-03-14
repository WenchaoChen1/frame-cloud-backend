package com.gstdev.template.common.base;

import com.gstdev.template.common.redis.currentLoginInformation.CurrentLoginInformation;

public interface BaseRedisCurrentLoginInformation {

  String getCurrentLoginAccountId();

  String getCurrentLoginTenantId();

  CurrentLoginInformation getCurrentLoginInformation();
}
