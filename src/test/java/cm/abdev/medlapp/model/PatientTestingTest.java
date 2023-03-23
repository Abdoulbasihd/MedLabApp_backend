package cm.abdev.medlapp.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientTestingTest {

    PatientTesting patientTesting;

    @BeforeEach
    void setUp() {
        patientTesting = new PatientTesting();
        PatientTestingKey key = new PatientTestingKey();
        key.setPatientId(0);
        key.setTestingId(0);
        patientTesting.setId(key);
    }

    @AfterEach
    void tearDown() {
        patientTesting = null;
    }

    @Test
    void requiredFilled() {
        patientTesting.setSampleCode("CODE");
        patientTesting.setResult("+");
        assertTrue(patientTesting.requiredFilled());
    }

    @Test
    void requiredFilled_sampleNull() {
        patientTesting.setSampleCode(null);
        assertFalse(patientTesting.requiredFilled());
    }

    @Test
    void requiredFilled_sampleEmptyl() {
        patientTesting.setSampleCode("");
        assertFalse(patientTesting.requiredFilled());
    }
}