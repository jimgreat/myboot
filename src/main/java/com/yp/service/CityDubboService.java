package com.yp.service;

import com.yp.City;

public interface CityDubboService {
    City findCityByName(String cityName);
}
