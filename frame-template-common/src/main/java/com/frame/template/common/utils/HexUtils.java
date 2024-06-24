package com.frame.template.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * @Author: zcy
 * @Data: 2022/12/07
 * @Description:
 */
public final class HexUtils {

    private static final Logger LOG = LoggerFactory.getLogger(HexUtils.class);
    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);

    private HexUtils() {
        throw new UnsupportedOperationException("工具类无法被实例化.");
    }

    /**
     * 字节数组转为16进制的字符串，字母部分为大写
     *
     * @param bytes
     * @return
     */
    public static String bytesToHexString(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            LOG.warn("被转换byte[]为null,返回null");
            return null;
        }
        if (bytes.length == 0) {
            LOG.warn("被转换byte[]长度为0,返回空字符串");
            return "";
        }
        /*
         JDK17已经内置了转化方法
         return java.util.HexFormat.of().formatHex(bytes);
         */

        //兼容JDK1.8的方法
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }

    /**
     * 16进制的字符串转回为byte[]
     *
     * @param hexString
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null) {
            LOG.warn("被转换字符串为null,返回null");
            return null;
        }
        if ("".equals(hexString)) {
            LOG.warn("被转换字符串为空字符串,返回空的byte数组");
            return new byte[0];
        }

        /*
         JDK17已经内置了转化方法
         return java.util.HexFormat.of().parseHex(hexString);
         */

        //兼容JDK1.8的方法
        hexString = hexString.toUpperCase();
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }
}

