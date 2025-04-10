package org.example.newsfeed.exception;

public class MisMatchPasswordException extends RuntimeException {
    public MisMatchPasswordException(String message) {
        super(message);
    }
}
