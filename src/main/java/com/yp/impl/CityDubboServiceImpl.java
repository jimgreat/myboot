package com.yp.impl;

import com.yp.City;
import com.yp.service.CityDubboService;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Service(interfaceClass = CityDubboService.class,version = "1.0.0")
@Component
public class CityDubboServiceImpl implements CityDubboService  {

    private static Logger logger = LoggerFactory.getLogger(CityDubboServiceImpl.class);


    public static int cnt=0;

    @Override
    public City findCityByName(String cityName) {
        City c = new City();
        cnt++;
        c.setMsg("HiYYYY好的"+cnt);
        c.setName(cityName);
        String index = RpcContext.getContext().getAttachment("index");
        logger.info("RpcContext:{}",index);
        return c;
    }
}
