package io.github.cscristianmoreno.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

import io.github.cscristianmoreno.components.MyComponentInjectable;
import io.github.cscristianmoreno.components.MyComponentToInject;

public class InjectionUtilTest {
    @Test
    void testInject() throws Exception {
        MyComponentToInject myComponentToInject = new MyComponentToInject();
        Class<?> clazz = myComponentToInject.getClass();


        InjectionUtil.inject(myComponentToInject, clazz);

        Field field = clazz.getDeclaredField("myComponentInjectable");

        assertNotNull(field);
        assertTrue(field.getType().isAssignableFrom(MyComponentInjectable.class));
    }
}
