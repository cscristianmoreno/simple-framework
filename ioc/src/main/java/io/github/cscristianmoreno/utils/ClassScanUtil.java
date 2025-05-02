package io.github.cscristianmoreno.utils;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;

import io.github.cscristianmoreno.annotations.Component;
import io.github.cscristianmoreno.annotations.Server;
import io.github.cscristianmoreno.ioc.IOCManager;

public class ClassScanUtil {

    ObjectMapper objectMapper = new ObjectMapper();
    
    public <T> void scan(List<File> files) throws Exception {

        for (File file: files) {
            String path = file.getPath();
            
            String posClassesDirectory = "classes" + File.separator;
            int packagePosition = path.indexOf("classes");
            String pack = path.replace(posClassesDirectory, "").replace(File.separator, ".").substring(packagePosition);
            String packageName = pack.replace(".class", "");

            URLClassLoader loader = URLClassLoader.newInstance(new URL[] {
                file.toURI().toURL()
            });

            Class<?> clazz = loader.loadClass(packageName);

            if (clazz.isAnnotationPresent(Server.class) && ServerUtil.isMainClass(clazz)) {
                Server server = clazz.getAnnotation(Server.class);
                ServerUtil.initializer(server.port());
                continue;
            }

            if (!clazz.isAnnotationPresent(Component.class)) {
                continue;
            }
            
            IOCManager.component().create(clazz);
        }
    }
}
