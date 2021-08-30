package com.example.demo.bean;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @ClassName CacheCondition
 * @Description
 * @Author ykw
 * @Date 2021/7/13 15:14
 */
public class CacheCondition implements Condition {
    private final static Logger logger = LoggerFactory.getLogger(CacheCondition.class);
    public static boolean flag = true;
    public static int a = 1;
    @Autowired
    private String cacheConfig = "spring-sentinels-redis";

    @Override
    public boolean matches(ConditionContext arg0, AnnotatedTypeMetadata arg1) {
        if(StringUtils.isBlank(cacheConfig)){
            logger.info("缓存未配置缓冲变量");
            return false;
        }
        System.setProperty("cacheConfig",cacheConfig);
        return true;
    }

    public static void main(String[] args) {
        new Thread(() -> {
            int b = a;
            while (b == 1){

            }
            System.out.println(a);
        }).start();
        try {
            Thread.sleep(100);
            a = 2;
            flag = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
