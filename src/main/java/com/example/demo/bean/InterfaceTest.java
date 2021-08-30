package com.example.demo.bean;

import java.io.*;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public interface InterfaceTest {
    String a = "1";
    int b = 1;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SoftReference<HashMap> reference = new SoftReference<>(new HashMap());
        reference.get().put("1","2");
        Bean1 bean1 = new Bean1();
        bean1.setS1("s1");
        bean1.setI1(1);
        Bean2 bean2 = new Bean2();
        bean2.setI2(2);
        bean2.setS2("s2");
        bean1.setBean2(bean2);
        String s = JSON.toJSONString(bean1);
        Bean1 bean11 = JSONObject.parseObject(s, Bean1.class);
        bean1.setS1("ss1");
        Bean1 bean12 = bean11;
        bean11.setI1(11);
        System.out.println(1);
        ArrayList<Object> objects = new ArrayList<>();
    }


}
