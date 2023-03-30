package cm.abdev.medlapp.dto.patiesting;

import cm.abdev.medlapp.dto.patient.PatientDTO;
import cm.abdev.medlapp.dto.testing.TestingDTO;
import cm.abdev.medlapp.exception.MedLAppGeneralException;
import cm.abdev.medlapp.model.PatientTesting;
import cm.abdev.medlapp.util.CodedMessages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientTestingDTO {
    private PatientDTO patient;
    private TestingDTO testing;

    private String sampleCode;
    private String result;
    private Date testingDate;

    public void initFromPatientTesting(PatientTesting patientTesting) throws MedLAppGeneralException {

        if (patientTesting==null)
            throw new MedLAppGeneralException(CodedMessages.PARAM_NULL_NOT_ALLOWED.getCode(), CodedMessages.PARAM_NULL_NOT_ALLOWED.getMessage());

        PatientDTO pDto = new PatientDTO();
        pDto.initFromPatient(patientTesting.getPatient());
        this.setPatient(pDto);

        TestingDTO tDto = new TestingDTO();
        tDto.initFromTesting(patientTesting.getTesting());
        this.setTesting(tDto)
        ;
        this.setSampleCode(patientTesting.getSampleCode());
        this.setResult(patientTesting.getResult());
        this.setTestingDate(patientTesting.getTestingDate());
    }

    public PatientTesting convertToPatientTesting(){
        PatientTesting patientTesting = new PatientTesting();
        patientTesting.setTesting(this.getTesting().convertToTesting());
        patientTesting.setPatient(this.getPatient().convertToPatient());
        patientTesting.setResult(this.getResult());
        patientTesting.setTestingDate(this.getTestingDate());
        patientTesting.setSampleCode(this.getSampleCode());
        return patientTesting;
    }

    public static List<PatientTestingDTO> convertPatientTestingsToDTO(List<PatientTesting> patientTestings) throws MedLAppGeneralException {
        if (patientTestings ==null)
            throw new MedLAppGeneralException(CodedMessages.PARAM_NULL_NOT_ALLOWED.getCode(), CodedMessages.PARAM_NULL_NOT_ALLOWED.getMessage());


        List<PatientTestingDTO> patientTestingDTOList = new ArrayList<>();

        for (PatientTesting patientTesting : patientTestings) {
            PatientTestingDTO pTestingDTO = new PatientTestingDTO();
            pTestingDTO.setTestingDate(patientTesting.getTestingDate());
            pTestingDTO.setResult(patientTesting.getResult());
            pTestingDTO.setSampleCode(patientTesting.getSampleCode());

            PatientDTO pDto = new PatientDTO();
            pDto.initFromPatient(patientTesting.getPatient());
            pTestingDTO.setPatient(pDto);

            TestingDTO tDto = new TestingDTO();
            tDto.initFromTesting(patientTesting.getTesting());
            pTestingDTO.setTesting(tDto);

            patientTestingDTOList.add(pTestingDTO);
        }

        return patientTestingDTOList;

    }

    public static Page<PatientTestingDTO> convertPatientTestingsPageToDTO(Page<PatientTesting> patientTestings) throws MedLAppGeneralException {

        if (patientTestings ==null)
            throw new MedLAppGeneralException(CodedMessages.PARAM_NULL_NOT_ALLOWED.getCode(), CodedMessages.PARAM_NULL_NOT_ALLOWED.getMessage());


        List<PatientTestingDTO> patientTestingDTOS = PatientTestingDTO.convertPatientTestingsToDTO(patientTestings.getContent());


        Pageable pageable = PageRequest.of(patientTestings.getPageable().getPageNumber(), patientTestings.getPageable().getPageSize());


        return new PageImpl<>(patientTestingDTOS, pageable, patientTestingDTOS.size());

    }


}
