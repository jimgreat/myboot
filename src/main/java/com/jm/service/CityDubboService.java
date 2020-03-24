package com.jm.service;

import com.jm.City;

public interface CityDubboService {
    City findCityByName(String cityName);
}
