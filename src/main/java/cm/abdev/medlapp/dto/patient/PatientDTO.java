package cm.abdev.medlapp.dto.patient;

import cm.abdev.medlapp.exception.MedLAppGeneralException;
import cm.abdev.medlapp.model.Patient;
import cm.abdev.medlapp.util.CodedMessages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {

    String names;
    String code;
    String phone;


    public void initFromPatient(Patient patient) throws MedLAppGeneralException {

        if (patient==null)
            throw new MedLAppGeneralException(CodedMessages.PARAM_NULL_NOT_ALLOWED.getCode(), CodedMessages.PARAM_NULL_NOT_ALLOWED.getMessage());

        this.setNames(patient.getNames());
        this.setCode(patient.getCode());
        this.setPhone(patient.getPhone());
    }

    public Patient convertToPatient(){
        Patient patient = new Patient();
        patient.setNames(this.getNames());
        patient.setPhone(this.getPhone());
        patient.setCode(this.getCode());
        return patient;
    }

    public static  List<PatientDTO> convertPatientsToDTO(List<Patient> patients) throws MedLAppGeneralException {
        if (patients ==null)
            throw new MedLAppGeneralException(CodedMessages.PARAM_NULL_NOT_ALLOWED.getCode(), CodedMessages.PARAM_NULL_NOT_ALLOWED.getMessage());


        List<PatientDTO> patientDTOList = new ArrayList<>();

        for (Patient patient : patients) {
            patientDTOList.add(new PatientDTO(patient.getNames(), patient.getCode(), patient.getPhone()));
        }

        return patientDTOList;

    }

    public static  Page<PatientDTO> convertPatientsPageToDTO(Page<Patient> patients) throws MedLAppGeneralException {

        if (patients ==null)
            throw new MedLAppGeneralException(CodedMessages.PARAM_NULL_NOT_ALLOWED.getCode(), CodedMessages.PARAM_NULL_NOT_ALLOWED.getMessage());


        List<PatientDTO> patientDTOList = PatientDTO.convertPatientsToDTO(patients.getContent());


        Pageable pageable = PageRequest.of(patients.getPageable().getPageNumber(), patients.getPageable().getPageSize());


        return new PageImpl<>(patientDTOList, pageable, patientDTOList.size());
    }

}
