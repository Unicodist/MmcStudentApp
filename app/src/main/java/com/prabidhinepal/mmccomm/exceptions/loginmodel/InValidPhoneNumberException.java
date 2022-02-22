package com.prabidhinepal.mmccomm.exceptions.loginmodel;

import androidx.annotation.Nullable;

public class InValidPhoneNumberException extends Exception{
    public InValidPhoneNumberException(String message){
        super(message);
    }
    public InValidPhoneNumberException(String message, Throwable cause){
        super(message,cause);
    }
    public InValidPhoneNumberException(){
        super("The phone number is not valid");
    }
    @Nullable
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
