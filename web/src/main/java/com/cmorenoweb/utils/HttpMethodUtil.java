package com.cmorenoweb.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.List;

import com.cmorenoweb.annotations.servlet.http.GET;
import com.cmorenoweb.annotations.servlet.http.POST;
import com.cmorenoweb.utils.annotations.AnnotationUtil;

import jakarta.servlet.http.HttpServletRequest;

public abstract class HttpMethodUtil {
    
    /** Get method if match with annotation */
    public static Method getMethodIsMatchingWithHttpAndAnnotation(HttpServletRequest request, Method[] methods) {
        /** Conver array method in list */
        List<Method> listMethods = List.of(methods);

        /** Get a request method */
        String httpMethod = request.getMethod();

        /** Get annotation */
        Class<? extends Annotation> annotation = AnnotationUtil.getAnnotation(httpMethod);

        /** Get a new list if annotation present (in the method) matches with this annotation */
        List<Method> newListMethods = listMethods.stream().filter((m) -> m.isAnnotationPresent(annotation)).toList();

        return newListMethods.get(0);
    }
}
