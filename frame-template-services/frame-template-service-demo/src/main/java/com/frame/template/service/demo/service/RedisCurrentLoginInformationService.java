package com.frame.template.service.demo.service;

import com.frame.template.common.redis.currentLoginInformation.CurrentLoginInformation;
import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformationInput;
import com.gstdev.cloud.base.definition.domain.Result;

public interface RedisCurrentLoginInformationService {

    Result<Object> addByTokenCurrentLoginInformation(RedisCurrentLoginInformationInput redisCurrentLoginInformationInput);

    Result<Object> updateByTokenCurrentLoginInformation(RedisCurrentLoginInformationInput redisCurrentLoginInformationInput);

    Result<Object> deleteByTokenCurrentLoginInformation();

    Result<CurrentLoginInformation> getCurrentLoginInformation();
}
