package com.example.demo.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @ClassName TransStateConvert
 * @Description 交易状态转换类
 * @Author ykw
 * @Date 2021/5/26 15:23
 */
public class TransStateConvert implements Converter<String> {
    public static Map<String,String> map = new HashMap<>(8);

    static {
        map.put("26","Consume（消费）");
    }

    @Override
    public Class supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public String convertToJavaData(CellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return null;
    }

    @Override
    public CellData convertToExcelData(String value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return new CellData(Optional.ofNullable(map.get(value)).orElse(value));
    }
}
