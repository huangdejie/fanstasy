package com.cashbang.netty.rpc.provider;

import com.cashbang.netty.rpc.api.IRpcService;

/**
 * @Author: huangdejie
 * @Date: 2019/6/26
 */
public class IRpcServiceImpl implements IRpcService {

    @Override
    public int add(int a, int b) {
        return a+b;
    }
}
