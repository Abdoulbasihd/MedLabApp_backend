package cm.abdev.medlapp.dto.patient;

import cm.abdev.medlapp.exception.MedLAppGeneralException;
import cm.abdev.medlapp.model.Patient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PatientDTOTest {

    private PatientDTO patientDTO;
    private PatientDTO patientDTO2;
    private Patient patient;
    private List<Patient> patients;

    @BeforeEach
    void setUp() {
        patientDTO = new PatientDTO();
        patientDTO2 = new PatientDTO("TESTER", "KJKDQ_LKA", "677515077");
        patient = new Patient();
        patient.setNames("TESTER ABIM");
        patient.setCode("CODE 008");
        patient.setPhone("694814302");
        patient.setId(1);

        patients = new ArrayList<>();
        patients.add(patient);
        patients.add(patient);
        patients.add(patient);
        patients.add(patient);
        patients.add(patient);

    }

    @AfterEach
    void tearDown() {
        patientDTO = null;
        patientDTO2 = null;
        patient = null;
    }

    @Test
    void initFromPatient() throws MedLAppGeneralException {
        patientDTO.initFromPatient(patient);
        assertEquals(patient.getNames(), patientDTO.getNames());
    }

    @Test
    void initFromPatient_initAllArgs() {
        assertEquals("677515077", patientDTO2.getPhone());

    }

    @Test
    void initFromPatient_paramNull() {
        assertThrows(MedLAppGeneralException.class, ()->patientDTO.initFromPatient(null));
    }

    @Test
    void convertPatientsToDTO_paramNull() {
        assertThrows(MedLAppGeneralException.class, ()-> PatientDTO.convertPatientsToDTO(null));
    }

    @Test
    void convertPatientsToDTO() throws MedLAppGeneralException {
        List<PatientDTO> patientDTOList = PatientDTO.convertPatientsToDTO(patients);
        assertEquals(patients.size(), patientDTOList.size());
    }


}