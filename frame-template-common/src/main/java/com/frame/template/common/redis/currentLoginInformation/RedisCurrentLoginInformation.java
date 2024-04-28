package com.frame.template.common.redis.currentLoginInformation;

import com.frame.template.common.base.BaseRedisCurrentLoginInformation;
import com.gstdev.cloud.base.definition.domain.Result;

public interface RedisCurrentLoginInformation extends BaseRedisCurrentLoginInformation {
    Result<Object> addByTokenCurrentLoginInformation(CurrentLoginInformation currentLoginInformation);

    Result<Object> updateByTokenCurrentLoginInformation(CurrentLoginInformation currentLogIninformation);

    Result<Object> deleteByTokenCurrentLoginInformation();

    CurrentLoginInformation getCurrentLoginInformation();

    String getCurrentLoginAccountId();

    String getCurrentLoginTenantId();
}
