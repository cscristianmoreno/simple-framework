package io.github.cscristianmoreno.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CastUtilTest {
    @Test
    void testCastSuccess() {
        Integer cast = CastUtil.cast(Integer.class, "1");

        assertNotNull(cast);
        assertTrue(cast.getClass().isAssignableFrom(Integer.class));
    }

    @Test
    void testCastBad() {
        assertThrows(RuntimeException.class, () -> {
            CastUtil.cast(Integer.class, "asdasd");
        });
    }
}
