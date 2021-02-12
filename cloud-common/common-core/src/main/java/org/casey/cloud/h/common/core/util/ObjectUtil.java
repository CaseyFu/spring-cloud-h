package org.casey.cloud.h.common.core.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName ObjectUtil
 * @Author Code2021
 * @Description 对象工具类
 * @Date 2020/12/13 13:30
 */

public class ObjectUtil {

    /**
     * 空
     */
    public static boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }

    /**
     * 非空
     */
    public static boolean nonNull(Object obj) {
        return Objects.nonNull(obj);
    }

    /**
     * 相等
     */
    public static boolean equals(Object obj1, Object obj2) {
        return Objects.equals(obj1, obj2);
    }


    /**
     * Object转指定类型
     * 必须明确Object为List类型
     */
    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new LinkedList<>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }

}
