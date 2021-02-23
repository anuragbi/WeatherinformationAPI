package com.test.weather.informations.service;

import java.util.List;

import com.test.weather.information.model.Location;
import com.test.weather.information.model.TemperatureAtTime;


public interface TemperatureForecastService {
    List<TemperatureAtTime> getTemperatureForeCastForGeoPosition(Location location);
}
