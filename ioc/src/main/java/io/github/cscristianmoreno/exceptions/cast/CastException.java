package io.github.cscristianmoreno.exceptions.cast;

import io.github.cscristianmoreno.exceptions.ExceptionMain;
import io.github.cscristianmoreno.utils.MessageUtil;

public class CastException extends ExceptionMain {

    public CastException(String message, Object... params) {
        super(MessageUtil.message(message, params));
    }
    
}
