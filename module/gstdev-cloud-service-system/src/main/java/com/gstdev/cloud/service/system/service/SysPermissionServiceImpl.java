package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.service.BaseDtoServiceImpl;
import com.gstdev.cloud.service.system.mapper.RoleMapper;
import com.gstdev.cloud.service.system.mapper.SysPermissionMapper;
import com.gstdev.cloud.service.system.pojo.base.SysPermission.SysPermissionDto;
import com.gstdev.cloud.service.system.pojo.entity.SysAttribute;
import com.gstdev.cloud.service.system.pojo.entity.SysPermission;
import com.gstdev.cloud.service.system.repository.SysPermissionRepository;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
public class SysPermissionServiceImpl extends BaseDtoServiceImpl<SysPermission, String, SysPermissionRepository, SysPermissionMapper, SysPermissionDto> implements SysPermissionService {

    //    private  SysPermissionRepository sysPermissionRepository;
//    @Resource
//    private SysPermissionMapper sysPermissionMapper;

    @Resource
    private SysAttributeService sysAttributeService;

    public SysPermissionServiceImpl(SysPermissionRepository sysPermissionRepository, SysPermissionMapper sysPermissionMapper) {
        super(sysPermissionRepository, sysPermissionMapper);
    }

    public void permissionInit() {
        List<SysAttribute> attributeList = sysAttributeService.findAll();
        permissionInit(attributeList);
    }

    public void permissionInit(List<SysAttribute> attributeList) {
        this.updateStatusByPermissionType(DataItemStatus.EXPIRED, "generateCorrelatedKeysService");
        Map<String, List<SysAttribute>> attributeMap = attributeList.stream()
            .collect(Collectors.groupingBy(SysAttribute::getServiceId));
        List<SysPermission> permissionList = new ArrayList<>();

        for (List<SysAttribute> value : attributeMap.values()) {
            Map<String, Set<SysPermission>> stringListMap = generateCorrelatedKeys(value);
            value.forEach(attribute -> attribute.addPermissions(stringListMap.get(attribute.getAttributeId())));

            permissionList.addAll(stringListMap.values().stream()
                .flatMap(Set::stream)
                .collect(Collectors.toList()));
        }

        getRepository().saveAllAndFlush(permissionList);
        sysAttributeService.saveAllAndFlush(attributeList);
    }

    void updateStatusByPermissionType(DataItemStatus status, String permissionType) {
        getRepository().updateStatusByPermissionType(status, permissionType);
    }

    public static Map<String, Set<SysPermission>> generateCorrelatedKeys(List<SysAttribute> sysAttributes) {
        Map<String, Set<SysPermission>> permissionMap = new HashMap<>();
        for (int i = 0; i < sysAttributes.size(); i++) {
            SysAttribute attribute1 = sysAttributes.get(i);
            String attributeId = attribute1.getAttributeId();// Assuming SysAttribute has a method getId() to get its ID

            Set<SysPermission> sysPermissions = new HashSet<>();

            String key1 = generateKey(Collections.singletonList(attribute1.getServiceId() + attribute1.getAttributeCode()));
            SysPermission sysPermission = new SysPermission();
            sysPermission.setPermissionId(key1);
            sysPermission.setPermissionCode(key1);
            sysPermission.setPermissionType("generateCorrelatedKeysService");
            sysPermission.setPermissionName(attribute1.getServiceId() + ":" + attribute1.getAttributeCode());
            sysPermissions.add(sysPermission);

            for (int j = i + 1; j < sysAttributes.size(); j++) {
                SysAttribute attribute2 = sysAttributes.get(j);
                List<String> combinedCodes = new ArrayList<>();
                combinedCodes.add(attribute1.getServiceId() + attribute1.getAttributeCode());
                combinedCodes.add(attribute2.getServiceId() + attribute2.getAttributeCode());
                String key2 = generateKey(combinedCodes);
                SysPermission sysPermission1 = new SysPermission();
                sysPermission1.setPermissionId(key2);
                sysPermission1.setPermissionCode(key2);
                sysPermission1.setPermissionType("generateCorrelatedKeysService");
                sysPermission1.setPermissionName(attribute2.getServiceId() + ":" + attribute2.getAttributeCode());
                sysPermissions.add(sysPermission1);
                sysPermissions.add(sysPermission1);
            }

            permissionMap.put(attributeId, sysPermissions);
        }
        return permissionMap;
    }


    public static String generateKey(List<String> input) {
        Collections.sort(input); // 对字符串列表进行排序
        String combinedInput = String.join("", input); // 连接排序后的字符串
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(combinedInput.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
