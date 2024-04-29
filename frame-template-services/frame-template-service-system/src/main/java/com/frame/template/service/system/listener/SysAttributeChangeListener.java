package com.frame.template.service.system.listener;

import com.frame.template.service.system.pojo.entity.SysAttribute;
import com.frame.template.service.system.pojo.event.SysAttributeChangeEvent;
import com.frame.template.service.system.processor.SecurityMetadataDistributeProcessor;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * <p>Description: SysSecurityAttribute变更事件监听 </p>
 *
 */
@Component
public class SysAttributeChangeListener implements ApplicationListener<SysAttributeChangeEvent> {

    private static final Logger log = LoggerFactory.getLogger(SysAttributeChangeListener.class);

    private final SecurityMetadataDistributeProcessor securityMetadataDistributeProcessor;

    public SysAttributeChangeListener(SecurityMetadataDistributeProcessor securityMetadataDistributeProcessor) {
        this.securityMetadataDistributeProcessor = securityMetadataDistributeProcessor;
    }

    @Override
    public void onApplicationEvent(SysAttributeChangeEvent event) {

        log.debug("[Gstdev Cloud] |- SysAttribute Change Listener, response event!");

        SysAttribute sysAttribute = event.getData();
        if (ObjectUtils.isNotEmpty(sysAttribute)) {
            log.debug("[Gstdev Cloud] |- Got SysAttribute, start to process SysAttribute change.");
            securityMetadataDistributeProcessor.distributeChangedSecurityAttribute(sysAttribute);
        }
    }
}
