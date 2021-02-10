package com.janitha.trafficoffencemanagement.driverservice.exception;

import java.util.Date;

public class ErrorDetails {

    private Date timeStamp;
    private int responseCode;
    private String message;
    private String details;

    public ErrorDetails(){

    }

    public ErrorDetails(Date timeStamp, int responseCode, String message, String details){
        this.timeStamp = timeStamp;
        this.responseCode = responseCode;
        this.message = message;
        this.details = details;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
