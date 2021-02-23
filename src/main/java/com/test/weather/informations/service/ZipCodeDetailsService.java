package com.test.weather.informations.service;

import com.test.weather.information.exceptions.USZipCodeNotFoundException;
import com.test.weather.information.model.Location;

public interface ZipCodeDetailsService {
    Location getLocationFromZipCode(String zipcode) throws USZipCodeNotFoundException;
}
