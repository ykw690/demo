package com.example.demo.bean;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    //    @Bean
//    public TestClass1 testClass12(){
//        return new TestClass1();
//    }
    public static void main(String[] args) {
        String s = StringUtils.leftPad("13415215485", 11, "A");
        System.out.println(s);
        String center = StringUtils.center("21", 11, "*");
        System.out.println(center);
        String s1 = StringUtils.rightPad("1", 11, "*");
        System.out.println(s1);
    }
}
