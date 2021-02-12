package org.casey.cloud.h.common.core.util;

import cn.hutool.core.util.IdUtil;

/**
 * @ClassName UUIDUtil
 * @Author Fu Kai
 * @Description todo
 * @Date 2021/1/4 11:44
 */

public class UUIDUtil {
    /**
     * 生成32位的UUID
     */
    public static String generate() {
        return IdUtil.fastSimpleUUID();
    }
}
