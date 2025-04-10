package org.example.newsfeed.common.handler;

import lombok.Getter;

@Getter
public class GlobalFieldError {
    private String field;
    private String message;

    public GlobalFieldError() {
    }

    public GlobalFieldError(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
