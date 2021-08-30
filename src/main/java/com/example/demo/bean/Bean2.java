package com.example.demo.bean;

import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Data
public class Bean2 implements Serializable {
    public String s2;
    public int i2;

    public Bean2(){
    }

    public static void main(String[] args) throws ParseException {
//        TreeSet<String> tree = new TreeSet<String> ();
//        tree.add("China");
//        tree.add("America");
//        tree.add("Japan");
//        tree.add("Chinese");
//        tree.add("Diio");
//        Iterator<String> iter = tree.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next());
//        }
        String format = String.format("%02d", 8);
        System.out.println(format);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH,0);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println(DateUtil.formatDate(c.getTime()));
        c.setTime(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-01"));
        int i = c.get(Calendar.YEAR);
        int x = c.get(Calendar.MONTH);
        int y = c.get(Calendar.DAY_OF_MONTH);
        System.out.println(i);
        System.out.println(x);
        System.out.println(y);
    }
}
