package com.cashbang.designpattern.factory.factorymethod;


import com.cashbang.designpattern.factory.ICourse;
import com.cashbang.designpattern.factory.JavaCourse;

/**
 * Created by Tom.
 */
public class JavaCourseFactory implements ICourseFactory {
    public ICourse create() {
        return new JavaCourse();
    }
}
