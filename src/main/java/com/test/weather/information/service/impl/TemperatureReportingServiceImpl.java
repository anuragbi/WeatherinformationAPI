package com.test.weather.information.service.impl;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.test.weather.information.model.TemperatureAtTime;
import com.test.weather.informations.service.TemperatureReportingService;

/**
 * This service reports the temperature in a given format.
 * <p>
 * This service also detects the coolest temperature of the day and highlights it during reporting
 */
@Service
public class TemperatureReportingServiceImpl implements TemperatureReportingService {
	private static final Logger logger = LoggerFactory.getLogger(TemperatureReportingServiceImpl.class);
	@Value("${report.date.format}")
    private String reportDateFormat;

    @Override
    public void printTemperatures(List<TemperatureAtTime> temperatures) {
        TemperatureAtTime minimumTemperature = getMinimumTemp(temperatures);
        for (TemperatureAtTime temperature : temperatures) {
            if (temperature.compareTo(minimumTemperature) == 0) {
                reportDateFormat = "dd MMM yyyy - hh:mm a";
                logger.info("--The-Coolest-Hour--> The temperature at hour " + temperature.getTime().format(DateTimeFormatter.ofPattern(reportDateFormat)) + " is " + temperature.getValue() + "°C<---");
            } else {
                logger.info("The temperature at hour " + temperature.getTime().format(DateTimeFormatter.ofPattern("dd MMM yyyy - hh:mm a")) + " is " + temperature.getValue() + "°C");
            }
        }
    }

    
    @Override
    public TemperatureAtTime getMinimumTemperature(List<TemperatureAtTime> temperatures) {
        TemperatureAtTime minimumTemperature = getMinimumTemp(temperatures);
        
        logger.info("--------------------------------"+minimumTemperature.getValue());
        logger.info("________________________________"+minimumTemperature.getTime());
        TemperatureAtTime min=null;
        for (TemperatureAtTime temperature : temperatures) {
            if (temperature.compareTo(minimumTemperature) == 0) {
                reportDateFormat = "dd MMM yyyy - hh:mm a";
                 min= temperature;
                return min;
              //  logger.info("--The-Coolest-Hour--> The temperature at hour " + temperature.getTime().format(DateTimeFormatter.ofPattern(reportDateFormat)) + " is " + temperature.getValue() + "°C<---");
            } else {
               // logger.info("The temperature at hour " + temperature.getTime().format(DateTimeFormatter.ofPattern("dd MMM yyyy - hh:mm a")) + " is " + temperature.getValue() + "°C");
            }
        }
		return min;
    }
    
    
    public double getMinimumTemperature1(List<TemperatureAtTime> temperatures) {
        TemperatureAtTime minimumTemperature = getMinimumTemp(temperatures);
        double min=0;
        for (TemperatureAtTime temperature : temperatures) {
            if (temperature.compareTo(minimumTemperature) == 0) {
                reportDateFormat = "dd MMM yyyy - hh:mm a";
                 min= temperature.getValue();
                return min;
              //  logger.info("--The-Coolest-Hour--> The temperature at hour " + temperature.getTime().format(DateTimeFormatter.ofPattern(reportDateFormat)) + " is " + temperature.getValue() + "°C<---");
            } else {
               // logger.info("The temperature at hour " + temperature.getTime().format(DateTimeFormatter.ofPattern("dd MMM yyyy - hh:mm a")) + " is " + temperature.getValue() + "°C");
            }
        }
		return min;
    }
    
     public TemperatureAtTime getMinimumTemp(List<TemperatureAtTime> temperatures) {
       
    	 TemperatureAtTime minValue = temperatures.get(0);
        for (int i = 1; i < temperatures.size(); i++) {
            if (temperatures.get(i).compareTo(minValue) < 1) {
                minValue = temperatures.get(i);
            }
        }
        return minValue;
    }
     
}
