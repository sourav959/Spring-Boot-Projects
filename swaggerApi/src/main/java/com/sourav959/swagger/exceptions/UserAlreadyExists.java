package com.sourav959.swagger.exceptions;

public class UserAlreadyExists extends RuntimeException{
    public UserAlreadyExists(String error){
        super(error);
    }
}
