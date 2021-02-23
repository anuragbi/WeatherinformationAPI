package com.test.weather.information.model;

import java.util.Date;

public class ErrorDetails {
	public int errorcode;
    public String errormessage;
    public Date time;
    
	public ErrorDetails(int errorcode, String errormessage, Date time) {
		super();
		this.errorcode = errorcode;
		this.errormessage = errormessage;
		this.time = time;
	}
	public int getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}
	public String getErrormessage() {
		return errormessage;
	}
	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
    
}
