/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dubbo.service;

import com.alibaba.fastjson.JSON;
import com.dubbo.back.BackService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


/**
 * Default {@link DemoService}
 *
 * @since 2.6.5
 */
@Service(version = "${demo.service.version}")
public class DefaultService implements DemoService {

    @Value("${demo.service.name}")
    private String serviceName;

    @Reference(version = "1.0.0")
    private BackService backService;

    @Reference(version = "1.0.0",interfaceName = "com.test.hi")
    private GenericService genericService;

    @Override
    public Object test() {
        Map<String,String> map = new HashMap<>();
        map.put("name","jinming");
        return JSON.toJSONString(map);
    }

    @Override
    public String sayName(String name) {
        return String.format("Service [name :%s ] %s : Hello,%s",
                backService.back(serviceName),
                genericService.$invoke("hi",null,null),
                name);
    }


    @Override
    public CompletableFuture<String> futureName(String name) {
        CompletableFuture<String> r = CompletableFuture.supplyAsync(()->{
            System.out.println("S "+System.currentTimeMillis());
            try{
                Thread.sleep(4000);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("E "+System.currentTimeMillis());
            return "HI:"+name;
        });

        return r.thenApplyAsync(item->item+" Apply");

    }
}
