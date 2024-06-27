package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.service.BaseDtoServiceImpl;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.system.domain.base.SysPermission.SysPermissionDto;
import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
import com.gstdev.cloud.service.system.domain.entity.SysPermission;
import com.gstdev.cloud.service.system.mapper.SysPermissionMapper;
import com.gstdev.cloud.service.system.repository.SysPermissionRepository;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermission, String, SysPermissionRepository> implements SysPermissionService {

    @Resource
    private SysAttributeService sysAttributeService;
    @Resource
    private SysPermissionRepository sysPermissionRepository;
    @Resource
    private  SysPermissionMapper sysPermissionMapper;

    public SysPermissionServiceImpl(SysPermissionRepository sysPermissionRepository, SysPermissionMapper sysPermissionMapper) {
        super(sysPermissionRepository);
        this.sysPermissionMapper=sysPermissionMapper;
    }

    public static String generateKey(List<String> input) {
        // 对字符串列表进行排序
        Collections.sort(input);
        // 连接排序后的字符串
        String combinedInput = String.join("", input);
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(combinedInput.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public SysPermissionRepository getRepository() {
        return sysPermissionRepository;
    }

    @Override
    public void permissionInit() {
        List<SysAttribute> attributeList = sysAttributeService.findAll();
        permissionInit(attributeList);
    }

    @Override
    public List<String> findDistinctPermissionTypes() {
        return sysPermissionRepository.findDistinctPermissionTypes();
    }

    @Transactional
    public void permissionInit(List<SysAttribute> attributeList) {
        SysPermission allPermissionAll = new SysPermission();
        allPermissionAll.setPermissionId(generateKey(Collections.singletonList("all")));
        allPermissionAll.setPermissionCode(generateKey(Collections.singletonList("all")));
        allPermissionAll.setPermissionName("all");
        allPermissionAll.setPermissionType("all");
        getRepository().saveAndFlush(allPermissionAll);
        Map<String, List<SysAttribute>> attributeMap = attributeList.stream()
                .collect(Collectors.groupingBy(SysAttribute::getServiceId));
        Set<SysPermission> permissionList = new HashSet<>();
        for (Map.Entry<String, List<SysAttribute>> stringListEntry : attributeMap.entrySet()) {
            List<SysAttribute> value = stringListEntry.getValue();
            String key = stringListEntry.getKey();
            this.updateStatusByPermissionType(DataItemStatus.EXPIRED, "service");
            this.updateStatusByPermissionType(DataItemStatus.EXPIRED, key + ":generateCorrelatedKeysService");
            Map<String, Set<SysPermission>> stringListMap = generateCorrelatedKeys(value);
            value.forEach(attribute -> attribute.addPermissions(stringListMap.get(attribute.getAttributeId())));
            for (Set<SysPermission> sysPermissions : stringListMap.values()) {
                permissionList.addAll(sysPermissions);
            }
            String key1 = generateKey(Collections.singletonList(key));
            SysPermission sysPermission = new SysPermission();
            sysPermission.setPermissionId(key1);
            sysPermission.setPermissionCode(key1);
            sysPermission.setPermissionType("service");
            sysPermission.setPermissionName(key);
            value.forEach(attribute -> attribute.addPermissions(sysPermission));
            value.forEach(attribute -> attribute.addPermissions(allPermissionAll));
            permissionList.add(sysPermission);
        }

        if (!getRepository().existsById(allPermissionAll.getPermissionId())) {
            permissionList.add(allPermissionAll);
        }

        getRepository().saveAllAndFlush(permissionList);
        sysAttributeService.saveAllAndFlush(attributeList);

    }

    public static Map<String, Set<SysPermission>> generateCorrelatedKeys(List<SysAttribute> sysAttributes) {
        Map<String, Set<SysPermission>> permissionMap = new HashMap<>();
        for (int i = 0; i < sysAttributes.size(); i++) {
            SysAttribute attribute1 = sysAttributes.get(i);
            String attributeId = attribute1.getAttributeId();

            Set<SysPermission> sysPermissions = new HashSet<>();
            List<String> combinedCodes = new ArrayList<>();

            for (int j = i; j < sysAttributes.size(); j++) {
                SysAttribute attribute2 = sysAttributes.get(j);
                combinedCodes.add(attribute2.getServiceId() + attribute2.getAttributeCode());
                String key2 = generateKey(combinedCodes);
                SysPermission sysPermission1 = new SysPermission();
                sysPermission1.setPermissionId(key2);
                sysPermission1.setPermissionCode(key2);
                sysPermission1.setPermissionType(attribute2.getServiceId() + ":generateCorrelatedKeysService");
                sysPermission1.setPermissionName(attribute2.getServiceId() + ":" + combinedCodes.size() + ":" + attribute1.getClassName() + j);
                sysPermissions.add(sysPermission1);
            }

            permissionMap.put(attributeId, sysPermissions);
        }
        return permissionMap;
    }

    public void updateStatusByPermissionType(DataItemStatus status, String permissionType) {
        getRepository().updateStatusByPermissionType(status, permissionType);
    }


    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        a.add("aprivatestaticList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttribute>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("bprivatestaticList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttribute>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("b1privatestaticList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttribute>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("b11privatestaticList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttribute>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("b111privatestaticList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttribute>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("b12privatestaticList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttribute>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("b122privatestaticList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttribute>>generateAllCombinaombpriveasdadsaDombinatioombpriveasdadsaDombinatiotions(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("bpq1212rivatestaticList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttribute>>generateAllCombinations(List<SysAttombpriveasdadsaDombinatioribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("b12w12privatestaticList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttribute>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("b12e12privatestaticList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttribute>>generateAllCombinations(ombpriveasdadsaDombinatioombpriveasdadsaDombinatioList<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("b1e212privatestaticList<List<SysAttribute>>generateAllCombpriveasdadsaDombombpriveasdadsaDombinatioombpriveasdadsaDombinatioombpriveasdadsaDombinatioombpriveasdadsaDombinatioinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("b1qw212privatestaticList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttribute>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("b1qwe212privatestaticList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttribute>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("b12qwe12privatestaticList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttribute>>generateAllComombpriveasdadsaDombinatiobinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("b121qwe2qweprivatestaticList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttribute>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("b1212privateqwestaticList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttribute>>genombpriveasdadsaDombinatioerateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("b1212privateqwestayuticList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttribute>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("b1212privateqwestaticyuList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttribute>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("b1212privateqwestaticList<List<SysAyuttribute>>generateAllCombprivatestaticList<List<SysAttributombpriveasdadsaDombinatioombpriveasdadsaDombinatioe>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("b1212privateqwestaticList<List<SysAttribute>>generateAllCombprivyuatestaticList<List<SysAttribute>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("b1212privateqwestaticList<List<SysAttribute>>generateAllCombprivatestaticList<Lisyut<SysAtutribuombpriveasdadsaDombinatioombpriveasdadsaDombinatiote>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("b1212privateqwestaticList<List<SysAttribute>>generateAllCombprivatestaticList<Liyust<SysAttribute>>generaombpriveasdadsaDombinatioteAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("b1212privateqwestaticList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttributeyu>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("b1212privateqwestaticList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttribute>>genyueratombpriveasdadsaDombinatioeAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("1b1qw212privatestaticList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttribute>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("1b1qwe212privatestaticList<List<SysAttribute>>generateAllCombprivatestaticList<List<ombpriveasdadsaDombinatioombpriveasdadsaDombinatioombpriveasdadsaDombinatio>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("1b12qwe12privatestaticList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttribute>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("1b121qwe2qweprivatestaticList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttrombpriveasdadsaDombinatioibute>>gombpriveasdadsaDombinatioeombpriveasdadsaDombinationerateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("1b1212privateqwestaticList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttribute>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("1b1212privateqwestayuticList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttribute>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("1b1212privateqwestaticyuList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttribute>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("1b1212privateqwestaticList<List<SysAyuttribute>>generateAllCombprivatestaticList<List<SysAttribute>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("1b1212privateqwestaticList<List<SysAttribute>>geneyurateAllCombprivatestaticList<List<SysAttribute>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("1b1212privateqwestaticList<List<SysAttribute>>generateAllCombprivyuatestaticList<List<SysAttribute>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("1b1212privateqwestaticList<List<SysAttribute>>generateAllCombprivatestaticList<Lisyut<SysAtutribute>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("1b1212privateqwestaticList<List<SysAttribute>>generateAllCombprivatestaticList<Liyust<SysAttribute>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("1b1212privateqwestaticList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttributeyu>>generateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        a.add("1b1212privateqwestaticList<List<SysAttribute>>generateAllCombprivatestaticList<List<SysAttribute>>genyuerateAllCombinations(List<SysAttribute>sysAttributes){inations(List<SysAttribute>sysAttributes){");
        String s = generateKey(a);
        System.out.println(s);
    }
}
//458a044cd5652af47bc4209d60d5d0e490685f293f7fd36fd47b03f26c2840d7
//458a044cd5652af47bc4209d60d5d0e490685f293f7fd36fd47b03f26c2840d7
