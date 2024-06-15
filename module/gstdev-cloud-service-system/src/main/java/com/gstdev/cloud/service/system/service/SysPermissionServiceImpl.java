package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.service.BaseDtoServiceImpl;
import com.gstdev.cloud.service.system.mapper.SysPermissionMapper;
import com.gstdev.cloud.service.system.pojo.base.SysPermission.SysPermissionDto;
import com.gstdev.cloud.service.system.pojo.entity.SysAttribute;
import com.gstdev.cloud.service.system.pojo.entity.SysPermission;
import com.gstdev.cloud.service.system.repository.SysPermissionRepository;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
public class SysPermissionServiceImpl extends BaseDtoServiceImpl<SysPermission, String, SysPermissionRepository, SysPermissionMapper, SysPermissionDto> implements SysPermissionService {

    @Resource
    private SysAttributeService sysAttributeService;
    @Resource
    private SysPermissionRepository sysPermissionRepository;

    public SysPermissionServiceImpl(SysPermissionRepository sysPermissionRepository, SysPermissionMapper sysPermissionMapper) {
        super(sysPermissionRepository, sysPermissionMapper);
    }

    public SysPermissionRepository getRepository() {
        return sysPermissionRepository;
    }

    public void permissionInit() {
        List<SysAttribute> attributeList = sysAttributeService.findAll();
        permissionInit(attributeList);
    }

    @Override
    public List<String> findDistinctPermissionTypes() {
        return sysPermissionRepository.findDistinctPermissionTypes();
    }

    public void permissionInit(List<SysAttribute> attributeList) {
        this.updateStatusByPermissionType(DataItemStatus.EXPIRED, "generateCorrelatedKeysService");
        Map<String, List<SysAttribute>> attributeMap = attributeList.stream()
            .collect(Collectors.groupingBy(SysAttribute::getServiceId));
        List<SysPermission> permissionList = new ArrayList<>();
        for (Map.Entry<String, List<SysAttribute>> stringListEntry : attributeMap.entrySet()) {
            List<SysAttribute> value = stringListEntry.getValue();
            String key = stringListEntry.getKey();
//            Map<String, Set<SysPermission>> stringListMap = generateCorrelatedKeys(value);
//            value.forEach(attribute -> attribute.addPermissions(stringListMap.get(attribute.getAttributeId())));
//            permissionList.addAll(stringListMap.values().stream()
//                .flatMap(Set::stream)
//                .collect(Collectors.toList()));

            String key1 = generateKey(Collections.singletonList(key));
            SysPermission sysPermission = new SysPermission();
            sysPermission.setPermissionId(key1);
            sysPermission.setPermissionCode(key1);
            sysPermission.setPermissionType("service");
            sysPermission.setPermissionName(key);
            value.forEach(attribute -> attribute.addPermissions(sysPermission));
            permissionList.add(sysPermission);
        }

        SysPermission allPermissionAll = new SysPermission();
        allPermissionAll.setPermissionId(generateKey(Collections.singletonList("all")));
        allPermissionAll.setPermissionCode(generateKey(Collections.singletonList("all")));
        allPermissionAll.setPermissionName("all");
        allPermissionAll.setPermissionType("all");
        if(!getRepository().existsById(allPermissionAll.getPermissionId())){
            permissionList.add(allPermissionAll);
        }
//        // 去重处理，避免重复主键问题
//        Map<String, SysPermission> uniquePermissions = permissionList.stream()
//            .collect(Collectors.toMap(SysPermission::getPermissionId, Function.identity(), (existing, replacement) -> existing));
//        getRepository().saveAll(new ArrayList<>(uniquePermissions.values()));
        getRepository().saveAllAndFlush(permissionList);
        sysAttributeService.saveAllAndFlush(attributeList);
    }

    public void updateStatusByPermissionType(DataItemStatus status, String permissionType) {
        getRepository().updateStatusByPermissionType(status, permissionType);
    }

//    public static Map<String, Set<SysPermission>> generateCorrelatedKeys(List<SysAttribute> sysAttributes) {
//        Map<String, Set<SysPermission>> permissionMap = new HashMap<>();
//        for (int i = 0; i < sysAttributes.size(); i++) {
//            SysAttribute attribute1 = sysAttributes.get(i);
//            String attributeId = attribute1.getAttributeId();// Assuming SysAttribute has a method getId() to get its ID
//
//            Set<SysPermission> sysPermissions = new HashSet<>();
//
//            String key1 = generateKey(Collections.singletonList(attribute1.getServiceId() + attribute1.getAttributeCode()));
//            SysPermission sysPermission = new SysPermission();
//            sysPermission.setPermissionId(key1);
//            sysPermission.setPermissionCode(key1);
//            sysPermission.setPermissionType("generateCorrelatedKeysService");
//            sysPermission.setPermissionName(attribute1.getServiceId() + ":" + attribute1.getAttributeCode());
//            sysPermissions.add(sysPermission);
//
//            for (int j = i + 1; j < sysAttributes.size(); j++) {
//                SysAttribute attribute2 = sysAttributes.get(j);
//                List<String> combinedCodes = new ArrayList<>();
//                combinedCodes.add(attribute1.getServiceId() + attribute1.getAttributeCode());
//                combinedCodes.add(attribute2.getServiceId() + attribute2.getAttributeCode());
//                String key2 = generateKey(combinedCodes);
//                SysPermission sysPermission1 = new SysPermission();
//                sysPermission1.setPermissionId(key2);
//                sysPermission1.setPermissionCode(key2);
//                sysPermission1.setPermissionType("generateCorrelatedKeysService");
//                sysPermission1.setPermissionName(attribute2.getServiceId() + ":" + attribute2.getAttributeCode());
//                sysPermissions.add(sysPermission1);
//                sysPermissions.add(sysPermission1);
//            }
//
//            permissionMap.put(attributeId, sysPermissions);
//        }
//        return permissionMap;
//    }


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

//    public static Map<String, Set<SysPermission>> generateCorrelatedKeys(List<SysAttribute> sysAttributes) {
//        Map<String, Set<SysPermission>> permissionMap = new HashMap<>();
//
//        // Generate all possible non-empty subsets of sysAttributes
//        List<List<SysAttribute>> allCombinations = generateAllCombinations(sysAttributes);
//
//        for (SysAttribute attribute : sysAttributes) {
//            String attributeId = attribute.getAttributeId();
//            Set<SysPermission> sysPermissions = new HashSet<>();
//
//            for (List<SysAttribute> combination : allCombinations) {
//                if (combination.contains(attribute)) {
//                    List<String> combinedCodes = new ArrayList<>();
//                    StringBuilder permissionName = new StringBuilder();
//
//                    for (SysAttribute attr : combination) {
//                        combinedCodes.add(attr.getServiceId() + attr.getAttributeCode());
//                        if (permissionName.length() > 0) {
//                            permissionName.append(",");
//                        }
//                        permissionName.append(attr.getServiceId()).append(":").append(attr.getAttributeCode());
//                    }
//
//                    String key =String.join("-", combinedCodes);
//                    SysPermission sysPermission = new SysPermission();
//                    sysPermission.setPermissionId(key);
//                    sysPermission.setPermissionCode(key);
//                    sysPermission.setPermissionType("generateCorrelatedKeysService");
//                    sysPermission.setPermissionName(permissionName.toString());
//                    sysPermissions.add(sysPermission);
//                }
//            }
//
//            permissionMap.put(attributeId, sysPermissions);
//        }
//
//        return permissionMap;
//    }


//    private static List<List<SysAttribute>> generateAllCombinations(List<SysAttribute> sysAttributes) {
//        List<List<SysAttribute>> allCombinations = new ArrayList<>();
//        int n = sysAttributes.size();
//
//        // Generate all possible non-empty subsets
//        for (int i = 1; i < (1 << n); i++) {
//            List<SysAttribute> combination = new ArrayList<>();
//            for (int j = 0; j < n; j++) {
//                if ((i & (1 << j)) != 0) {
//                    combination.add(sysAttributes.get(j));
//                }
//            }
//            allCombinations.add(combination);
//        }
//
//        return allCombinations;
//    }

//    public static void main(String[] args) {
//        SysAttribute attribute = new SysAttribute();
//        attribute.setAttributeId("1");
//        attribute.setServiceId("1");
//        attribute.setAttributeCode("1");
//        SysAttribute attribute2 = new SysAttribute();
//        attribute2.setAttributeId("2");
//        attribute2.setServiceId("2");
//        attribute2.setAttributeCode("2");
//        SysAttribute attribute3 = new SysAttribute();
//        attribute3.setAttributeId("3");
//        attribute3.setServiceId("3");
//        attribute3.setAttributeCode("3");
//        // Example usage
//        List<SysAttribute> sysAttributes = Arrays.asList(
//            attribute,
//            attribute2,
//            attribute3
//        );
//
//        Map<String, Set<SysPermission>> result = generateCorrelatedKeys(sysAttributes);
//        result.forEach((key, value) -> {
//            System.out.println("Attribute ID: " + key);
//            value.forEach(permission -> System.out.println("  Permission: " + permission.getPermissionName()));
//        });
//    }
}
