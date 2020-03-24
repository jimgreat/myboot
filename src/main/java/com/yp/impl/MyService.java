package com.yp.impl;

import com.yp.service.IMyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyService implements IMyService {

    private static Logger logger = LoggerFactory.getLogger(MyService.class);

    @Override
    public void t(){
        logger.info("t");
    }
}
