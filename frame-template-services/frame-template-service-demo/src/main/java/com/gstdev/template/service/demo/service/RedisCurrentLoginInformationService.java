package com.gstdev.template.service.demo.service;

import com.gstdev.cloud.commons.web.Result;
import com.gstdev.template.common.redis.currentLoginInformation.CurrentLoginInformation;
import com.gstdev.template.common.redis.currentLoginInformation.RedisCurrentLoginInformationInput;

public interface RedisCurrentLoginInformationService {

  Result<Object> addByTokenCurrentLoginInformation(RedisCurrentLoginInformationInput redisCurrentLoginInformationInput);

  Result<Object> updateByTokenCurrentLoginInformation(RedisCurrentLoginInformationInput redisCurrentLoginInformationInput);

  Result<Object> deleteByTokenCurrentLoginInformation();

  Result<CurrentLoginInformation> getCurrentLoginInformation();
}
