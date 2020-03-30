package com.dubbo.bootstrap;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

@EnableDubbo(scanBasePackages = "com.dubbo.back")
@EnableAutoConfiguration
@MapperScan("com.jm.business.mapper")
@ComponentScan(value = {"com.dubbo.bootstrap","com.jm.business.service"})
@PropertySource(value = "classpath:/back-config.properties")
public class BackProvider {

    private static Logger logger = LoggerFactory.getLogger(BackProvider.class.getName());

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BackProvider.class);
        context.refresh();
        logger.debug("HIHIHIHIHII");
        System.out.println("Back provider is starting...");
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
