package cm.abdev.medlapp.service.impl;

import cm.abdev.medlapp.exception.MedLAppGeneralException;
import cm.abdev.medlapp.model.Patient;
import cm.abdev.medlapp.repository.PatientRepository;
import cm.abdev.medlapp.service.PatientService;
import cm.abdev.medlapp.util.CodedMessages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient createPatient(Patient patient) throws MedLAppGeneralException {
        if (patient==null || !patient.requiredFilled())
            throw new MedLAppGeneralException(CodedMessages.PARAM_NULL_NOT_ALLOWED.getCode(), CodedMessages.PARAM_NULL_NOT_ALLOWED.getMessage());

        if (this.patientAlreadyRegistered(patient))
            throw new MedLAppGeneralException(CodedMessages.ALREADY_EXIST.getCode(), CodedMessages.ALREADY_EXIST.getMessage());

        patient = patientRepository.save(patient);

        return patient;
    }

    @Override
    public Patient getPatientById(long id) throws MedLAppGeneralException {
        Optional<Patient> optionalPatient =  patientRepository.findById(id);

        if (optionalPatient.isPresent())
            return optionalPatient.get();
        else
            throw new MedLAppGeneralException(CodedMessages.NOT_FOUND.getCode(), CodedMessages.NOT_FOUND.getMessage());
    }

    @Override
    public Patient getPatientByCode(String code) throws MedLAppGeneralException {
        Optional<Patient> optionalPatient =  patientRepository.findByCode(code);

        if (optionalPatient.isPresent())
            return optionalPatient.get();
        else
            throw new MedLAppGeneralException(CodedMessages.NOT_FOUND.getCode(), CodedMessages.NOT_FOUND.getMessage());    }

    @Override
    public List<Patient> getPatientsByName(String names) throws MedLAppGeneralException {
        Optional<List<Patient>> optionalPatient =  patientRepository.findByNames(names);

        if (optionalPatient.isPresent())
            return optionalPatient.get();
        else
            throw new MedLAppGeneralException(CodedMessages.NOT_FOUND.getCode(), CodedMessages.NOT_FOUND.getMessage());
    }

    @Override
    public List<Patient> getPatientsByPhone(String phone) throws MedLAppGeneralException {
        Optional<List<Patient>> optionalPatient =  patientRepository.findByPhone(phone);

        if (optionalPatient.isPresent())
            return optionalPatient.get();
        else
            throw new MedLAppGeneralException(CodedMessages.NOT_FOUND.getCode(), CodedMessages.NOT_FOUND.getMessage());
    }

    @Override
    public Page<Patient> getAllPatient(int page, int size) throws MedLAppGeneralException {
        Pageable pageable = PageRequest.of(page, size);

        try {
            return patientRepository.findAll(pageable);
        } catch (Exception exc) {
            throw new MedLAppGeneralException(500, exc.getMessage());
        }

    }

    private boolean patientAlreadyRegistered(Patient patient) throws MedLAppGeneralException {
        if (patient==null || !patient.requiredFilled()) throw new MedLAppGeneralException(CodedMessages.PARAM_NULL_NOT_ALLOWED.getCode(), CodedMessages.PARAM_NULL_NOT_ALLOWED.getMessage());

        Optional<Patient> optionalTesting = patientRepository.findByCode(patient.getCode());

        return  optionalTesting.isPresent();
    }


}
