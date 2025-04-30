package com.cmorenoweb.utils;

import java.lang.reflect.Constructor; 

import com.cmorenoweb.exceptions.constructor.ConstructorException;
import com.cmorenoweb.models.ioc.IComponentUtil;

public class ComponentUtil implements IComponentUtil {
    
    /**
     * Create a component and inject dependencies
     * @return void
     * @throws Exception
     */
    @Override
    public <T> void create(Class<?> clazz) throws Exception {
        Constructor<?>[] constructors = clazz.getConstructors();
            
        /** Component class is required a unique constructor */
        if (constructors.length > 1) {
            throw new ConstructorException("The class component <%s> required a unique constructor", clazz.getName());
        }

        /** Find all parameters class with @Injectable annotation to inject dependencie */
        ConstructorUtil.instance(clazz);
    }
}