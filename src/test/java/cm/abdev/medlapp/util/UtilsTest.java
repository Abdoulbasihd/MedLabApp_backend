package cm.abdev.medlapp.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isPositiveStrictInteger() {
        assertTrue(Utils.isPositiveStrictInteger(10));
    }

    @Test
    void isNotPositiveStrictInteger() {
        assertFalse(Utils.isPositiveStrictInteger(-1));
    }

    @Test
    void getErrorHttpHeaders_nullParams() {
        assertNotNull(Utils.getErrorHttpHeaders(null, null, null));
    }
}