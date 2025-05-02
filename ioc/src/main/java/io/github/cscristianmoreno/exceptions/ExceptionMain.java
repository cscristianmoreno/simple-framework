package io.github.cscristianmoreno.exceptions;

import io.github.cscristianmoreno.utils.MessageUtil;

public class ExceptionMain extends RuntimeException {
    
    public ExceptionMain(String message, Object... params) {
        super(MessageUtil.message(message, params));
    }
}
