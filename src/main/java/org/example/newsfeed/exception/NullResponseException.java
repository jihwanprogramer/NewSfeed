package org.example.newsfeed.exception;

public class NullResponseException extends RuntimeException {
  public NullResponseException(String message) {
    super(message);
  }
}
