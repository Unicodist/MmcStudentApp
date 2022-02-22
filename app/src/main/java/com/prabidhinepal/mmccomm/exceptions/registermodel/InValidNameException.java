package com.prabidhinepal.mmccomm.exceptions.registermodel;

public class InValidNameException extends Exception{
    public InValidNameException() {
        super("That name is not valid");
    }

    public InValidNameException(String message) {
        super(message);
    }

    public InValidNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public InValidNameException(Throwable cause) {
        super(cause);
    }
}
