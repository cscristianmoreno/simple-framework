package io.github.cscristianmoreno.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;

import io.github.cscristianmoreno.annotations.servlet.methods.Body;
import io.github.cscristianmoreno.annotations.servlet.methods.Header;
import io.github.cscristianmoreno.annotations.servlet.methods.Param;
import io.github.cscristianmoreno.annotations.servlet.methods.Variable;
import io.github.cscristianmoreno.utils.values.PathValueUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;


/** Set a controller methods parameter values  */
public abstract class HttpParameterUtil {
    /**
     * @param method
     * @param request
     * @return
     * @throws Exception 
     */
    public static Object[] setParameter(Method method, HttpServletRequest request, String annotationValue) throws Exception {

        /** Get from method all parameters */
        Parameter[] parameters = method.getParameters();

        /** Get a request method */
        String requestMethod = request.getMethod();

        /** Get a path info */
        String pathInfo = request.getPathInfo();

        /** If method parameters is empty, return null  */
        if (parameters.length == 0) {
            return null;
        }
        
        /** Get a paths values  */
        List<String> paths = PathValueUtil.getPathsValue(pathInfo, annotationValue);

        int pathInt = 0;
        int i = 0;

        Object[] methodParams = new Object[parameters.length];

        /** Loop for all parameters */
        for (Parameter parameter: parameters) {
            /** Get a parameter type */
            Class<?> type = parameter.getType();

            /** Get a parameter name */
            String parameterName = parameter.getName();

            /** If annotation @Variable is present in the parameter method */
            if (parameter.isAnnotationPresent(Variable.class)) {
                /** If paths is empty or @Variable annotations is greater than paths size, set default value */
                if (paths.isEmpty() || pathInt >= paths.size()) {
                    /** If parameter type is primitive, cast type and set value 0, else set null value */
                    methodParams[i] = (type.isPrimitive()) ? CastUtil.cast(type, "0") : null;
                    i++;
                    continue;
                }

                /** Get path value from list string */
                String pathValue = paths.get(pathInt);
                
                /** Cast value according to path value */
                methodParams[i] = CastUtil.cast(type, pathValue); 
                pathInt++;
            }
            /** If @Param annotation is present in the parameter method */
            else if (parameter.isAnnotationPresent(Param.class)) {
                Param paramAnnotation = parameter.getAnnotation(Param.class);
                String annoValue = paramAnnotation.value();
                String pathValue = request.getParameter(parameterName);

                /** If pathValue is null or isBlank or isEmpty, continue */
                if (pathValue == null || pathValue.isBlank() || pathValue.isEmpty()) {
                    /** Cast value according to parametert type */
                    methodParams[i] = setDefaultNullValue(type);
                    i++;
                    continue;
                }

                /** if @Param annotation is empty */
                if (annoValue.isEmpty()) {
                    /** Cast value according to parameter type */
                    methodParams[i] = setDefaultNullValue(type);
                    i++;
                    continue;
                }

                methodParams[i] = CastUtil.cast(type, request.getParameter(annoValue));
            }
            /** If @Body annotation is present in the method */
            else if (parameter.isAnnotationPresent(Body.class)) {
                /** If request method matches with GET or DELETE, send exception   */
                if (requestMethod == "GET" || requestMethod == "DELETE") {
                    throw new ServletException("@Body annotation is only allowed in POST, PUT or PATCH method");
                }

                /** Convert buffer reader in JSON string object */
                String body = IOUtils.toString(request.getReader());
                methodParams[i] = JsonUtil.stringToObject(type, body);
            }
            else if (parameter.isAnnotationPresent(Header.class)) {
                Header annoHeader = parameter.getAnnotation(Header.class);
                String value = annoHeader.value();

                if (value.isEmpty() || value == null || value.isBlank()) {
                    methodParams[i] = setDefaultNullValue(type);
                    i++;
                    continue;
                }
                
                if (value.isEmpty()) {
                    methodParams[i] = CastUtil.cast(type, request.getHeader(parameterName));
                }
                else {
                    methodParams[i] = CastUtil.cast(type, request.getHeader(value));
                }
            }
            
            i++;
        }

        return methodParams;
    }

    private static Object setDefaultNullValue(Class<?> type) {
        return (type.isPrimitive()) ? CastUtil.cast(type, "0") : null;
    }
}
