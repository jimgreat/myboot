package com.yp.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class MyInterceptor implements MethodInterceptor {

    private static Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        logger.info(method.getName()+" Start");
        Object ret = invocation.proceed();
        logger.info(method.getName()+" OK");
        return ret;
    }
}
