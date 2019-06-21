package com.cashbang.designpattern.factory.work.simple;

/**
 * @Author: huangdejie
 * @Date: 2019/5/25
 */
public class ComputerFactory {

    public Computer createComputer(Class<? extends Computer> clazz){
        try{
            if(clazz != null){
                return clazz.newInstance();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
