package org.casey.cloud.h.common.core.util;

import cn.hutool.core.util.StrUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName StringUtil
 * @Author Code2021
 * @Description 字符串工具类, 项目统一使用, 方便修改
 * @Date 2020/11/29 19:22
 */

public class StringUtil {
    private static final Pattern UNDERLINE_PATTERN = Pattern.compile("_(\\w)");
    private static final Pattern HUMP_PATTERN = Pattern.compile("[A-Z]");

    /**
     * 下划线转驼峰
     */
    public static String underlineToHump(String str) {
        Matcher matcher = UNDERLINE_PATTERN.matcher(str.toLowerCase());
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    /**
     * 驼峰转下划线
     */
    public static String humpToUnderline(String str) {
        Matcher matcher = HUMP_PATTERN.matcher(str);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    /**
     * 不为null
     * 不为""
     */
    public static boolean isNotEmpty(String string) {
        return StrUtil.isNotEmpty(string);
    }

    /**
     * 为null
     * 为""
     */
    public static boolean isEmpty(String string) {
        return StrUtil.isEmpty(string);
    }

    /**
     * 为null
     * 为""
     * 为"   "
     */
    public static boolean isBlank(String string) {
        return StrUtil.isBlank(string);
    }

    /**
     * 不为null
     * 不为""
     * 不为"   "
     */
    public static boolean isNotBlank(String string) {
        return StrUtil.isNotBlank(string);
    }

    /**
     * 相等
     */
    public static boolean equals(String str1, String str2) {
        return StrUtil.equals(str1, str2);
    }

}
