package com.cmorenoweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.runtime.ObjectMethods;
import java.util.List;
import java.util.regex.Pattern;

import org.codehaus.jackson.map.ObjectMapper;

import com.cmorenoweb.annotations.servlet.Controller;
import com.cmorenoweb.annotations.servlet.methods.Param;
import com.cmorenoweb.annotations.servlet.methods.Variable;
import com.cmorenoweb.factory.SingletonFactory;
import com.cmorenoweb.utils.CastUtil;
import com.cmorenoweb.utils.ConstructorUtil;
import com.cmorenoweb.utils.HttpMethodUtil;
import com.cmorenoweb.utils.HttpParameterUtil;
import com.cmorenoweb.utils.annotations.AnnotationUtil;
import com.cmorenoweb.utils.paths.PathComodin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
    private Object instance = null;
    private final Method[] methods;
    private Controller controller;

    private ObjectMapper objectMapper = SingletonFactory.get(ObjectMapper.class);

    public RegisterServlet(final Class<?> clazz) throws Exception {
        instance = ConstructorUtil.instance(clazz);
        methods = clazz.getDeclaredMethods();
        controller = clazz.getAnnotation(Controller.class);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Method method = HttpMethodUtil.getMethodIsMatchingWithHttpAndAnnotation(request, methods);
        try {
            sendResponse(method, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private <T> void sendResponse(final Method method, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        /** Get a request method */
        String requestMethod = request.getMethod();

        /** Get annotations */
        List<Annotation> annotations = AnnotationUtil.getAnnotations(method.getDeclaredAnnotations());

        /** Get a first annotation value */
        String annotationValue = AnnotationUtil.getAnnotationValue(requestMethod, annotations.get(0));

        /** Get a request uri */
        String uri = request.getRequestURI();
        
        /** Convert prefix path in regex pattern */
        Pattern pattern = PathComodin.convert(controller.value() + annotationValue);
        
        PrintWriter printWriter = response.getWriter();
        
        /** If regex patter value don't match uri */
        if (!pattern.matcher(uri).matches()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            printWriter.println("URI " + uri + " is not available.");
            return;
        }

        /** Set a method parameter values */
        Object[] params = HttpParameterUtil.setParameter(method, request, annotationValue);

        /** Set a accessible method from another package */
        method.setAccessible(true);

        /** Invoke a method */
        T result = (T) method.invoke(instance, params);

        response.setContentType("application/json");
        printWriter.print(objectMapper.writeValueAsString(result));
    }   

}
