package com.example.demo.bean;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName HolidayDO
 * @Description
 * @Author ykw
 * @Date 2021/7/9 10:47
 */
@Data
@EqualsAndHashCode
public class HolidayDO {
    private int id;
    private String calendarDate;
    private String dateType;
    private Date createTime;
    private Date updateTime;
    private String isDelete;
    private String reverse1;
    private String reverse2;
    private String reverse3;

    public static void main(String[] args) {
        List<HolidayDO> list1 = new ArrayList<>();
        List<HolidayDO> list2 = new ArrayList<>();
        HolidayDO holidayDO = new HolidayDO();
        holidayDO.setCalendarDate("2021/7/14");
        holidayDO.setReverse1("1");
        list1.add(holidayDO);
        HolidayDO holidayDO2 = new HolidayDO();
        holidayDO2.setCalendarDate("2021/7/13");
        holidayDO2.setReverse1("2");
        System.out.println(holidayDO.equals(holidayDO2));
        list2.add(holidayDO2);
        Set<HolidayDO> set = new TreeSet<>(Comparator.comparing(HolidayDO::getCalendarDate));
        List<HolidayDO> collect = Stream.of(list1, list2)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect));
        set.addAll(collect);
        List<HolidayDO> result = set.stream().collect(Collectors.toList());
        System.out.println(JSON.toJSONString(result));
    }
}
