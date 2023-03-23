package cm.abdev.medlapp.service;

import cm.abdev.medlapp.exception.MedLAppGeneralException;
import cm.abdev.medlapp.model.Patient;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PatientService {

    Patient createPatient(Patient patient) throws MedLAppGeneralException;

    Patient getPatientById(long id) throws MedLAppGeneralException;
    Patient getPatientByCode(String code) throws MedLAppGeneralException;

    List<Patient> getPatientsByName(String names) throws MedLAppGeneralException;
    List<Patient> getPatientsByPhone(String phone) throws MedLAppGeneralException;

    Page<Patient> getAllPatient(int page, int size) throws MedLAppGeneralException;
}
