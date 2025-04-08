package org.example.newsfeed.exception;

public class UnauthorizedCommentAccessException extends RuntimeException{
    public UnauthorizedCommentAccessException(String message) {
        super(message);
    }
}
