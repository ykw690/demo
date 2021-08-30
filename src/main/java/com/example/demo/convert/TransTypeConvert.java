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
 * @ClassName TransTypeConvert
 * @Description 交易类型转换类
 * @Author ykw
 * @Date 2021/5/26 15:23
 */
public class TransTypeConvert implements Converter<String> {
    public static HashMap<String,String> map = new HashMap<>(8);

    static {
        map.put("20","记账失败");
    }

    @Override
    public Class supportJavaTypeKey() {
        return null;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return null;
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
