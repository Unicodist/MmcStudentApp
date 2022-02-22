package com.prabidhinepal.mmccomm.exceptions.loginmodel;

public class InValidPassWordException extends Exception{
    private String message;
    public InValidPassWordException(String message){
        this.message=message;
    }
    public InValidPassWordException(){
        this.message = "The password is not valid";
    }
    @Override
    public String getMessage(){
        return this.message;
    }
}
