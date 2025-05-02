package io.github.cscristianmoreno.utils;

import java.lang.reflect.Field;

import io.github.cscristianmoreno.annotations.Component;
import io.github.cscristianmoreno.annotations.Inject;
import io.github.cscristianmoreno.annotations.Injectable;
import io.github.cscristianmoreno.exceptions.injection.InjectionException;

public abstract class InjectionUtil {
    
    /**
     * Inject a dependency in field class
     * @param <T>
     * @param clazz
     * @throws Exception
     */
    public static <T> void inject(T instance, Class<?> clazz) throws Exception {
        /** Get all fields */
        Field[] fields = clazz.getDeclaredFields();

        for (Field field: fields) {
            /** If @Inject annotation don't have present, continue */
            if (!field.isAnnotationPresent(Inject.class)) {
                continue;
            };


            /** Get a field type */
            Class<?> parameterType = field.getType();

            /** If @Injectable annotation don't have present in parameter type, throw exception */
            if (!parameterType.isAnnotationPresent(Injectable.class) && !parameterType.isAnnotationPresent(Component.class)) {
                throw new InjectionException("The class <%s> require @Injectable annotation", field.getType().getSimpleName());
            }
            
            T parameterInstance = ConstructorUtil.instance(parameterType);
            field.setAccessible(true);
            field.set(instance, parameterInstance);
        }
    }
}
