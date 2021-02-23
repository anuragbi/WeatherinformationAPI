package com.test.weather.information.service.impl;

import com.test.weather.information.exceptions.RemoteServiceExecutionException;
import com.test.weather.information.model.ResponseCodeAndBody;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class HTTPClientService {
    public ResponseCodeAndBody get(String Url) throws IOException {
        URL url = new URL(Url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        ResponseCodeAndBody responseCodeAndBody = new ResponseCodeAndBody();
        responseCodeAndBody.setResponseCode(conn.getResponseCode());
        if (conn.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            StringBuilder body = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                body.append(output);
            }
            conn.disconnect();


            responseCodeAndBody.setBody(body.toString());
        } else {
            throw new RemoteServiceExecutionException("The service at " + Url + " returned with code" + conn.getResponseCode());
        }
        return responseCodeAndBody;
    }
}
