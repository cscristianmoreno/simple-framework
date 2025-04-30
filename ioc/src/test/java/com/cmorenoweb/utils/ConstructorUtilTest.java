package com.cmorenoweb.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Constructor;

import org.junit.jupiter.api.Test;

import com.cmorenoweb.components.MyComponentConstructor;
import com.cmorenoweb.components.MyComponentToInject;

public class ConstructorUtilTest {
    @Test
    void testInstance() throws Exception {
        MyComponentConstructor myComponentToInject = ConstructorUtil.instance(MyComponentConstructor.class);

        assertNotNull(myComponentToInject);
        assertNotNull(myComponentToInject.getMyComponentInjectable());
    }
}
