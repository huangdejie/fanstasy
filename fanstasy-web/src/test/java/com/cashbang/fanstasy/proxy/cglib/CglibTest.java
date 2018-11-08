package com.cashbang.fanstasy.proxy.cglib;

import com.cashbang.fanstasy.BaseTest;
import org.junit.Test;

/**
 * @Author: huangdejie
 * @Date: 2018/11/8
 */
public class CglibTest extends BaseTest{

    @Test
    public void testCglib(){
        TeacherService teacherService = new TeacherService();
        CglibProxyFactory cglibProxyFactory = new CglibProxyFactory(teacherService);
        TeacherService teacherProxy = (TeacherService) cglibProxyFactory.getProxyInstance();
        teacherProxy.teach();
    }

}
