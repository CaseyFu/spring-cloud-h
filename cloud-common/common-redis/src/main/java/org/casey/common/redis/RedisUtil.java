package org.casey.common.redis;

import java.util.Collection;
import java.util.List;

/**
 * @InterfaceName RedisUtil
 * @Author Code2021
 * @Description Redis
 * @Date 2020/6/13
 */
public interface RedisUtil {
    /**
     * 增加数据
     * @param key 键
     * @param value 值
     */
    void set(String key, String value);

    /**
     * 增加数据并设置过期时间
     * @param key
     * @param value
     * @param expiration
     */
    void set(String key, String value, Long expiration);

    /**
     * 获得数据
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 设置键的过期时间
     * @param key
     * @param expiration
     * @return
     */
    Boolean expire(String key, Long expiration);

    /**
     * 查看键是否过期
     * @param key
     * @return
     */
    Boolean isExpired(String key);

    /**
     * 获得键的过期时间
     * @param key
     * @return
     */
    Long getExpired(String key);

    /**
     * 删除键
     * @param key
     * @return
     */
    Boolean delete(String key);

    /**
     * 根据 key 前缀批量删除键
     * @param keyPrefix
     * @return
     */
    Long deleteByPrefix(String keyPrefix);

    /**
     * 批量删除键
     * @param keys
     */
    void delete(Collection<String> keys);

    /**
     *
     * @param key
     * @param delta
     */
    void increment(String key, Long delta);

    void decrement(String key, Long delta);

    /**
     * 使用scan扫描指定模式的键
     *
     * @param patternKey 模糊键, 如token:*
     * @return 具体键集合
     */
    List<String> findKeys(String patternKey);
}
