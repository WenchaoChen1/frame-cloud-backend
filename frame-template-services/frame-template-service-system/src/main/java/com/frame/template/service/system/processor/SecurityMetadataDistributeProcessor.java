package com.frame.template.service.system.processor;


import com.frame.template.service.system.service.SysAttributeService;
import com.gstdev.cloud.base.core.exception.transaction.TransactionalRollbackException;
import com.gstdev.cloud.message.core.definition.strategy.StrategyEventManager;
import com.gstdev.cloud.message.core.logic.domain.RequestMapping;
import com.gstdev.cloud.oauth2.core.definition.domain.SecurityAttribute;
import com.gstdev.cloud.oauth2.resource.server.processor.SecurityMetadataSourceAnalyzer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>Description: SecurityMetadata数据处理器 </p>
 */
@Component
public class SecurityMetadataDistributeProcessor implements StrategyEventManager<List<SecurityAttribute>> {

    private static final Logger log = LoggerFactory.getLogger(SecurityMetadataDistributeProcessor.class);

    //    private final Converter<List<SysInterface>, List<SysAttribute>> toSysAttributes;
//    private final Converter<SysAttribute, SecurityAttribute> toSecurityAttribute;

    private final SysAttributeService sysAttributeService;
    //    private final SysInterfaceService sysInterfaceService;
    private final SecurityMetadataSourceAnalyzer securityMetadataSourceAnalyzer;

    public SecurityMetadataDistributeProcessor(SysAttributeService sysAttributeService, SecurityMetadataSourceAnalyzer securityMetadataSourceAnalyzer) {
        this.sysAttributeService = sysAttributeService;
//        this.sysInterfaceService = sysInterfaceService;
        this.securityMetadataSourceAnalyzer = securityMetadataSourceAnalyzer;
//        this.toSysAttributes = new SysInterfacesToSysAttributesConverter();
//        this.toSecurityAttribute = new SysAttributeToSecurityAttributeConverter();
    }

    @Override
    public void postLocalProcess(List<SecurityAttribute> data) {
        securityMetadataSourceAnalyzer.processSecurityAttribute(data);
    }

    @Override
    public void postRemoteProcess(String data, String originService, String destinationService) {
        System.out.println("11111111111111111111");
//        publishEvent(new RemoteSecurityMetadataSyncEvent(data, originService, destinationService));
    }

    /**
     * 将SysAuthority表中存在，但是SysSecurityAttribute中不存在的数据同步至SysSecurityAttribute，保证两侧数据一致
     */
    @Transactional(rollbackFor = TransactionalRollbackException.class)
    public void postRequestMappings(List<RequestMapping> requestMappings) {
        System.out.println("2222222222222222");
        requestMappings.forEach(requestMapping -> {
            System.out.println(requestMapping);
        });
//        List<SysAttribute> elements = toSysAttributes.convert(sysInterfaces);
//        SysAttribute sysAttribute = new SysAttribute();
//        sysAttribute.setAttributeId(requestMapping.getMappingId());
//        sysAttribute.setAttributeCode(requestMapping.getMappingCode());
//        sysAttribute.setRequestMethod(requestMapping.getRequestMethod());
//        sysAttribute.setServiceId(requestMapping.getServiceId());
//        sysAttribute.setClassName(requestMapping.getClassName());
//        sysAttribute.setMethodName(requestMapping.getMethodName());
//        sysAttribute.setUrl(requestMapping.getUrl());
//        sysAttribute.setDescription(requestMapping.getDescription());
//        List<SysAttribute> result = sysAttributeService.saveAllAndFlush(elements);
//        List<SysInterface> storedInterfaces = sysInterfaceService.storeRequestMappings(requestMappings);
//        if (CollectionUtils.isNotEmpty(storedInterfaces)) {
//            log.debug("[Gstdev Cloud] |- [5] Request mapping store success, start to merge security metadata!");
//
//            List<SysInterface> sysInterfaces = sysInterfaceService.findAllocatable();
//
//            if (CollectionUtils.isNotEmpty(sysInterfaces)) {
//                List<SysAttribute> elements = toSysAttributes.convert(sysInterfaces);
//                List<SysAttribute> result = sysAttributeService.saveAllAndFlush(elements);
//                if (CollectionUtils.isNotEmpty(result)) {
//                    log.debug("[Gstdev Cloud] |- Merge security attribute SUCCESS and FINISHED!");
//                } else {
//                    log.error("[Gstdev Cloud] |- Merge Security attribute failed!, Please Check!");
//                }
//            } else {
//                log.debug("[Gstdev Cloud] |- No security attribute requires merge, SKIP!");
//            }

//            distributeServiceSecurityAttributes(storedInterfaces);
//        }
    }
//
//    private void distributeServiceSecurityAttributes(List<SysInterface> storedInterfaces) {
//        storedInterfaces.stream().findAny().ifPresent(item -> {
//            String serviceId = item.getServiceId();
//            List<SysAttribute> sysAttributes = sysAttributeService.findAllByServiceId(item.getServiceId());
//            if (CollectionUtils.isNotEmpty(sysAttributes)) {
//                List<SecurityAttribute> securityAttributes = sysAttributes.stream().map(toSecurityAttribute::convert).toList();
//                log.debug("[Gstdev Cloud] |- [6] Synchronization permissions to service [{}]", serviceId);
//                this.postProcess(serviceId, securityAttributes);
//            }
//        });
//    }
//
//    public void distributeChangedSecurityAttribute(SysAttribute sysAttribute) {
//        SecurityAttribute securityAttribute = toSecurityAttribute.convert(sysAttribute);
//        postProcess(securityAttribute.getServiceId(), ImmutableList.of(securityAttribute));
//    }
}
