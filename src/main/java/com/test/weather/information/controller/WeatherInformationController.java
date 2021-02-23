package com.test.weather.information.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.weather.information.exceptions.USZipCodeNotFoundException;
import com.test.weather.information.model.ErrorDetails;
import com.test.weather.information.model.Location;
import com.test.weather.information.model.TemperatureAtTime;
import com.test.weather.informations.service.TemperatureForecastService;
import com.test.weather.informations.service.TemperatureReportingService;
import com.test.weather.informations.service.ZipCodeDetailsService;

@RestController
public class WeatherInformationController {
	private static final Logger logger = LoggerFactory.getLogger(WeatherInformationController.class);
	@Autowired
	TemperatureForecastService temperatureForecastServiceimpl;
	@Autowired
	TemperatureReportingService temperatureReportingService;
	@Autowired
	ZipCodeDetailsService zipCodeDetailsService;

	@GetMapping("/weatherinfo")
	public ResponseEntity<TemperatureAtTime> getweatherinfo(@RequestParam(value = "zipcode") String zipcode)
			throws USZipCodeNotFoundException {
		List<TemperatureAtTime> temperatures = null;
		TemperatureAtTime temperatureAtTime = null;
		logger.info("-----------the zip code is:" + zipcode);
		if (zipcode.equals("") || zipcode == null) {
			TemperatureAtTime temperatureAtTime1 = new TemperatureAtTime();
			temperatureAtTime1.setValue(0);
			temperatureAtTime1.setError(new ErrorDetails(101, "zipcode is null", new Date()));
			return ResponseEntity.badRequest().body(temperatureAtTime1);

		} else if (!zipcode.matches("[0-9]+")) {
			logger.info("zipcode is not valid");
			TemperatureAtTime temperatureAtTime1 = new TemperatureAtTime();
			temperatureAtTime1.setError(new ErrorDetails(102, "zipcode is not valid", new Date()));
			temperatureAtTime1.setValue(0);
			return ResponseEntity.badRequest().body(temperatureAtTime1);
		} else if (zipcode.length() != 5) {
			logger.info("zipcode is not having 5 digits");
			TemperatureAtTime temperatureAtTime1 = new TemperatureAtTime();
			temperatureAtTime1.setError(new ErrorDetails(103, "zipcode is not having 5 digits", new Date()));
			temperatureAtTime1.setValue(0);
			return ResponseEntity.badRequest().body(temperatureAtTime1);
		}

		else {
			LocalDate tomorrow = LocalDate.from(LocalDate.now()).plusDays(1);
			try {
				logger.info("this is logger");
				Location location = zipCodeDetailsService.getLocationFromZipCode(zipcode);
				System.out.println("location value::" + location);
				if (location == null) {
					System.out.println("location is null");
					TemperatureAtTime temperatureAtTime1 = new TemperatureAtTime();
					temperatureAtTime1.setValue(0);
					temperatureAtTime1
							.setError(new ErrorDetails(101, "We were unable to map the provided zipcode", new Date()));
					return ResponseEntity.badRequest().body(temperatureAtTime1);
				}
				System.out.println(
						"Checking the weather for " + tomorrow.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
								+ " at " + zipcode + "(" + location.getName() + ")");
				temperatures = temperatureForecastServiceimpl.getTemperatureForeCastForGeoPosition(location);

				// temperatureReportingService.printTemperatures(temperatures);
				temperatureAtTime = temperatureReportingService.getMinimumTemperature(temperatures);
				temperatureAtTime.setZipcode(zipcode);

				logger.info("This is min temp::::::::::::::::" + temperatureAtTime.getValue());
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}

			return ResponseEntity.status(200).body(temperatureAtTime);
		}
	}


}
