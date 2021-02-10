package com.janitha.trafficoffencemanagement.offenceservice.exception;

public class FineNotFoundException extends RuntimeException{

    public FineNotFoundException(String message){
        super(message);
    }
}
