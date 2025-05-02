package io.github.cscristianmoreno.exceptions.injection;

import io.github.cscristianmoreno.exceptions.ExceptionMain;

public class InjectionException extends ExceptionMain {

    public InjectionException(String message, Object... params) {
        super(message, params);
    }
}
