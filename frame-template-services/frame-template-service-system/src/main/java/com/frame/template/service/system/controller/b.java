package com.frame.template.service.system.controller;

import com.gstdev.cloud.base.core.utils.SecureUtil;
import com.stripe.exception.StripeException;

import java.util.*;

public class b {
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

    /**
     * 2
     * 1 2 12
     * <p>
     * 3
     * 1 2 3 12 13 23 123
     * <p>
     * 4
     * 1 2 3 4    12 13 14 23 24 34   123 124 134 234    1234
     * <p>
     * 5
     * 1 2 3 4 5 12 13 14 15 23 24 25 34 35 45  123 124 125 134 135 145 234 235 245 345  1234 1235 1245 1345 2345 12345
     * <p>
     * 6
     * 1 2 3 4 5 6 12 13 14 15 16 23 24 25 26 34 35 36 45 46 56 123 124 125 126 134 135 136 145 146 156 234 235 236 245 246 256 345 346 356 456 1234 1235 1236 1245 1246 1256 1345 1346 1356 1456 2345 2346 2356 2456 3456 12345 12346 12356 12456 13456 23456 123456
     *
     * @param args
     * @throws StripeException
     */

    public static List<String> printCombinations(List<String> array, int start, List<List<String>> prefix, List<String> all) {
        if (start >= array.size()) {
            return all;
        }
        List<String> b = new ArrayList<>();
        List<List<String>> objects = new ArrayList<>();
        for (int i = 0; i < prefix.size() - 1; i++) {
            b = new ArrayList<>();
            for (int i1 = 0; i1 < prefix.get(i).size(); i1++) {
                String string1 = prefix.get(i).get(i1).toString();
                for (int a = start+i+1; a < array.size(); a++) {
                    String string = array.get(a).toString();
                    if (string1.contains(string)) {
                        continue;
                    }
                    b.add(string1 + string);
                }
            }
//            all.add(generateKey(b));
            objects.add(b);
        }
        System.out.println(objects);
        List<String> strings = printCombinations(array, start + 1, objects, all);
        return strings;
    }


    public static void main(String[] args) throws StripeException {
        List<List<String>> combinedCodes = new ArrayList<>();
        List<String> a = new ArrayList<>();
        List<String> all = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            a.add(String.valueOf(i + 1));
            List<String> b = new ArrayList<>();
            b.add(String.valueOf(i + 1));
            combinedCodes.add(b);
        }
        List<String> strings = printCombinations(a, 0, combinedCodes, all);
        System.out.println(strings);
    }

}
