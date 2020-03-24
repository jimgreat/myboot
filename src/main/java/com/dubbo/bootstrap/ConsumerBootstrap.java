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
package com.dubbo.bootstrap;

import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallback;
import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallbackRegistry;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.SentinelRpcException;
import com.alibaba.fastjson.JSONObject;
import com.dubbo.service.DemoService;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;

import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * {@link DemoService} consumer demo
 */
@EnableDubbo
@EnableAutoConfiguration
@ComponentScan(value = "com.dubbo.bootstrap")
@PropertySource(value = "classpath:/consumer-config.properties")
public class ConsumerBootstrap {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerBootstrap.class.getName());

    @Reference(version = "${demo.service.version}",retries = 0,timeout = 6000,
                methods = {@Method(name="futureName",timeout = 7000)})
    private DemoService demoService;

    @PostConstruct
    public void init() throws InterruptedException {


        DubboFallbackRegistry.setConsumerFallback(new DubboFallback() {
            @Override
            public Result handle(Invoker<?> invoker, Invocation invocation, BlockException ex) {
                logger.debug("Flow Blocked");
                throw new SentinelRpcException(ex);
            }
        });

        for (int j = 0; j < 1000000000; j++) {
            try {
                logger.info(demoService.sayName("小马哥（mercyblitz）"));
                Object ret = demoService.test();
                if(ret instanceof JSONObject) {
                    JSONObject json = (JSONObject) ret;
                    System.out.println(json.getString("name"));
                }else{
                    System.out.println(ret);
                }
                Thread.sleep(1000L);

            }catch(Exception e){
            }
        }

//        System.out.println(System.currentTimeMillis());
//        CompletableFuture<String> f = demoService.futureName("JM");
//        System.out.println("String complete "+System.currentTimeMillis());
//        f.whenComplete((r,t)->{
//            System.out.println(r);
//            System.out.println("End complete "+System.currentTimeMillis());
//
//        });
//
//        System.out.println(demoService.sayName("MM"));
//
//        Thread.sleep(TimeUnit.SECONDS.toMillis(50));
    }

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ConsumerBootstrap.class);
        context.refresh();
        System.in.read();
        context.close();


    }
}
