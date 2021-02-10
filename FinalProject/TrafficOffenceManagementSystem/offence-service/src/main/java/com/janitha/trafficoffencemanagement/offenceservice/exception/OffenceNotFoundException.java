package com.janitha.trafficoffencemanagement.offenceservice.exception;

public class OffenceNotFoundException extends RuntimeException{

    public OffenceNotFoundException(String message){
        super(message);
    }

}
