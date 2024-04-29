package com.frame.template.service.system.pojo.listener;

import com.frame.template.service.system.pojo.entity.SysAttribute;
import com.frame.template.service.system.pojo.event.SysAttributeChangeEvent;
import com.gstdev.cloud.rest.core.definition.context.AbstractApplicationContextAware;
import jakarta.persistence.PostUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description: SysAttribute实体数据变更监听 </p>
 *
 */
public class SysAttributeEntityListener extends AbstractApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(SysAttributeEntityListener.class);

    @PostUpdate
    protected void postUpdate(SysAttribute entity) {
        log.debug("[Herodotus] |- [1] SysAttribute entity @PostUpdate activated, value is : [{}]. Trigger SysAttribute change event.", entity.toString());
        publishEvent(new SysAttributeChangeEvent(entity));
    }
}
