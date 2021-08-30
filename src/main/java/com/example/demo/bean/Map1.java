package com.example.demo.bean;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Map1 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int aHundredMillion = 990000;

        Map<Integer, Integer> Map = new HashMap<>(1320000);
        long s5 = System.currentTimeMillis();
        for (int i = 0; i < aHundredMillion; i++) {
            Map.put(i, i);
        }
        long s6 = System.currentTimeMillis();
        System.out.println("初始化容量"+8+"，耗时 ： " + (s6 - s5));

        HashMap<Integer, Integer> map = new HashMap<>(1320001);
        long s1 = System.currentTimeMillis();
        for (int i = 0; i < aHundredMillion; i++) {
            map.put(i, i);
        }
        long s2 = System.currentTimeMillis();
        System.out.println("初始化容量9，耗时 ： " + (s2 - s1));

//        Map<Integer, Integer> Map3 = new HashMap<>(16);
//        long s7 = System.currentTimeMillis();
//        for (int i = 0; i < aHundredMillion; i++) {
//            Map3.put(i, i);
//        }
//        long s8 = System.currentTimeMillis();
//        System.out.println("初始化容量为16，耗时 ： " + (s8 - s7));
//
//        Map<Integer, Integer> Map2 = new HashMap<>(aHundredMillion);
//        long s3 = System.currentTimeMillis();
//        for (int i = 0; i < aHundredMillion; i++) {
//            Map2.put(i, i);
//        }
//        long s4 = System.currentTimeMillis();
//        System.out.println("初始化容量为"+aHundredMillion+"，耗时 ： " + (s4 - s3));
        HashMap<Object, Object> map1 = new HashMap<>(6);
        Class<?> mapType = map1.getClass();
        Method capacity = mapType.getDeclaredMethod("capacity");
        capacity.setAccessible(true);
        System.out.println("capacity : " + capacity.invoke(map1));
        map1.put("01", "小微智造");
        map1.put("02", "口腔医疗");
        map1.put("03", "汽车");
        map1.put("04", "智能终端");
        System.out.println("capacity : " + capacity.invoke(map1));
    }
}
