package com.example.demo.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName CommonConfig
 * @Description
 * @Author ykw
 * @Date 2021/7/13 15:22
 */
@Configuration
@Data
public class CommonConfig {
    @Value("${cacheConfig}")
    private String cacheConfig;
}
