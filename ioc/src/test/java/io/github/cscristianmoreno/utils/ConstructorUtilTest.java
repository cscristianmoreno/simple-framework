package io.github.cscristianmoreno.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import io.github.cscristianmoreno.components.MyComponentConstructor;

public class ConstructorUtilTest {
    @Test
    void testInstance() throws Exception {
        MyComponentConstructor myComponentToInject = ConstructorUtil.instance(MyComponentConstructor.class);

        assertNotNull(myComponentToInject);
        assertNotNull(myComponentToInject.getMyComponentInjectable());
    }
}
