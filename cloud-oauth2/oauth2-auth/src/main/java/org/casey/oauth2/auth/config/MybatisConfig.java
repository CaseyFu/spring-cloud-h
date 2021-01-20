package org.casey.oauth2.auth.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName MybatisConfig
 * @Author Fu Kai
 * @Description todo
 * @Date 2020/12/27 0:39
 */
@Configuration
@MapperScan(basePackages = {"org.casey.cloudoauth2auth.mapper"})
@EnableTransactionManagement
public class MybatisConfig {
}
