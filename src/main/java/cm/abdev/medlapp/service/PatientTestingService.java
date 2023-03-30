package cm.abdev.medlapp.service;

import cm.abdev.medlapp.dto.patient.PatientDTO;
import cm.abdev.medlapp.dto.patiesting.PatientTestingDTO;
import cm.abdev.medlapp.dto.testing.TestingDTO;
import cm.abdev.medlapp.exception.MedLAppGeneralException;
import cm.abdev.medlapp.model.Patient;
import cm.abdev.medlapp.model.PatientTesting;
import cm.abdev.medlapp.model.Testing;

import java.util.Date;
import java.util.List;

public interface PatientTestingService {

    PatientTestingDTO createPatientTesting(PatientTesting patientTesting, String patientCode, String testingCode) throws MedLAppGeneralException;
    PatientTesting createPatientTesting(PatientTestingDTO patientTestingDTO) throws MedLAppGeneralException;

    PatientTestingDTO getTestingBySampleCode(String sampleCode) throws MedLAppGeneralException;

    List<PatientTestingDTO> getPatientTestingByResult(String result) throws MedLAppGeneralException;
    List<PatientTestingDTO> getPatientTestingByTestingDate(Date date) throws MedLAppGeneralException;

    List<PatientTestingDTO> getPatientTestingsByPatient(long patientId) throws MedLAppGeneralException;
    List<PatientTestingDTO> getPatientTestingsByPatientName(String patientName) throws MedLAppGeneralException;
    List<PatientTestingDTO> getPatientTestingsByPatientPatient(String patientPhone) throws MedLAppGeneralException;

    List<PatientTestingDTO> getPatientTestingsByTesting(long testingId) throws MedLAppGeneralException;
    List<PatientDTO> getPatientByTesting(long testingId) throws MedLAppGeneralException;
    List<TestingDTO> getTestingByPatient(long patient) throws MedLAppGeneralException;

}
