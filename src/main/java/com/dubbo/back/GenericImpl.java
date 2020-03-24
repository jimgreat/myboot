package com.dubbo.back;

import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.service.GenericException;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.concurrent.CompletableFuture;

@Service(version = "1.0.0",interfaceName = "com.test.hi")
public class GenericImpl implements GenericService
{
    @Override
    public CompletableFuture<Object> $invokeAsync(String method, String[] parameterTypes, Object[] args) throws GenericException {
        return null;
    }

    @Override
    public Object $invoke(String method, String[] parameterTypes, Object[] args) throws GenericException {
        if("hi".equals(method)){
            return "ok";
        }
        return "no";
    }
}
