package io.github.cscristianmoreno.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

import javax.lang.model.element.AnnotationValue;

import io.github.cscristianmoreno.annotations.servlet.http.GET;
import io.github.cscristianmoreno.annotations.servlet.http.POST;
import io.github.cscristianmoreno.utils.annotations.AnnotationUtil;
import io.github.cscristianmoreno.utils.paths.PathComodin;
import io.github.cscristianmoreno.utils.values.PathValueUtil;

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
        List<Method> newListMethods = listMethods.stream().filter((m) -> {
            return m.isAnnotationPresent(annotation);
        })
        .filter((m) -> {
            Annotation getFirstAnnotation = m.getAnnotations()[0];
            String annotationValue = AnnotationUtil.getAnnotationValue(httpMethod, getFirstAnnotation);
            String pathInfo = request.getPathInfo();
            Pattern pattern = PathComodin.convert(annotationValue);
            return pattern.matcher(pathInfo).matches();
        })
        .toList();

        if (newListMethods.isEmpty()) {
            return null;
        }

        return newListMethods.get(0);
    }
}
