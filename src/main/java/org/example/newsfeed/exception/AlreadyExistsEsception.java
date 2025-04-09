package org.example.newsfeed.exception;

public class AlreadyExistsEsception extends RuntimeException {
    public AlreadyExistsEsception(String message) {
        super(message);
    }
}
