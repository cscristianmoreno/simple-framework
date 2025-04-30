package com.cmorenoweb.exceptions.constructor;

import com.cmorenoweb.exceptions.ExceptionMain;

public class ConstructorException extends ExceptionMain {

    public ConstructorException(String message, Object... params) {
        super(message, params);
    }
}
