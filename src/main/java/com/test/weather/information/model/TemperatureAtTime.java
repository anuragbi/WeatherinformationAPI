package com.test.weather.information.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TemperatureAtTime implements Comparable<TemperatureAtTime> {
	
	private Double value;
	private LocalDateTime time;
	private String zipCode;
	
	
	public TemperatureAtTime() {
		super();
	}

	public TemperatureAtTime(double value, LocalDateTime time, double minValue ,String zipcode) {
		super();
		this.value = value;
		this.time = time;
		this.zipCode = zipcode;
	}
    public ErrorDetails error;
	
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getZipcode() {
		return zipCode;
	}

	public void setZipcode(String zipcode) {
		this.zipCode = zipcode;
	}

	public ErrorDetails getError() {
		return error;
	}

	public void setError(ErrorDetails error) {
		this.error = error;
	}

	@Override
	public int compareTo(TemperatureAtTime o) {
		return Double.compare(getValue(), o.getValue());
	}
    
    
}
