package com.example.execrisespringsecurity.API;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
