package io.github.cscristianmoreno.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;

import io.github.cscristianmoreno.annotations.servlet.Controller;
import io.github.cscristianmoreno.factory.SingletonFactory;
import io.github.cscristianmoreno.utils.ConstructorUtil;
import io.github.cscristianmoreno.utils.HttpMethodUtil;
import io.github.cscristianmoreno.utils.HttpParameterUtil;
import io.github.cscristianmoreno.utils.annotations.AnnotationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
    private Object instance = null;
    private final Method[] methods;

    private ObjectMapper objectMapper = SingletonFactory.get(ObjectMapper.class);

    public RegisterServlet(final Class<?> clazz) throws Exception {
        instance = ConstructorUtil.instance(clazz);
        methods = clazz.getDeclaredMethods();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Method method = HttpMethodUtil.getMethodIsMatchingWithHttpAndAnnotation(request, methods);

        String uri = request.getPathInfo();

        if (method == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().println("URI " + uri + " is not available.");
            return;
        }

        try {
            sendResponse(method, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> void sendResponse(final Method method, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        /** Get a request method */
        String requestMethod = request.getMethod();

        /** Get annotations */
        List<Annotation> annotations = AnnotationUtil.getAnnotations(method.getDeclaredAnnotations());

        /** Get a first annotation value */
        String annotationValue = AnnotationUtil.getAnnotationValue(requestMethod, annotations.get(0));
        
        PrintWriter printWriter = response.getWriter();

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
