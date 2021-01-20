package org.casey.product.service.config;

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
@MapperScan(basePackages = {"org.casey.cloudproductservice.mapper"})
@EnableTransactionManagement
public class MybatisConfig {
}
