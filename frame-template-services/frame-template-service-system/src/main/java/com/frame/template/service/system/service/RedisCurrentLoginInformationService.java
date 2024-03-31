package com.frame.template.service.system.service;

import com.gstdev.cloud.commons.ass.definition.domain.Result;
import com.frame.template.common.redis.currentLoginInformation.CurrentLoginInformation;
import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformationInput;

public interface RedisCurrentLoginInformationService {

  Result<Object> addByTokenCurrentLoginInformation(RedisCurrentLoginInformationInput redisCurrentLoginInformationInput);

  Result<Object> updateByTokenCurrentLoginInformation(RedisCurrentLoginInformationInput redisCurrentLoginInformationInput);

  Result<Object> deleteByTokenCurrentLoginInformation();

  Result<CurrentLoginInformation> getCurrentLoginInformation();
}
