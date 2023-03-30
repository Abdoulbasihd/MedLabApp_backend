package cm.abdev.medlapp.service.impl;


import cm.abdev.medlapp.dto.patient.PatientDTO;
import cm.abdev.medlapp.dto.patiesting.PatientTestingDTO;
import cm.abdev.medlapp.dto.testing.TestingDTO;
import cm.abdev.medlapp.exception.MedLAppGeneralException;
import cm.abdev.medlapp.model.Patient;
import cm.abdev.medlapp.model.PatientTesting;
import cm.abdev.medlapp.model.PatientTestingKey;
import cm.abdev.medlapp.model.Testing;
import cm.abdev.medlapp.repository.PatientTestingRepository;
import cm.abdev.medlapp.service.PatientTestingService;
import cm.abdev.medlapp.util.CodedMessages;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatientTestingServiceImpl implements PatientTestingService {

    private PatientTestingRepository patientTestingRepository;
    private PatientServiceImpl patientService;
    private TestingServiceImpl testingService;

    public PatientTestingServiceImpl(PatientTestingRepository patientTestingRepository, PatientServiceImpl patientService, TestingServiceImpl testingService) {
        this.patientTestingRepository = patientTestingRepository;
        this.patientService = patientService;
        this.testingService = testingService;
    }

    @Override
    public PatientTestingDTO createPatientTesting(PatientTesting patientTesting, String patientCode, String testingCode) throws MedLAppGeneralException {
        if (patientTesting==null || !patientTesting.requiredFilled() || patientCode==null || testingCode==null)
            throw new MedLAppGeneralException(CodedMessages.PARAM_NULL_NOT_ALLOWED.getCode(), CodedMessages.PARAM_NULL_NOT_ALLOWED.getMessage());

        if (patientTestingAlreadyRegistered(patientTesting))
            throw new MedLAppGeneralException(CodedMessages.ALREADY_EXIST.getCode(), CodedMessages.ALREADY_EXIST.getMessage());

        Patient patient = patientService.getPatientByCode(patientCode);
        Testing testing = testingService.getTestingByCode(testingCode);

        PatientTestingKey patientTestingKey = new PatientTestingKey();
        patientTestingKey.setTestingId(testing.getId());
        patientTestingKey.setPatientId(patient.getId());

        patientTesting.setPatient(patient);
        patientTesting.setTesting(testing);
        patientTesting.setId(patientTestingKey);
        //patientTesting.setSampleCode();

        PatientTestingDTO dto = new PatientTestingDTO();
        dto.initFromPatientTesting(patientTestingRepository.save(patientTesting));
        return dto;
    }

    /**
     * <h4>Testing a patient</h4>
     *
     * To do this, we need to :
     * <ol>
     *     <li>Verify if the exam exist</li>
     *     <li>Create a patient</li>
     *     <li>Insert a patient testing</li>
     * </ol>
     */
    @Override
    public PatientTesting createPatientTesting(PatientTestingDTO patientTestingDTO) throws MedLAppGeneralException {
        if (patientTestingDTO==null || patientTestingDTO.getTesting()==null || patientTestingDTO.getTesting().getTCode()==null || patientTestingDTO.getSampleCode()==null)
            throw new MedLAppGeneralException(CodedMessages.PARAM_NULL_NOT_ALLOWED.getCode(), CodedMessages.PARAM_NULL_NOT_ALLOWED.getMessage());

        //1. verify if exam  (testing) exist and get it. if not exist then an exception is thrown
        Testing testing = testingService.getTestingByCode(patientTestingDTO.getTesting().getTCode());

        //2. Insert patient
        Patient patient = patientTestingDTO.getPatient().convertToPatient();
        patient = patientService.createPatient(patient);

        //3. Create PatientTestingKey
        PatientTestingKey patientTestingKey = new PatientTestingKey();
        patientTestingKey.setPatientId(patient.getId());
        patientTestingKey.setTestingId(testing.getId());

        //4. create
        PatientTesting patientTesting = patientTestingDTO.convertToPatientTesting();
        patientTesting.setPatient(patient);
        patientTesting.setTesting(testing);
        patientTesting.setId(patientTestingKey);

        //5. if  all required filled then  save. else throw exception
        if (patientTesting.requiredFilled()) {
            patientTesting =  patientTestingRepository.save(patientTesting);

            return patientTesting;
        }

        throw new MedLAppGeneralException(400, "Sample code is required");

    }

    @Override
    public PatientTestingDTO getTestingBySampleCode(String sampleCode) throws MedLAppGeneralException {
        if (sampleCode==null)
            throw new MedLAppGeneralException(CodedMessages.PARAM_NULL_NOT_ALLOWED.getCode(), CodedMessages.PARAM_NULL_NOT_ALLOWED.getMessage());

        Optional<PatientTesting> patientTestingOptional = patientTestingRepository.findBySampleCode(sampleCode);

        if (patientTestingOptional.isPresent()) {
            PatientTestingDTO dto = new PatientTestingDTO();
            dto.initFromPatientTesting(patientTestingOptional.get());
            return dto;
        }

        throw new MedLAppGeneralException(CodedMessages.NOT_FOUND.getCode(), CodedMessages.NOT_FOUND.getMessage());
    }

    @Override
    public List<PatientTestingDTO> getPatientTestingByResult(String result) throws MedLAppGeneralException {
        if (result==null)
            throw new MedLAppGeneralException(CodedMessages.PARAM_NULL_NOT_ALLOWED.getCode(), CodedMessages.PARAM_NULL_NOT_ALLOWED.getMessage());

        Optional<List<PatientTesting>> optionalPatientTestings = patientTestingRepository.findByResult(result);

        if (optionalPatientTestings.isPresent())
            return PatientTestingDTO.convertPatientTestingsToDTO(optionalPatientTestings.get());

        throw new MedLAppGeneralException(CodedMessages.NOT_FOUND.getCode(), CodedMessages.NOT_FOUND.getMessage());
    }

    @Override
    public List<PatientTestingDTO> getPatientTestingByTestingDate(Date date) throws MedLAppGeneralException {
        if (date==null)
            throw new MedLAppGeneralException(CodedMessages.PARAM_NULL_NOT_ALLOWED.getCode(), CodedMessages.PARAM_NULL_NOT_ALLOWED.getMessage());

        Optional<List<PatientTesting>> optionalPatientTestings = patientTestingRepository.findByTestingDate(date);

        if (optionalPatientTestings.isPresent())
            return PatientTestingDTO.convertPatientTestingsToDTO(optionalPatientTestings.get());

        throw new MedLAppGeneralException(CodedMessages.NOT_FOUND.getCode(), CodedMessages.NOT_FOUND.getMessage());
    }

    @Override
    public List<PatientTestingDTO> getPatientTestingsByPatient(long patientId) throws MedLAppGeneralException {
        Patient patient = patientService.getPatientById(patientId);
        Optional<List<PatientTesting>> optionalPatientTestings = patientTestingRepository.findByPatient(patient);

        if (optionalPatientTestings.isPresent())
            return PatientTestingDTO.convertPatientTestingsToDTO(optionalPatientTestings.get());


        throw new MedLAppGeneralException(CodedMessages.NOT_FOUND.getCode(), CodedMessages.NOT_FOUND.getMessage());
    }

    @Override
    public List<PatientTestingDTO> getPatientTestingsByPatientName(String patientName) throws MedLAppGeneralException {
        return null;
    }

    @Override
    public List<PatientTestingDTO> getPatientTestingsByPatientPatient(String patientPhone) throws MedLAppGeneralException {
        return null;
    }

    @Override
    public List<PatientTestingDTO> getPatientTestingsByTesting(long testingId) throws MedLAppGeneralException {
        Testing testing = testingService.getTestingById(testingId);
        Optional<List<PatientTesting>> optionalPatientTestings = patientTestingRepository.findByTesting(testing);

        if (optionalPatientTestings.isPresent())
            return PatientTestingDTO.convertPatientTestingsToDTO(optionalPatientTestings.get());

        throw new MedLAppGeneralException(CodedMessages.NOT_FOUND.getCode(), CodedMessages.NOT_FOUND.getMessage());
    }

    @Override
    public List<PatientDTO> getPatientByTesting(long testingId) throws MedLAppGeneralException {
        Testing testing = testingService.getTestingById(testingId);
        Optional<List<PatientTesting>> optionalPatientTestings = patientTestingRepository.findByTesting(testing);

        List<Patient> patients = new ArrayList<>();

        if (optionalPatientTestings.isPresent()) {
            List<PatientTesting> patientTestings = optionalPatientTestings.get();

            for (PatientTesting patientTesting : patientTestings) {
                Patient patient = patientTesting.getPatient();
                patients.add(patient);
            }

        }

        return PatientDTO.convertPatientsToDTO(patients);
    }

    @Override
    public List<TestingDTO> getTestingByPatient(long patientId) throws MedLAppGeneralException {
        Patient patient = patientService.getPatientById(patientId);
        Optional<List<PatientTesting>> optionalPatientTestings = patientTestingRepository.findByPatient(patient);

        List<Testing> testings = new ArrayList<>();

        if (optionalPatientTestings.isPresent()) {
            List<PatientTesting> patientTestings = optionalPatientTestings.get();

            for (PatientTesting patientTesting : patientTestings) {
                Testing testing = patientTesting.getTesting();
                testings.add(testing);
            }

        }
        return TestingDTO.convertTestingsToDTO(testings);
    }

    private boolean patientTestingAlreadyRegistered(PatientTesting patientTesting) throws MedLAppGeneralException {
        if (patientTesting==null || !patientTesting.requiredFilled()) throw new MedLAppGeneralException(CodedMessages.PARAM_NULL_NOT_ALLOWED.getCode(), CodedMessages.PARAM_NULL_NOT_ALLOWED.getMessage());

        Optional<PatientTesting> optionalTesting = patientTestingRepository.findBySampleCode(patientTesting.getSampleCode());

        return  optionalTesting.isPresent();
    }
}
