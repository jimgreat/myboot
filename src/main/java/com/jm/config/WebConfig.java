package com.jm.config;

import com.jm.interceptor.MyInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.context.annotation.Bean;

//@Configuration
public class WebConfig {

    @Bean
    public MyInterceptor myInterceptor() {
        return new MyInterceptor();
    }

    @Bean
    public JdkRegexpMethodPointcut myPointcut(){
        JdkRegexpMethodPointcut myPointcut = new JdkRegexpMethodPointcut();
        String patterns = "com.jm.*.service.*";
        //可以set多个
        myPointcut.setPatterns(patterns);
        return myPointcut;
    }

    @Bean
    public Advisor druidStatAdvisor() {
        return new DefaultPointcutAdvisor(myPointcut(), myInterceptor());
    }
}
