package io.github.cscristianmoreno.servlet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Stream;

import org.apache.catalina.LifecycleException;

import io.github.cscristianmoreno.annotations.IgnoreController;
import io.github.cscristianmoreno.annotations.Server;
import io.github.cscristianmoreno.annotations.servlet.Controller;
import io.github.cscristianmoreno.utils.MessageUtil;
import io.github.cscristianmoreno.utils.ServerUtil;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration.Dynamic;
import jakarta.servlet.annotation.HandlesTypes;

@HandlesTypes({
    Controller.class,
    IgnoreController.class
})
public class ServletInitializer implements ServletContainerInitializer {

    private final Logger logger = Logger.getLogger(ServletInitializer.class.getSimpleName());

    @Override
    public void onStartup(Set<Class<?>> classes, ServletContext context) throws ServletException {
        HashSet<String> paths = new HashSet<>();

        if (classes == null) {
            logger.warning("Controllers not has been detected!");
            return;
        }

        /** Loop for all class */
        for (Class<?> clazz: classes) {
            /** Get a class name */
            String className = clazz.getSimpleName();

            if (clazz.isAnnotationPresent(IgnoreController.class)) {
                continue;
            }
            
            try {
                /** Registry a new servlet */
                RegisterServlet registerServlet = new RegisterServlet(clazz);

                /** Add servlet */
                Dynamic dynamic = context.addServlet(className, registerServlet);

                /** Get @Controller annotation */
                Controller controller = clazz.getDeclaredAnnotation(Controller.class); 

                String pathValue = controller.value();
                
                Stream<String> items = paths.stream().filter((p) -> p.equals(pathValue));
                
                if (items.count() > 0) {
                    throw new ServletException(MessageUtil.message("Controller %s already register!", pathValue));
                }
                
                logger.info(MessageUtil.message("Controller %s has been register!", pathValue));
                paths.add(pathValue);
                dynamic.addMapping(pathValue + "/*");
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
