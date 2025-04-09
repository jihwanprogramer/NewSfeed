package org.example.newsfeed.exception;

public class SelfFollowNotAllowedException extends RuntimeException {
    public SelfFollowNotAllowedException(String message) {
        super(message);
    }
}
