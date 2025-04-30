package com.cmorenoweb.tomcat;

import java.io.File;

import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.startup.Tomcat;

public class TomcatInitializer {
    
    public void initializer(final int port) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);

        String property = System.getProperty("user.dir");
        File file = new File(property);

        tomcat.addWebapp("", file.getAbsolutePath());

        tomcat.getConnector();

        tomcat.start();
        tomcat.getServer().await();
    }
}
