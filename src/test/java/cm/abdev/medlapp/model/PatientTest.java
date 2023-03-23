package cm.abdev.medlapp.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    Patient patient;

    @BeforeEach
    void setUp() {
        patient = new Patient();
        patient.setCode("Code");
        patient.setNames("Names");
        patient.setPhone("677515077");
        patient.setId(0);
    }

    @AfterEach
    void tearDown() {
        patient = null;
    }

    @Test
    void requiredFilled() {
        assertTrue(patient.requiredFilled());
    }

    @Test
    void requiredFilled_codeNull() {
        patient.setCode(null);
        assertFalse(patient.requiredFilled());
    }

    @Test
    void requiredFilled_codeEmpty() {
        patient.setCode("");
        assertFalse(patient.requiredFilled());
    }

    @Test
    void requiredFilled_namesNull() {
        patient.setNames(null);
        assertFalse(patient.requiredFilled());
    }

    @Test
    void requiredFilled_namesEmpty() {
        patient.setNames("");
        assertFalse(patient.requiredFilled());
    }

}