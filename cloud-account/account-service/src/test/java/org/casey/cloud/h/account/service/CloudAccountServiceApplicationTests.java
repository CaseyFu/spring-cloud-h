package org.casey.cloud.h.account.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class CloudAccountServiceApplicationTests {

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Test
	void contextLoads() {
	}

}
