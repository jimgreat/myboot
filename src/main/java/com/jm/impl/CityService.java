package com.jm.impl;

import com.jm.City;
import com.jm.service.ICityService;
import org.springframework.stereotype.Service;

@Service
public class CityService implements ICityService {

    @Override
    public City findCity(String name){
        City c = new City();
        c.setName(name);
        return c;
    }
}
