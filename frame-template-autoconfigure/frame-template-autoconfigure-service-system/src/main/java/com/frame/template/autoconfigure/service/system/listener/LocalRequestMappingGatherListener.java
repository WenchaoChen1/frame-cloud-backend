package com.frame.template.autoconfigure.service.system.listener;

import com.frame.template.autoconfigure.service.system.processor.RequestMappingStoreProcessor;
import com.gstdev.cloud.message.core.logic.domain.RequestMapping;
import com.gstdev.cloud.message.core.logic.event.RequestMappingGatherEvent;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>Description: 本地RequestMapping收集监听 </p>
 * <p>
 * 主要在单体式架构，以及 UUA 服务自身使用
 */
@Component
public class LocalRequestMappingGatherListener implements ApplicationListener<RequestMappingGatherEvent> {

    private static final Logger log = LoggerFactory.getLogger(LocalRequestMappingGatherListener.class);

    private final RequestMappingStoreProcessor requestMappingStoreProcessor;

    @Autowired
    public LocalRequestMappingGatherListener(RequestMappingStoreProcessor requestMappingStoreProcessor) {
        this.requestMappingStoreProcessor = requestMappingStoreProcessor;
    }

    @Override
    public void onApplicationEvent(RequestMappingGatherEvent event) {

        log.info("[Gstdev Cloud] |- Request mapping gather LOCAL listener, response event!");

        List<RequestMapping> requestMappings = event.getData();
        if (CollectionUtils.isNotEmpty(requestMappings)) {
            requestMappingStoreProcessor.postProcess(requestMappings);
        }
    }
}
