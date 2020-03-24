package com.dubbo.back;

import org.apache.dubbo.config.annotation.Service;

@Service(version = "1.0.0",parameters = {"metadata","remote"})
public class BackServiceImpl implements BackService {
    @Override
    public String back(String msg) {
        return "I'm Back 2 ["+msg+"]";
    }
}
