package io.github.cscristianmoreno.exceptions.constructor;

import io.github.cscristianmoreno.exceptions.ExceptionMain;

public class ConstructorException extends ExceptionMain {

    public ConstructorException(String message, Object... params) {
        super(message, params);
    }
}
