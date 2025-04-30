package com.cmorenoweb.utils.annotations;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.cmorenoweb.annotations.servlet.http.DELETE;
import com.cmorenoweb.annotations.servlet.http.GET;
import com.cmorenoweb.annotations.servlet.http.PATCH;
import com.cmorenoweb.annotations.servlet.http.POST;
import com.cmorenoweb.annotations.servlet.http.PUT;

public abstract class AnnotationUtil {
    
    public static Class<? extends Annotation> getAnnotation(String annotation) {
        Map<String, Class<?>> mapList = Map.of(
            "GET", GET.class,
            "POST", POST.class,
            "PUT", PUT.class,
            "PATCH", PATCH.class,
            "DELETE", DELETE.class
        );

        return (Class<? extends Annotation>) mapList.get(annotation);
    }

    public static String getAnnotationValue(String method, Annotation annotation) {
        Map<String, Function<Annotation, String>> map = Map.of(
            "GET", a -> ((GET) a).value(),
            "POST", a -> ((POST) a).value(),
            "PUT", a -> ((PUT) a).value(),
            "PATCH", a -> ((PATCH) a).value(),
            "DELETE", a -> ((DELETE) a).value()
        );

        return map.get(method).apply(annotation);
    }

    public static List<Annotation> getAnnotations(Annotation[] annotations) {
        List<Annotation> listAnnotations = List.of(annotations);

        return listAnnotations.stream().filter((a) -> {
            return a.annotationType().isAssignableFrom(GET.class)
            || a.annotationType().isAssignableFrom(POST.class)
            || a.annotationType().isAssignableFrom(PUT.class)
            || a.annotationType().isAssignableFrom(PATCH.class)
            || a.annotationType().isAssignableFrom(DELETE.class);
        }).toList();
    }
}
