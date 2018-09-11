package com.cashbang.fanstasy.jmx.ModelBean;

import javax.management.Descriptor;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.modelmbean.*;

/**
 * @Author: huangdejie
 * @Date: 2018/9/7 0007 上午 11:38
 */
public class ModelMBeanUtils {

    private static final boolean READABLE = true;
    private static final boolean WRITABLE = true;
    private static final boolean BOOLEAN = true;
    private static final String STRING_CLASS = "java.lang.String";
    public static RequiredModelMBean createModelerMBean() {
        RequiredModelMBean model = null;
        try {
            model = new RequiredModelMBean();
            model.setManagedResource(new Hello(), "ObjectReference");
            ModelMBeanInfo info = createModelMBeanInfo();
            model.setModelMBeanInfo(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
    private static ModelMBeanInfo createModelMBeanInfo() {
        //////////////////////////////////////////////////////////////////
        //                        属性                                        //
        //////////////////////////////////////////////////////////////////
        // 构造name属性信息
        Descriptor portAttrDesc = new DescriptorSupport();
        portAttrDesc.setField("name", "Name");
        portAttrDesc.setField("descriptorType", "attribute");
        portAttrDesc.setField("displayName", "Name");
        portAttrDesc.setField("getMethod", "getName");
        portAttrDesc.setField("setMethod", "setName");
        ModelMBeanAttributeInfo nameAttrInfo = new ModelMBeanAttributeInfo(
                // 属性名
                "Name",
                //属性类型
                STRING_CLASS,
                // 描述文字
                "people name",
                // 读写
                READABLE, WRITABLE, !BOOLEAN,
                // 属性描述
                portAttrDesc
        );
        //////////////////////////////////////////////////////////////////
        //                        方法                                        //
        //////////////////////////////////////////////////////////////////
        // 构造 getName操作描述符信息
        Descriptor getStateDesc = new DescriptorSupport(new String[] {
                "name=getName",
                "descriptorType=operation",
                "class=com.cashbang.fanstasy.jmx.ModelBean.Hello",
                "role=operation"
        });

        ModelMBeanOperationInfo getName = new ModelMBeanOperationInfo(
                "getName",
                "get name attribute",
                null,
                "java.lang.String",
                MBeanOperationInfo.ACTION,
                getStateDesc
        );

        // 构造 setName操作描述符信息
        Descriptor setStateDesc = new DescriptorSupport(new String[] {
                "name=setName", "descriptorType=operation", "class=com.cashbang.fanstasy.jmx.ModelBean.Hello",
                "role=operation" });

        MBeanParameterInfo[] setStateParms = new MBeanParameterInfo[] { (new MBeanParameterInfo(
                "name", "java.lang.String", "new name value")) };

        ModelMBeanOperationInfo setName = new ModelMBeanOperationInfo(
                "setName",
                "set name attribute",
                setStateParms,
                "void",
                MBeanOperationInfo.ACTION,
                setStateDesc
        );

        //构造 printHello()操作的信息
        ModelMBeanOperationInfo print1Info = new ModelMBeanOperationInfo(
                "printHello",
                null,
                null,
                "void",
                MBeanOperationInfo.INFO,
                null
        );
        // 构造printHello(String whoName)操作信息
        ModelMBeanOperationInfo print2Info;
        MBeanParameterInfo[] param2 = new MBeanParameterInfo[1];
        param2[0] = new MBeanParameterInfo("whoName", STRING_CLASS, "say hello to who");
        print2Info = new ModelMBeanOperationInfo(
                "printHello",
                null,
                param2,
                "void",
                MBeanOperationInfo.INFO,
                null
        );
        //////////////////////////////////////////////////////////////////
        //                        最后总合                                    //
        //////////////////////////////////////////////////////////////////
        // create ModelMBeanInfo
        ModelMBeanInfo mbeanInfo = new ModelMBeanInfoSupport(
                // MBean类
                RequiredModelMBean.class.getName(),
                // 描述文字
                null,
                // 所有的属性信息（数组）
                new ModelMBeanAttributeInfo[] {
                        //只有一个属性
                        nameAttrInfo },
                // 所有的构造函数信息
                null,
                // 所有的操作信息（数组）
                new ModelMBeanOperationInfo[] {
                        getName,
                        setName,
                        print1Info,
                        print2Info },
                // 所有的通知信息(本例无)
                null,
                //MBean描述
                null
        );
        return mbeanInfo;
    }

}
