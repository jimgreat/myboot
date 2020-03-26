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

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

@EnableDubbo(scanBasePackages = "com.dubbo.service")
@EnableAutoConfiguration
@ComponentScan(value = "com.dubbo.bootstrap")
@PropertySource(value = "classpath:/provider-config.properties")
public class ProviderBootstrap {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ProviderBootstrap.class);
        context.refresh();
        System.out.println("DemoService provider is starting...");
        while(true) {
            try {
                Thread.sleep(5000);
                System.out.println("I'm alive.");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
