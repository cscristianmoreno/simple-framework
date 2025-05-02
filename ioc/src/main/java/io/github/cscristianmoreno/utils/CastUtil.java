package io.github.cscristianmoreno.utils;

import java.util.Map;
import java.util.function.Function;

public abstract class CastUtil {
    
    public static <T> T cast(Class<?> clazz, String value) {
        
        if (clazz.isAssignableFrom(String.class)) {
            return (T) value;
        }

        Map<Class<?>, Function<String, Object>> mapCast = Map.of(
            int.class, Integer::parseInt,
            Integer.class, Integer::parseInt,
            long.class, Long::parseLong,
            Long.class, Long::parseLong,
            boolean.class, Boolean::parseBoolean,
            Boolean.class, Boolean::parseBoolean
        );

        T result = (T) mapCast.get(clazz).apply(value);

        /** If result don't is assignable from */
        // if (!result.getClass().isAssignableFrom(clazz)) {
        //     throw new CastException("Class %s is not assignable from %s", result.getClass().getSimpleName(), clazz.getSimpleName());
        // }

        return result;
    }
} 
