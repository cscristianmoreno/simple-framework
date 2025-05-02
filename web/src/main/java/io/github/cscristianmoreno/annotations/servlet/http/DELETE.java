package io.github.cscristianmoreno.annotations.servlet.http;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface DELETE {
    String value() default "";
    
    String accept() default "";

    String consume() default "";
}
