package com.frame.template.service.system.converter;

import com.frame.template.service.system.pojo.entity.SysAttribute;
import com.frame.template.service.system.pojo.entity.SysInterface;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Description: List<SysInterface> 转 List<SysAttribute> 转换器 </p>
 *
 */
public class SysInterfacesToSysAttributesConverter implements Converter<List<SysInterface>, List<SysAttribute>> {
    @Override
    public List<SysAttribute> convert(List<SysInterface> sysInterfaces) {
        if (CollectionUtils.isNotEmpty(sysInterfaces)) {
            return sysInterfaces.stream().map(this::convert).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    // TODO
    private SysAttribute convert(SysInterface sysInterface) {
        SysAttribute sysAttribute = new SysAttribute();
        sysAttribute.setAttributeId(sysInterface.getInterfaceId());
        sysAttribute.setAttributeCode(sysInterface.getInterfaceCode());
        sysAttribute.setRequestMethod(sysInterface.getRequestMethod());
        sysAttribute.setServiceId(sysInterface.getServiceId());
        sysAttribute.setClassName(sysInterface.getClassName());
        sysAttribute.setMethodName(sysInterface.getMethodName());
        sysAttribute.setUrl(sysInterface.getUrl());
//        sysAttribute.setStatus(sysInterface.getStatus());
//        sysAttribute.setReserved(sysInterface.getReserved());
        sysAttribute.setDescription(sysInterface.getDescription());
//        sysAttribute.setReversion(sysInterface.getReversion());
//        sysAttribute.setCreateTime(sysInterface.getCreateTime());
//        sysAttribute.setUpdateTime(sysInterface.getUpdateTime());
//        sysAttribute.setRanking(sysInterface.getRanking());
        return sysAttribute;
    }

}
