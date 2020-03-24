package com.jm;

import com.jm.ds.DataSourceContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Jobs {
    public final static long ONE_Minute =  1000;

//    @Scheduled(fixedDelay=ONE_Minute)
    public void fixedDelayJob(){
        System.out.println((new Date())+" >>fixedDelay执行...."+DataSourceContextHolder.getDB());
    }
}
