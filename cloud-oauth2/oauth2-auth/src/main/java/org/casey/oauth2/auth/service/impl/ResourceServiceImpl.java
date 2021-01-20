package org.casey.oauth2.auth.service.impl;

import org.casey.common.core.consts.RedisConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import cn.hutool.core.collection.CollUtil;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @ClassName ResourceServiceImpl
 * @Author Fu Kai
 * @Description 资源与角色匹配关系管理业务类
 * @Date 2021/1/5 17:20
 */

@Service
public class ResourceServiceImpl {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public ResourceServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void initData() {
        Map<String, List<String>> resourceRolesMap = new TreeMap<>();
        resourceRolesMap.put("/api/hello", CollUtil.toList("ADMIN"));
        resourceRolesMap.put("/api/user/currentUser", CollUtil.toList("ADMIN", "TEST"));
        redisTemplate.opsForHash().putAll(RedisConst.RESOURCE_ROLES_MAP, resourceRolesMap);
    }
}