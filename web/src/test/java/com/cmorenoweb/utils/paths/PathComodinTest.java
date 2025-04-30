package com.cmorenoweb.utils.paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import com.cmorenoweb.utils.paths.PathComodin;
import com.cmorenoweb.utils.values.PathValueUtil;

import jakarta.servlet.http.HttpServletRequest;

public class PathComodinTest {
    
    @Test
    public void convertTest() {
        String path = "/users/{id}/test";
        Pattern pattern = PathComodin.convert(path);

        boolean match = pattern.matcher("/users/1/test").matches();

        System.err.println(path + " - " + pattern);

        assertNotNull(pattern);
        assertTrue(match);
    }
}
