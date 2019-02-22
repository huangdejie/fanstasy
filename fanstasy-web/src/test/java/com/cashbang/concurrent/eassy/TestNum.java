package com.cashbang.concurrent.eassy;

/**
 * @Author: huangdejie
 * @Date: 2019/2/19
 */
public class TestNum {

    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public int getNextNum(){
        seqNum.set(seqNum.get()+1);
        return seqNum.get();
    }

    public static void main(String[] args) {
        TestNum testNum = new TestNum();
        TestClient testClient1 = new TestClient(testNum);
        TestClient testClient2 = new TestClient(testNum);
        TestClient testClient3 = new TestClient(testNum);
        testClient1.start();
        testClient2.start();
        testClient3.start();
    }

    public static class TestClient extends Thread{
        private TestNum sn;

        public TestClient(TestNum sn){
            this.sn = sn;
        }

        @Override
        public void run() {
            for(int i = 0;i<3;i++){
                System.out.println("thread["+Thread.currentThread().getName()+"] --> sn["+sn.getNextNum()+"]");
            }
        }
    }

}
