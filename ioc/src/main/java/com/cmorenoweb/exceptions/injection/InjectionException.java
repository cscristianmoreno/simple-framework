package com.cmorenoweb.exceptions.injection;

import com.cmorenoweb.exceptions.ExceptionMain;

public class InjectionException extends ExceptionMain {

    public InjectionException(String message, Object... params) {
        super(message, params);
    }
}
