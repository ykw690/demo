package com.example.demo.bean;

import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @ClassName CacheConfig
 * @Description
 * @Author ykw
 * @Date 2021/7/13 15:13
 */
@Configuration
@Conditional(CacheCondition.class)
@ImportResource(value = "classpath:${cacheConfig}.xml")
public class CacheConfig {

}

