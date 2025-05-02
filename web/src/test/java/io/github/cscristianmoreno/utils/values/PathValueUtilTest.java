package io.github.cscristianmoreno.utils.values;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

public class PathValueUtilTest {
    @Test
    void testGetPathsValue() {
        String annotationValue = "/users/value";
        String pathValue = "/users/value";

        List<String> wildcards = PathValueUtil.getPathsValue(pathValue, annotationValue);

        assertNotNull(wildcards);
    }
}
