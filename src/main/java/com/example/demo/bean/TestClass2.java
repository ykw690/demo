package com.example.demo.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class TestClass2  {
    private static final TestClass3 s3 = new TestClass3();
    private String s4;

    public static void main(String[] args) {
        TestClass2 anInt1 = getInt();
        System.out.println(anInt1.toString());
    }

    public static TestClass2 getInt(){
        int a = 0;
        TestClass2 testClass2 = new TestClass2();
        testClass2.setS4("try");
        try{
            return testClass2;
        }catch (Exception e){
            testClass2.setS4("catch");
            return testClass2;
        }finally {
            testClass2.setS4("finally");
        }
    }

    public static String getString(){
        String a = new String("aaa");
        try{
            return a;
        }catch (Exception e){
            return a;
        }finally {

        }
    }
}
