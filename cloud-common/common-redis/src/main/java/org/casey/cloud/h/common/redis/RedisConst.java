package org.casey.cloud.h.common.redis;

/**
 * @InterfaceName RedisConst
 * @Author Code2021
 * @Description Redis常量池
 * @Date 2020/11/25 17:10
 */

public interface RedisConst {
    /**
     * 整个部门树
     */
    String DEPARTMENT_TREE = "department:tree";

    /**
     * 整个数据字典
     */
    String DICT = "dict:*";

    /**
     * 存放所有字典数据项的类型, 如sex, enabled
     */
    String DICT_TYPES = "dict:types";

    /**
     * 所有数据项
     */
    String DICT_ITEMS = "dict:items";
}
