package com.janitha.trafficoffencemanagement.offenceservice.dto;

import com.janitha.trafficoffencemanagement.model.offenceservice.Fine;

import java.util.List;

public class FineResponse {

    private int statusCode;
    private String message;
    private Object data;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
