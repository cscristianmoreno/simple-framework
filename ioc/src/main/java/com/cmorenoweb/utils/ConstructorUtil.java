package com.cmorenoweb.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.List;

import com.cmorenoweb.annotations.Component;
import com.cmorenoweb.annotations.Injectable;
import com.cmorenoweb.exceptions.constructor.ConstructorException;


public abstract class ConstructorUtil {
    
    /**
     * Instancite a class and instanciate constructor parameter class that include Injectable annotation
     * @param <T>
     * @param clazz
     * @return
     * @throws Exception
     */
    public static <T> T instance(Class<?> clazz) throws Exception {
        /** Get a class constructors */
        Constructor<?>[] constructors = clazz.getConstructors();

        T instance = null; 

        /** Loop to access all parameters constructor
         * (Is need loop to access a random value in constructor) 
        */
        for (Constructor constructor: constructors) {
            List<Parameter> parameters = List.of(constructor.getParameters());

            /** If parameters constructor is empty, break loop */
            if (parameters.isEmpty()) {
                instance = (T) constructor.newInstance();
                break;
            }

            /** Create a array object with parameters size */
            Object[] values = new Object[parameters.size()];

            int i = 0;

            for (Parameter parameter: parameters) {
                Class<?> parameterType = parameter.getType();

                /** If parameter type is assignable from class type, send exception */
                if (parameterType.isAssignableFrom(clazz)) {
                    throw new ConstructorException("The parameter type %s is assignable from %s", parameterType.getSimpleName(), clazz.getSimpleName());
                }

                /** If is annotation @Injectable don't have present in parameter class, set null value */
                if (!parameterType.isAnnotationPresent(Injectable.class) && !parameterType.isAnnotationPresent(Component.class)) {
                    values[i] = null;
                    i++;
                    continue;
                }

                /** Create a parameter class instance */
                T parameterInstance = ConstructorUtil.instance(parameterType);
                values[i] = parameterInstance;
                i++;
            }
            
            /** Create a class instance */
            instance = (T) constructor.newInstance(values);
        }

        /** Find in fields class with @Inject annotations to inject dependencie */
        InjectionUtil.inject(instance, clazz);

        return instance;
    }
}
