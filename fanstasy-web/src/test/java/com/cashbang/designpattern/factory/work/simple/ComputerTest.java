package com.cashbang.designpattern.factory.work.simple;

/**
 * @Author: huangdejie
 * @Date: 2019/5/25
 */
public class ComputerTest {

    public static void main(String[] args) {
        ComputerFactory computerFactory = new ComputerFactory();
        Computer computer = computerFactory.createComputer(LaptopComputer.class);
        computer.create();
    }

}
