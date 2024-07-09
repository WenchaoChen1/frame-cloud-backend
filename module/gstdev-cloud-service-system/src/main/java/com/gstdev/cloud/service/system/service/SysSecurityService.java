package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.oauth2.core.definition.domain.FrameGrantedAuthority;
import com.gstdev.cloud.service.system.domain.entity.SysUser;
import com.gstdev.cloud.service.system.domain.pojo.sysSecurity.CurrentLoginInformation;

import java.util.Set;

public interface SysSecurityService {

    Set<FrameGrantedAuthority> getUserAuthoritiesPermissions(SysUser sysUser);

    CurrentLoginInformation getAccountCurrentLoginInformation(String accountId);

    CurrentLoginInformation updateAccountCurrentLoginInformation(String accountId);

}
