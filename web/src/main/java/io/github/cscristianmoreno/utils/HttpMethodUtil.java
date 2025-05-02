package io.github.cscristianmoreno.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Pattern;

import io.github.cscristianmoreno.dto.AnnotationHttpDTO;
import io.github.cscristianmoreno.utils.annotations.AnnotationUtil;
import io.github.cscristianmoreno.utils.paths.PathComodin;
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
            /** Get first annotation */
            Annotation getFirstAnnotation = m.getAnnotations()[0];
            
            /** Get annotation value */
            AnnotationHttpDTO annotationValue = AnnotationUtil.getAnnotationValue(httpMethod, getFirstAnnotation);

            /** Get path info */
            String pathInfo = request.getPathInfo();

            /** Get pattern */
            Pattern pattern = PathComodin.convert(annotationValue.getValue());
            
            return pattern.matcher(pathInfo).matches();
        })
        .toList();

        if (newListMethods.isEmpty()) {
            return null;
        }

        return newListMethods.get(0);
    }
}
