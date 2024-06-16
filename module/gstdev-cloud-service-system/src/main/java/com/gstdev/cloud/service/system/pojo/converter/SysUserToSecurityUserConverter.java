package com.gstdev.cloud.service.system.pojo.converter;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.oauth2.core.definition.domain.FrameGrantedAuthority;
import com.gstdev.cloud.oauth2.core.utils.SecurityUtils;
import com.gstdev.cloud.service.system.enums.AccountTypeConstants;
import com.gstdev.cloud.service.system.pojo.entity.SysAccount;
import com.gstdev.cloud.service.system.pojo.entity.SysPermission;
import com.gstdev.cloud.service.system.pojo.entity.SysRole;
import com.gstdev.cloud.service.system.pojo.entity.SysUser;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>Description: SysUser 转 SecurityUser 转换器 </p>
 *
 */
public class SysUserToSecurityUserConverter implements Converter<SysUser, DefaultSecurityUser> {
    @Override
    public DefaultSecurityUser convert(SysUser sysUser) {

        Set<FrameGrantedAuthority> authorities = new HashSet<>();

        Set<String> roles = new HashSet<>();

        List<SysRole> accountRoles = sysUser.getAccount().stream()
            .flatMap(account -> account.getRoles().stream())
            .collect(Collectors.toList());
        for (SysAccount sysAccount : sysUser.getAccount()) {
            if (sysAccount.getType().equals(AccountTypeConstants.SUPER.getCode())){
                authorities.add(new FrameGrantedAuthority("all"));
                authorities.add(new FrameGrantedAuthority("5ef5ef0364b6939c4ca61f34b393f7b368d1be8619647aaf83d5b395919ab629"));
            }
        }

        for (SysRole sysRole : accountRoles) {
            roles.add(sysRole.getCode());
            authorities.add(new FrameGrantedAuthority(SecurityUtils.wellFormRolePrefix(sysRole.getCode())));
            Set<SysPermission> sysPermissions = sysRole.getPermissions();
            if (CollectionUtils.isNotEmpty(sysPermissions)) {
                sysPermissions.forEach(sysAuthority -> authorities.add(new FrameGrantedAuthority((sysAuthority.getPermissionCode()))));
            }
        }


//        String employeeId = ObjectUtils.isNotEmpty(sysUser.getEmployee()) ? sysUser.getEmployee().getEmployeeId() : null;

        return new DefaultSecurityUser(sysUser.getId(), sysUser.getUsername(), sysUser.getPassword(),
                isEnabled(sysUser),
                isAccountNonExpired(sysUser),
                isCredentialsNonExpired(sysUser),
                isNonLocked(sysUser),
                authorities, roles, null, sysUser.getAvatar());
    }

    private boolean isEnabled(SysUser sysUser) {
        return sysUser.getStatus() != DataItemStatus.FORBIDDEN;
    }

    private boolean isNonLocked(SysUser sysUser) {
        return !(sysUser.getStatus() == DataItemStatus.LOCKING);
    }

    private boolean isNonExpired(Instant localDateTime) {
        if (ObjectUtils.isEmpty(localDateTime)) {
            return true;
        } else {
            return localDateTime.isAfter(localDateTime);
        }
    }

    private boolean isAccountNonExpired(SysUser sysUser) {
        if (sysUser.getStatus() == DataItemStatus.EXPIRED) {
            return false;
        }

        return isNonExpired(sysUser.getAccountExpireAt());
    }

    private boolean isCredentialsNonExpired(SysUser sysUser) {
        return isNonExpired(sysUser.getCredentialsExpireAt());
    }
}
