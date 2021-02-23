package com.test.weather.informations.service;

import java.util.List;

import com.test.weather.information.model.TemperatureAtTime;

public interface TemperatureReportingService {
    void printTemperatures(List<TemperatureAtTime> temperatures);
    public TemperatureAtTime getMinimumTemperature(List<TemperatureAtTime> temperatures);
    
}
