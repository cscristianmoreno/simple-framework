package com.cmorenoweb.servlet;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.cmorenoweb.annotations.servlet.Controller;
import com.cmorenoweb.annotations.servlet.http.DELETE;
import com.cmorenoweb.annotations.servlet.http.GET;
import com.cmorenoweb.annotations.servlet.http.PATCH;
import com.cmorenoweb.annotations.servlet.http.POST;
import com.cmorenoweb.annotations.servlet.http.PUT;
import com.cmorenoweb.utils.ConstructorUtil;
import com.cmorenoweb.utils.annotations.AnnotationUtil;
import com.cmorenoweb.utils.paths.PathComodin;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration.Dynamic;
import jakarta.servlet.annotation.HandlesTypes;

@HandlesTypes(Controller.class)
public class ServletInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> classes, ServletContext context) throws ServletException {
        
        /** Loop for all class */
        for (Class<?> clazz: classes) {
            /** Get a class name */
            String className = clazz.getSimpleName();
            
            try {
                /** Registry a new servlet */
                RegisterServlet registerServlet = new RegisterServlet(clazz);

                /** Add servlet */
                Dynamic dynamic = context.addServlet(className, registerServlet);

                /** Get @Controller annotation */
                Controller controller = clazz.getDeclaredAnnotation(Controller.class);

                /** Registry path */
                dynamic.addMapping(controller.value() + "/*");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
