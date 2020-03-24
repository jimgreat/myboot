package com.dubbo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.CompletableFuture;

public class DemoServiceStub implements DemoService {

    private final DemoService demoService;

    public DemoServiceStub(DemoService demoService) {
        this.demoService = demoService;
    }

    @Override
    public Object test() {
        String str = String.valueOf(demoService.test());
        return JSON.parseObject(str);
//        return demoService.test();
    }

    @Override
    public String sayName(String name) {
        return demoService.sayName(name)+" Stub";
    }

    @Override
    public CompletableFuture<String> futureName(String name) {
        return demoService.futureName(name);
    }
}
