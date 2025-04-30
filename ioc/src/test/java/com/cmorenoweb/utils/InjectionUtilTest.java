package com.cmorenoweb.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

import com.cmorenoweb.components.MyComponentInjectable;
import com.cmorenoweb.components.MyComponentToInject;

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
