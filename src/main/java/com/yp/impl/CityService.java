package com.yp.impl;

import com.yp.City;
import com.yp.service.ICityService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CityService implements ICityService {

    @Cacheable(value = "users",key = "'city_'+#name")
    @Override
    public City findCity(String name){

        System.out.println("NO Cache");
        City c = new City();
        c.setName(name);
        return c;
    }
}
