package com.test.weather.information.exceptions;

public class USZipCodeNotFoundException extends Exception {

	private static final long serialVersionUID = -470180507998010368L;

	public USZipCodeNotFoundException() {
		super();
	}

	public USZipCodeNotFoundException(final String message) {
		super(message);
	}
}
