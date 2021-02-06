package org.casey.common.redis;

import org.casey.common.redis.RedisUtil;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisUtilImpl
 * @Author Code2021
 * @Description redis简单封装
 * @Date 2020/7/13 17:47
 */


public class RedisUtilImpl implements RedisUtil {

    private final StringRedisTemplate stringRedisTemplate;

    public RedisUtilImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void set(String key, String value) {
        this.stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, String value, Long expiration) {
        this.stringRedisTemplate.opsForValue().set(key, value, expiration, TimeUnit.MILLISECONDS);
    }

    @Override
    public String get(String key) {
        return this.stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public Boolean expire(String key, Long expiration) {
        return this.stringRedisTemplate.expire(key, expiration, TimeUnit.MILLISECONDS);
    }

    @Override
    public Boolean isExpired(String key) {
        // key过期或者不存在返回true
        Long time = stringRedisTemplate.getExpire(key, TimeUnit.MILLISECONDS);
        return null == time || time <= 0;
    }

    @Override
    public Long getExpired(String key) {
        return stringRedisTemplate.getExpire(key, TimeUnit.MILLISECONDS);
    }


    @Override
    public Boolean delete(String key) {
        return this.stringRedisTemplate.delete(key);
    }

    @Override
    public Long deleteByPrefix(String keyPrefix) {
        Set<String> keys = this.stringRedisTemplate.keys(keyPrefix);
        if (null != keys && !keys.isEmpty()) {
            return stringRedisTemplate.delete(keys);
        }
        return 0L;
    }

    @Override
    public void delete(Collection<String> keys) {
        this.stringRedisTemplate.delete(keys);
    }

    @Override
    public void increment(String key, Long delta) {
        this.stringRedisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public void decrement(String key, Long delta) {
        this.stringRedisTemplate.opsForValue().decrement(key, delta);
    }

    /**
     * 查询当前在线的用户, 使用scan提高查询效率
     */
    @Override
    public List<String> findKeys(String patternKey) {
        ScanOptions options = ScanOptions.scanOptions().match(patternKey).build();
        RedisConnectionFactory factory = this.stringRedisTemplate.getConnectionFactory();
        if (null == factory) {
            throw new RuntimeException("redis 未连接 - RedisServiceImpl");
        }
        RedisConnection connection = factory.getConnection();
        Cursor<byte[]> cursor = connection.scan(options);

        List<String> result = new LinkedList<>();
        while (cursor.hasNext()) {
            result.add(new String(cursor.next()));
        }
        try {
            cursor.close();
        } catch (IOException ignored) {
        }
        return result;
    }

}
