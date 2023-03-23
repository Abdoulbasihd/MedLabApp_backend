package cm.abdev.medlapp.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestingTest {

    Testing testing;

    @BeforeEach
    void setUp() {
        testing = new Testing();
        testing.setId(0);
        testing.setTestingPrice(100);
        testing.setTestingEntitled("test");
        testing.setTestingCode("TEST");
        testing.setTestingComment("comment");
    }

    @AfterEach
    void tearDown() {
        testing = null;
    }

    @Test
    void requiredFilled() {
        assertTrue(testing.requiredFilled());
    }

    @Test
    void requiredFilled_priceNeg() {
        testing.setTestingPrice(-1);
        assertFalse(testing.requiredFilled());
    }

    @Test
    void requiredFilled_noTitle() {
        testing.setTestingEntitled(null);
        assertFalse(testing.requiredFilled());
    }

    @Test
    void requiredFilled_noCode() {
        testing.setTestingCode(null);
        assertFalse(testing.requiredFilled());
    }
}