package com.frame.template.service.system.controller;

import com.gstdev.cloud.base.core.utils.SecureUtil;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.oauth2.core.utils.SecurityUtils;
import com.gstdev.cloud.service.system.domain.pojo.sysPermission.PermissionManageQO;
import com.stripe.exception.StripeException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/aaaaaaaat")
public class StripeController {
    @Resource
    Environment environment;
    @PostConstruct
    public void postConstruct() {
        Environment environment1 = environment;
        String property = environment.getProperty("gstdev.cloud.swagger.enabled");
        System.out.println(property);
        System.out.println("aaaaaaaaa11112");
    }
    @GetMapping("/a")
    @Operation(summary = "a")
    public void a() {
        System.out.println("aaaaaaaaa1111");
        SecurityContext securityContext = SecurityUtils.getSecurityContext();
        System.out.println(SecurityUtils.getTokenValue());
    }
    @PostMapping("/{aaa}/get-permission-manage-page")
    @Operation(summary = "get-permission-manage-page")
    public Result<Map<String, Object>> getPermissionManagePage(@RequestBody PermissionManageQO permissionManageQO) {
        System.out.println("aaaaaaaaa1111");
        SecurityContext securityContext = SecurityUtils.getSecurityContext();
        System.out.println(SecurityUtils.getTokenValue());
        return null;
    }

    public static String generateKey(List<String> input) {
// 对字符串列表进行排序
        Collections.sort(input);

// 连接排序后的字符串
        String combinedInput = String.join("", input);
//        System.out.println(combinedInput);
        return SecureUtil.md5(combinedInput);
    }
//    [0]
//            [0, 2]
//            [0, 3]
//            [0, 1, 2]
//            [0, 1, 2, 3]
//            [1]
//            [1, 2]
//            [1, 3]
//            [1, 2, 3]
//            [2]
//            [2, 3]
public static void generateCombinations(List<String> input, List<String> tempCombination, int start) {
    for (int i = start; i < input.size(); i++) {
        tempCombination.add(input.get(i));
        System.out.println(tempCombination);
        generateCombinations(input, tempCombination, i + 1);
        tempCombination.remove(tempCombination.size() - 1);
    }
}


    public static List<String> printCombinations(List<String> array, int start, ArrayList<String> prefix) {
        ArrayList<String> objects = new ArrayList<>();
        objects.add(generateKey(prefix));
        String s = generateKey(prefix);
        System.out.println("aaaaaaaaaaaa"+prefix);
        for (int i = start; i < array.size(); i++) {
            ArrayList<String> newPrefix = new ArrayList<>(prefix);
            newPrefix.add(array.get(i));
            objects.addAll(printCombinations(array, i + 1, newPrefix));
        }
        return objects;
    }


    public static void main(String[] args) throws StripeException {
        List<String> combinedCodes = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            combinedCodes.add(String.valueOf(i));
        }
        List<String> strings = printCombinations(combinedCodes, 0, new ArrayList<>());
        System.out.println(strings);
//        List<String> combinedCodes = new ArrayList<>();
//        List<String> tempCombination1 = new ArrayList<>();
//        int a = 0;
//        for (int i = 0; i < 4; i++) {
//            List<String> tempCombination = new ArrayList<>();
//            System.out.println(i);
//            for (int j = 0; j < 4; j++) {
//                tempCombination.add(String.valueOf(j));
//                System.out.println(tempCombination);
//                a++;
//            }
//        }
//        System.out.println(a);
//List<String> combinedCodes = new ArrayList<>();
//combinedCodes.add("gstdev-systemput:v1:security:reset-password:originalPassword:newPassword");
//combinedCodes.add("gstdev-systemput:v1:security:update-account-current-login-information");
////combinedCodes.add("gstdev-systemget:v1:account:get-account-settings-detail");
////combinedCodes.add("gstdev-systemget:v1:user:get-user-settings-detail");
////combinedCodes.add("gstdev-systemget:v1:system:constant:enums");
////combinedCodes.add("gstdev-systempost:v1:account:update-account-settings-detail");
//String key2 = generateKey(combinedCodes);
//System.out.println(key2);
//Stripe.apiKey = "sk_test_51PNQG8Hsb9kAuba1PyOGdmhpyuR17Y0Gqq3OrjWzYxQSeFrfZyVO33akMLPZMA9PmQhsHSkyHcN4PSE3qZwFAgF700dRCVuq76";
//
//SessionCreateParams params =
//SessionCreateParams.builder()
//.setSuccessUrl("https://example.com/success")
//.addLineItem(
//SessionCreateParams.LineItem.builder()
//.setPrice("price_1MotwRLkdIwHu7ixYcPLm5uZ")
//.setQuantity(2L)
//.build()
//)
//.setMode(SessionCreateParams.Mode.PAYMENT)
//.build();
//
//Session session = Session.create(params);
//System.out.println("Hello, World!");
    }
}

