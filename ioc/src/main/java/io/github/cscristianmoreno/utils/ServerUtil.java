package io.github.cscristianmoreno.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.apache.catalina.LifecycleException;

import io.github.cscristianmoreno.annotations.Server;
import io.github.cscristianmoreno.tomcat.TomcatInitializer;

public abstract class ServerUtil {
    
    public static boolean isMainClass(final Class<?> clazz) {

        Method[] methods = clazz.getDeclaredMethods();

        boolean isMainClass = false;

        for (Method method: methods) {
            if (!method.getName().equals("main")) {
                continue;
            }

            Parameter[] parameters = method.getParameters();

            if (parameters.length != 1) {
                continue;
            }

            if (!parameters[0].getType().isAssignableFrom(String[].class)) {
                continue;
            }

            isMainClass = true;
        }

        return isMainClass;
    }

    public static void initializer(int port) throws LifecycleException {
        TomcatInitializer tomcatInitializer = new TomcatInitializer();
        tomcatInitializer.initializer(port);
    }
}
