package cm.abdev.medlapp.controller;

import cm.abdev.medlapp.dto.RestResponse;
import cm.abdev.medlapp.dto.STATUS;
import cm.abdev.medlapp.dto.patient.PatientDTO;
import cm.abdev.medlapp.exception.MedLAppGeneralException;
import cm.abdev.medlapp.model.Patient;
import cm.abdev.medlapp.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static cm.abdev.medlapp.util.Utils.getErrorHttpHeaders;


@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private PatientService patientService;

    private Logger logger = LoggerFactory.getLogger(PatientController.class);

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/")
    public ResponseEntity<RestResponse<Page<PatientDTO>>> getAllPatients(@RequestParam  int pageNumber, @RequestParam int pageSize) {

        try {
            Page<Patient> patients = patientService.getAllPatient(pageNumber, pageSize);

            RestResponse<Page<PatientDTO>> pagePatientsRestResponse = new RestResponse<>(HttpStatus.CREATED.value(), STATUS.SUCCESS.name(), "Patient successfully gotten...", PatientDTO.convertPatientsPageToDTO(patients));

            return new ResponseEntity<>(pagePatientsRestResponse,  new HttpHeaders(), HttpStatus.CREATED);

        } catch (MedLAppGeneralException medLAppGenExc) {
            logger.error(medLAppGenExc.getErrorMessage(), medLAppGenExc);
            return new ResponseEntity<>(
                    new RestResponse<>(medLAppGenExc.getErrorCode(), STATUS.FAILED.name(), medLAppGenExc.getErrorMessage()),
                    getErrorHttpHeaders(String.valueOf(medLAppGenExc.getErrorCode()), medLAppGenExc.getErrorMessage(), medLAppGenExc.getLocalizedMessage()),
                    medLAppGenExc.getErrorCode()
            );
        }
    }

    @PostMapping("/new")
    public ResponseEntity<RestResponse<PatientDTO>> createPatient(@RequestBody PatientDTO patientDTO) {

        try {
            Patient patient = patientService.createPatient(patientDTO.convertToPatient());
            PatientDTO createdPatient = new PatientDTO();
            createdPatient.initFromPatient(patient);

            HttpHeaders httpHeadersCP = new HttpHeaders();
            httpHeadersCP.add("patient", "/api/v1/patient/"+patient.getId());

            RestResponse<PatientDTO> cPatientRestResponse = new RestResponse<>(HttpStatus.CREATED.value(), STATUS.SUCCESS.name(), "Patient successfully created", createdPatient);
            return new ResponseEntity<>(cPatientRestResponse, httpHeadersCP, HttpStatus.CREATED);

        } catch (MedLAppGeneralException medLAppGenExc) {
            logger.error(medLAppGenExc.getErrorMessage(), medLAppGenExc);
            return new ResponseEntity<>(
                    new RestResponse<>(medLAppGenExc.getErrorCode(), STATUS.FAILED.name(), medLAppGenExc.getErrorMessage()),
                    getErrorHttpHeaders(String.valueOf(medLAppGenExc.getErrorCode()), medLAppGenExc.getErrorMessage(), medLAppGenExc.getLocalizedMessage()),
                    medLAppGenExc.getErrorCode()
            );

        }

    }

    //todo update and delete

}
