package com.cinema.exception;

public class HashingPasswordException extends RuntimeException {
    public HashingPasswordException(String message, Throwable e) {
        super(message);
    }
}
