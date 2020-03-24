package com.jm.impl;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Activate(
        group = {CommonConstants.PROVIDER,CommonConstants.CONSUMER}
)
public final class TestDubboFilter implements Filter {

    private final static Logger logger = LoggerFactory.getLogger(TestDubboFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        logger.info("TestDubboFilter");
        return invoker.invoke(invocation);
    }
}
