package com.frame.template.autoconfigure.service.system.listener;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.message.core.logic.domain.UserStatus;
import com.gstdev.cloud.message.core.logic.event.ChangeUserStatusEvent;
import com.gstdev.cloud.service.system.service.SysUserService;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * <p>Description: 本地用户状态变更监听 </p>
 */
@Component
public class LocalChangeUserStatusListener implements ApplicationListener<ChangeUserStatusEvent> {

    private static final Logger log = LoggerFactory.getLogger(LocalChangeUserStatusListener.class);
    private final SysUserService sysUserService;

    public LocalChangeUserStatusListener(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @Override
    public void onApplicationEvent(ChangeUserStatusEvent event) {

        log.info("[Gstdev Cloud] |- Change user status gather LOCAL listener, response event!");

        UserStatus userStatus = event.getData();
        if (ObjectUtils.isNotEmpty(userStatus)) {
            DataItemStatus dataItemStatus = DataItemStatus.valueOf(userStatus.getStatus());
            if (ObjectUtils.isNotEmpty(dataItemStatus)) {
                sysUserService.changeStatus(userStatus.getUserId(), dataItemStatus);
            }
        }
    }
}
