package com.example.demo.bean;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.example.demo.convert.TransStateConvert;
import com.example.demo.convert.TransTypeConvert;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@ExcelIgnoreUnannotated
@Data
public class TradingDataBill implements Serializable {
    private static final long serialVersionUID = -1556413147972884780L;

    @ExcelProperty(value = "交易时间",index = 0)
    @ColumnWidth(30)
    private String sttlDate;//交易时间

    @ColumnWidth(40)
    @ExcelProperty(value = "订单号",index = 1)
    private String orderNumber;//订单号

    @ColumnWidth(15)
    @ExcelProperty(value = "商户号",index = 2)
    private String merchantName;//商户名称

    @ColumnWidth(20)
    @ExcelProperty(value = "交易类型",index = 4)
    private String transType;//交易类型

    @ColumnWidth(20)
    @ExcelProperty(value = "交易状态",index = 5,converter = TransStateConvert.class)
    private String transState;//交易状态

    @ColumnWidth(20)
    @ExcelProperty(value = "交易金额(元)",index = 3)
    private BigDecimal transMoney;//交易金额

    private String orderId;//子单号

    @ColumnWidth(50)
    @ExcelProperty(value = "摘要",index = 6)
    private String remark;//摘要

}
