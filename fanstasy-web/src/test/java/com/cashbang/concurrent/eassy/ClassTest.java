package com.cashbang.concurrent.eassy;

import com.cashbang.BaseTest;
import com.cashbang.fanstasy.entity.UserEntity;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: huangdejie
 * @Date: 2019/2/21
 */
public class ClassTest extends BaseTest {

    private final Logger logger = LoggerFactory.getLogger(ClassTest.class);

    /**
     * 双亲委派
     *  如果一个类加载器收到了类加载的请求，首先不会自己去尝试加载这个类，
     *  而是将这个请求委派给父类加载器去完成这个请求，每个层次的类加载器
     *  都是如此，因此所有的类加载请求最终都会传送到顶层的启动类加载器，
     *  只有当父类加载器反馈无法完成这个加载请求时，子加载器才会尝试自己
     *  去加载
     */
    @Test
    public void testClassLoader(){
        ClassLoader classLoader = UserEntity.class.getClassLoader();
        while (classLoader != null){
            logger.info("类加载器:"+classLoader);
            classLoader = classLoader.getParent();
        }
    }

}
