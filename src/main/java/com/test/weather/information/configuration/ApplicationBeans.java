package com.test.weather.information.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.test.weather.information.service.impl.HTTPClientService;
import com.test.weather.information.service.impl.TemperatureForecastServiceImpl;
import com.test.weather.information.service.impl.TemperatureReportingServiceImpl;
import com.test.weather.information.service.impl.USZipCodeDetailsServiceImpl;
import com.test.weather.informations.service.TemperatureForecastService;
import com.test.weather.informations.service.TemperatureReportingService;
import com.test.weather.informations.service.ZipCodeDetailsService;


@Configuration
public class ApplicationBeans {
    @Value("${temperature.service.location}")
    private String darkSkyServiceLocation;

    
    @Bean
    @Primary
    public TemperatureForecastService getTemp()
    {
    	return new TemperatureForecastServiceImpl(new HTTPClientService());
    }
    
    @Bean
    @Primary
    public TemperatureReportingService getTemperatureReportingService()
    {
    	return new TemperatureReportingServiceImpl();
    }
    
    @Bean
    @Primary
    public ZipCodeDetailsService getZipCodeDetailsService()
    {
    	return new USZipCodeDetailsServiceImpl(new HTTPClientService());
    }
}

