package com.cashbang.fanstasy.zkdemo;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * @Author: huangdejie
 * @Date: 2018/8/30 0030 下午 2:35
 */
public class ZkCreate {

    private static ZooKeeper zooKeeper;

    private static ZookeeperConnection zookeeperConnection;

    /**
     * create(String path, byte[] data, List<ACL> acl, CreateMode createMode)
     * path:znode路径
     * data:存储在znode路径中的数据
     * acl:要创建的节点的访问控制列表
     * createMode：创建的节点类型
     * @param path
     * @param data
     * @throws Exception
     */
    public static void create(String path,byte[] data) throws Exception{
        zooKeeper.create(path,data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }


    public static void main(String[] args) {
        String path = "/firstNode";
        byte[] data = "on the fllor".getBytes();
        zookeeperConnection = new ZookeeperConnection();
        try {
            zooKeeper = zookeeperConnection.connect("localhost");
            create(path,data);
            zookeeperConnection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
