package com.cmorenoweb.path;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class pathTest {
    
    @Test
    public void testPath() throws URISyntaxException {
        Path path = Paths.get(getClass().getResource("/").toURI()).getParent();
        System.out.println(path.resolve("classes"));
    }
}
