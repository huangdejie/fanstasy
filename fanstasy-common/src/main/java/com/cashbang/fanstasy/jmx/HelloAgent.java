package com.cashbang.fanstasy.jmx;

import com.cashbang.fanstasy.jmx.notification.HiListener;
import com.cashbang.fanstasy.jmx.notification.Xiaosi;
import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.*;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @Author: huangdejie
 * @Date: 2018/9/7 0007 上午 9:35
 */
public class HelloAgent {

//    public static void main(String[] args) throws Exception{
//        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
//        String domainName = "MyMBean";
//        ObjectName objectName = new ObjectName(domainName+":name=Hello");
//        mBeanServer.registerMBean(new Hello(),objectName);
//        ObjectName adapterName = new ObjectName(domainName+":name=htmlAdapter,port=8082");
//        HtmlAdaptorServer htmlAdaptorServer = new HtmlAdaptorServer();
//        htmlAdaptorServer.start();
//        mBeanServer.registerMBean(htmlAdaptorServer,adapterName);
//        int rmiPort = 1099;
//        Registry registry = LocateRegistry.createRegistry(rmiPort);
//        JMXServiceURL jmxServiceURL =new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:"+rmiPort+"/"+domainName);
//        JMXConnectorServer jmxConnector = JMXConnectorServerFactory.newJMXConnectorServer(jmxServiceURL, null, mBeanServer);
//        jmxConnector.start();
//    }

    /**
     * notify
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();

        ObjectName helloName = new ObjectName("MyMBean:name=HelloWorld");
        Hello hello = new Hello();
        server.registerMBean(hello,helloName);

        ObjectName adapterName = new ObjectName("MyBean:name=htmladapter,port=8082");
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
        server.registerMBean(adapter,adapterName);

        Xiaosi xs = new Xiaosi();
        server.registerMBean(xs,new ObjectName("MyMBean:name=xiaosi"));
        xs.addNotificationListener(new HiListener(),null,hello);
        adapter.start();
    }

}
