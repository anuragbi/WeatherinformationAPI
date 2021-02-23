package com.test.weather.information.service.impl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.test.weather.information.exceptions.DataNotFoundException;
import com.test.weather.information.exceptions.SetupException;
import com.test.weather.information.exceptions.USZipCodeNotFoundException;
import com.test.weather.information.model.Location;
import com.test.weather.information.model.LocationDetails;
import com.test.weather.information.model.ResponseCodeAndBody;
import com.test.weather.informations.service.ZipCodeDetailsService;

/**
 * This service receives zipcode as an input and returns the latitude and longitude positions of the same if it is a valid US zipcode.
 * It is dependent upon accuweather for getting the data, since a free version is being used the usage of this api is limited to 50 uses per day.
 */
@Service
public class USZipCodeDetailsServiceImpl implements ZipCodeDetailsService {

    private final HTTPClientService httpClientService;
    @Value("${location.service}")
    private String zipCodeAPI;

    @Autowired
    public USZipCodeDetailsServiceImpl(HTTPClientService httpClientService) {
        this.httpClientService = httpClientService;
    }

    /**
     * @param zipcode
     * @return
     * @throws USZipCodeNotFoundException 
     */
    @Override
    public Location getLocationFromZipCode(String zipcode) throws USZipCodeNotFoundException {
        try {
            ResponseCodeAndBody responseCodeAndBody = httpClientService.get(zipCodeAPI + zipcode);

            Gson gson = new Gson();
            Type type = new TypeToken<List<LocationDetails>>() {
            }.getType();
            List<LocationDetails> locationDetailsList = gson.fromJson(responseCodeAndBody.getBody(), type);

            for (LocationDetails locationDetails : locationDetailsList) {
                if (locationDetails.getCountry().getID().equalsIgnoreCase("US")) {
                    Location location = new Location();
                    location.setName(locationDetails.getEnglishName());
                    location.setLatitude(locationDetails.getGeoPosition().getLatitude());
                    location.setLongitude(locationDetails.getGeoPosition().getLongitude());
                    return location;
                }
            }
      System.out.println("We were unable to map the provided zipcode [" + zipcode + "] to a valid geo-location in U.S.");
           
      
     throw new DataNotFoundException("User not found with id :");
    //  throw new USZipCodeNotFoundException("The zip code is not available");
        } catch (IOException e) {
            throw new SetupException("IO exception during invocation of REST call to " + zipCodeAPI);
        }

    }
}
