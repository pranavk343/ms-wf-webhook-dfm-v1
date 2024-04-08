package com.whirlpool.webhook.exception;

public class BaseException extends Exception{
    public BaseException(String message) {super(message);}
    public BaseException(Throwable cause){super(cause);}
    public BaseException(String message, Throwable cause){super(message,cause);}
}
