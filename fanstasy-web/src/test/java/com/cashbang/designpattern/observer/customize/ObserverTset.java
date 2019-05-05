package com.cashbang.designpattern.observer.customize;

import com.cashbang.BaseTest;
import org.junit.Test;

/**
 * @Author: huangdejie
 * @Date: 2019/3/7
 */
public class ObserverTset extends BaseTest {

    @Test
    public void testObserver(){
        Cup cup = new Cup(300);
        Person person1 = new Person("观察者1");
        Person person2 = new Person("观察者2");
        cup.registerObserver(person1);
        cup.registerObserver(person2);
        System.out.println("第一轮促销");
        cup.setPrice(280);
        System.out.println("第二轮促销");
        cup.setPrice(250);
        System.out.println("第三轮促销");
        cup.setPrice(200);
        cup.removeObserver(person2);
        System.out.println("第四轮促销");
        cup.setPrice(100);
    }

}
