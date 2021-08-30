package com.example.demo.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName Configuration
 * @Description
 * @Author ykw
 * @Date 2021/6/9 8:57
 */
@Data
@Component
//@ConfigurationProperties(prefix = "spring.datasource")
public class Configuration {
    private String name;
    @Value("spring.datasource.url")
    private String url;
    private String username;
    private String password;

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\test1\\123.txt");
        File parentFile = file.getParentFile();
        if (!file.getParentFile().exists()) {
            boolean mkdirs = file.getParentFile().mkdirs();
            System.out.println(mkdirs);
        }
        boolean newFile = file.createNewFile();
        System.out.println(newFile);
    }
}
