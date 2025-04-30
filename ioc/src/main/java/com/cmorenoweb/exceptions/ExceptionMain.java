package com.cmorenoweb.exceptions;

import com.cmorenoweb.utils.MessageUtil;

public class ExceptionMain extends RuntimeException {
    
    public ExceptionMain(String message, Object... params) {
        super(MessageUtil.message(message, params));
    }
}
