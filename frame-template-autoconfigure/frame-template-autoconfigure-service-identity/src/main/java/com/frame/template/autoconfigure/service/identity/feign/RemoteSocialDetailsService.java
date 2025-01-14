/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2020-2030 郑庚伟 ZHENGGENGWEI (码匠君), <herodotus@aliyun.com> Licensed under the AGPL License
 *
 * This file is part of Dante Cloud.
 *
 * Dante Cloud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Dante Cloud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.herodotus.cn>.
 */

package com.frame.template.autoconfigure.service.identity.feign;

import com.frame.template.common.constant.ServiceConstants;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.base.definition.domain.oauth2.AccessPrincipal;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.springframework.openfeign.annotation.Inner;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Description: SysSocialUser Feign Service </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/5/23 10:11
 */
@FeignClient(contextId = "remoteSocialDetailsService", value = ServiceConstants.SERVICE_NAME_SYSTEM)
public interface RemoteSocialDetailsService {

    @Inner
    @RequestMapping("/v1/security/social/sign-in/{source}")
    Result<DefaultSecurityUser> findUserDetailsBySocial(@PathVariable("source") String source, @SpringQueryMap AccessPrincipal accessPrincipal);
}
