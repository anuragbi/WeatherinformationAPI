package com.test.weather.information.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.weather.information.model.Location;
import com.test.weather.information.model.ResponseCodeAndBody;
import com.test.weather.information.model.TemperatureAtTime;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class TemperatureReportingServiceTest {

	private static StringBuilder successResponse = new StringBuilder("{\n" +
            "\t\"latitude\": 1,\n" +
            "\t\"longitude\": 1,\n" +
            "\t\"timezone\": \"Etc/GMT\",\n" +
            "\t\"hourly\": {\n" +
            "\t\t\"summary\": \"Rain starting this evening.\",\n" +
            "\t\t\"icon\": \"rain\",\n" +
            "\t\t\"data\": [{\n" +
            "\t\t\t\"time\": 1544335200,\n" +
            "\t\t\t\"temperature\": 27.17\n" +
            "\t\t}");
    @Autowired
    private TemperatureForecastServiceImpl temperatureForecastService;
    @Mock
    private HTTPClientService httpClientService;

    @Before
    public void setup() {
        for (int i = 0; i < 24; i++) {
            String hourlyTemp = ", {\n" +
                    "\t\t\t\"time\": " +
                    LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toEpochSecond()
                    + ",\n" +
                    "\t\t\t\"temperature\": " + i + "\n" +
                    "\t\t} \n";
            successResponse.append(hourlyTemp);
        }
        successResponse.append(
                "\t\t]\n" +
                        "\t},\n" +
                        "\t\"offset\": 0\n" +
                        "}");
    }
    
    @Test
    public void getTemperatureForeCastForGeoPosition() throws IOException {
    	ResponseCodeAndBody responseCodeAndBody = new ResponseCodeAndBody();
        responseCodeAndBody.setResponseCode(200);
        responseCodeAndBody.setBody(successResponse.toString());
        when(httpClientService.get(Mockito.anyString())).thenReturn(responseCodeAndBody);
        Location location = new Location();
        location.setLongitude(1);
        location.setLatitude(1);
        List<TemperatureAtTime> temperatures = temperatureForecastService.getTemperatureForeCastForGeoPosition(location);
        assertEquals(24, temperatures.size());
    }

    
}
