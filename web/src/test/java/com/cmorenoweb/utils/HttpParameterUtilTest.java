package com.cmorenoweb.utils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.junit.jupiter.api.Test;

import com.cmorenoweb.annotations.servlet.methods.Variable;
import com.cmorenoweb.controllers.ControllerTest;

import jakarta.servlet.http.HttpServletRequest;

public class HttpParameterUtilTest {
    @Test
    void testSetParameterVariable() throws Exception {
        String annotationValue = "/users/{value}/test";
        String pathInfo = "/users/1/test";

        HttpServletRequest request = mock(HttpServletRequest.class);
        
        when(request.getPathInfo()).thenReturn(pathInfo);

        ControllerTest controllerTest = new ControllerTest();

        Method method = controllerTest.getClass().getDeclaredMethods()[0];

        Object[] parameters = HttpParameterUtil.setParameter(method, request, annotationValue);
        method.invoke(controllerTest, parameters);
    }
}
