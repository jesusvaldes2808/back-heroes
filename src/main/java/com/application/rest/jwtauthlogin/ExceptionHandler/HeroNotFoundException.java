package com.application.rest.jwtauthlogin.ExceptionHandler;

public class HeroNotFoundException extends  RuntimeException{

    public HeroNotFoundException(String message){
        super(message);
    }
}
