package io.github.cscristianmoreno.utils.annotations;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import io.github.cscristianmoreno.annotations.servlet.http.DELETE;
import io.github.cscristianmoreno.annotations.servlet.http.GET;
import io.github.cscristianmoreno.annotations.servlet.http.PATCH;
import io.github.cscristianmoreno.annotations.servlet.http.POST;
import io.github.cscristianmoreno.annotations.servlet.http.PUT;
import io.github.cscristianmoreno.dto.AnnotationHttpDTO;

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

    public static AnnotationHttpDTO getAnnotationValue(String method, Annotation annotation) {
        AnnotationHttpDTO annotationHttpDTO = new AnnotationHttpDTO();

        Map<String, Function<Annotation, AnnotationHttpDTO>> map = Map.of(
            "GET", a -> {
                GET anno = (GET) a;
                annotationHttpDTO.setValue(anno.value());
                annotationHttpDTO.setProduce(anno.produce());
                annotationHttpDTO.setConsume(anno.consume());
                return annotationHttpDTO;
            },
            "POST", a -> {
                POST anno = (POST) a;
                annotationHttpDTO.setValue(anno.value());
                annotationHttpDTO.setProduce(anno.produce());
                annotationHttpDTO.setConsume(anno.consume());
                return annotationHttpDTO;
            },
            "PUT", a -> {
                PUT anno = (PUT) a;
                annotationHttpDTO.setValue(anno.value());
                annotationHttpDTO.setProduce(anno.produce());
                annotationHttpDTO.setConsume(anno.consume());
                return annotationHttpDTO;
            },
            "PATCH", a -> {
                PATCH anno = (PATCH) a;
                annotationHttpDTO.setValue(anno.value());
                annotationHttpDTO.setProduce(anno.produce());
                annotationHttpDTO.setConsume(anno.consume());
                return annotationHttpDTO;
            },
            "DELETE", a -> {
                DELETE anno = (DELETE) a;
                annotationHttpDTO.setValue(anno.value());
                annotationHttpDTO.setProduce(anno.produce());
                annotationHttpDTO.setConsume(anno.consume());
                return annotationHttpDTO;
            }
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
