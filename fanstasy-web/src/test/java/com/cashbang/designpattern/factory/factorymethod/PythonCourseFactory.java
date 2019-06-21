package com.cashbang.designpattern.factory.factorymethod;


import com.cashbang.designpattern.factory.ICourse;
import com.cashbang.designpattern.factory.PythonCourse;

/**
 * Created by Tom.
 */
public class PythonCourseFactory implements ICourseFactory {

    public ICourse create() {
        return new PythonCourse();
    }
}
