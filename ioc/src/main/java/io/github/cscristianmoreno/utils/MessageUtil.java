package io.github.cscristianmoreno.utils;

public abstract class MessageUtil {
    public static String message(String message, Object... params) {
        return String.format(message, params);
    }
}
