package com.cashbang;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * @Author: huangdejie
 * @Date: 2019/5/31
 */
public class WriteModel extends BaseRowModel{

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("年龄")
    private String age;

    @ExcelProperty("卡号")
    private String cardNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
