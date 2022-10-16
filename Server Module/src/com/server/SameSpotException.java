package com.server;

public class SameSpotException extends Exception{
    public SameSpotException(String errorMessage)
    {
        super(errorMessage);
    }
}
