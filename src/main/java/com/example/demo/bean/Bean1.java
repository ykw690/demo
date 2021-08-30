package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
//@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Bean1 extends Bean2 implements Serializable {
    public String s1;
    public int i1;
    public Bean2 bean2 = new Bean2();

    public static void main(String[] args) {
        try{
            test();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println();
        }
    }

    public static Bean1 getBean1(Bean1 bean1) {
        Bean2 bean2 = bean1.getBean2();
        bean2.setS2("s2");
        return bean1;
    }

    public static void test() throws Exception {
        throw new RuntimeException("123");
    }
}
