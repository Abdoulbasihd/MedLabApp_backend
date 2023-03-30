package cm.abdev.medlapp.controller;

import cm.abdev.medlapp.dto.RestResponse;
import cm.abdev.medlapp.dto.STATUS;
import cm.abdev.medlapp.dto.patiesting.PatientTestingDTO;
import cm.abdev.medlapp.exception.MedLAppGeneralException;
import cm.abdev.medlapp.model.PatientTesting;
import cm.abdev.medlapp.service.PatientTestingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cm.abdev.medlapp.util.Utils.getErrorHttpHeaders;

@RestController
@RequestMapping("/api/v1/patient-testing")
public class PatientTestingController {

    private Logger ptLogger = LoggerFactory.getLogger(PatientTestingController.class);

    private PatientTestingService patientTestingService;

    public PatientTestingController(PatientTestingService patientTestingService) {
        this.patientTestingService = patientTestingService;
    }

    //1. testing a patient
    @PostMapping("/new-testing")
    public ResponseEntity createPatientTesting(@RequestBody PatientTestingDTO patientTestingDTO) {

        try {
            PatientTesting patientTesting = patientTestingService.createPatientTesting(patientTestingDTO);

            HttpHeaders httpHeadersPT = new HttpHeaders();
            httpHeadersPT.add("patient-testing", "/api/v1/patient-testing/"+patientTesting.getId());

            RestResponse<PatientTesting> cPatientRestResponse = new RestResponse<>(HttpStatus.CREATED.value(), STATUS.SUCCESS.name(), "Patient successfully created", patientTesting);
            return new ResponseEntity<>(cPatientRestResponse, httpHeadersPT, HttpStatus.CREATED);

        } catch (MedLAppGeneralException e) {
            ptLogger.error(e.getErrorMessage(), e);

            return new ResponseEntity<>(
                    new RestResponse<>(e.getErrorCode(), STATUS.FAILED.name(), e.getErrorMessage()),
                    getErrorHttpHeaders(String.valueOf(e.getErrorCode()), e.getErrorMessage(), e.getLocalizedMessage()),
                    e.getErrorCode()
            );

        }

    }


    //2. get a testing-patient by id


    //3. get a testing-patient by sample code
    @GetMapping("/by-sample-code/{sampleCode}")
    public ResponseEntity getBySampleCode(@PathVariable("sampleCode") String sampleCode) {

        try {

            HttpHeaders httpHeadersPT = new HttpHeaders();
            PatientTestingDTO patientTesting = patientTestingService.getTestingBySampleCode(sampleCode);

            RestResponse<PatientTestingDTO> cPatientRestResponse = new RestResponse<>(HttpStatus.CREATED.value(), STATUS.SUCCESS.name(), "Successful", patientTesting);
            return new ResponseEntity<>(cPatientRestResponse, httpHeadersPT, HttpStatus.CREATED);


        } catch (MedLAppGeneralException e) {
            ptLogger.error(e.getErrorMessage(), e);

            return new ResponseEntity<>(
                    new RestResponse<>(e.getErrorCode(), STATUS.FAILED.name(), e.getErrorMessage()),
                    getErrorHttpHeaders(String.valueOf(e.getErrorCode()), e.getErrorMessage(), e.getLocalizedMessage()),
                    e.getErrorCode()
            );
        }
    }



    //4. get testing-patients by result
    @GetMapping("/by-result/{result}")
    public ResponseEntity getByResult(@PathVariable("result") String result) {

        try {

            HttpHeaders httpHeadersPT = new HttpHeaders();
            List<PatientTestingDTO> patientTesting = patientTestingService.getPatientTestingByResult(result);

            RestResponse<List<PatientTestingDTO>> cPatientRestResponse = new RestResponse<>(HttpStatus.CREATED.value(), STATUS.SUCCESS.name(), "Success", patientTesting);
            return new ResponseEntity<>(cPatientRestResponse, httpHeadersPT, HttpStatus.CREATED);


        } catch (MedLAppGeneralException e) {
            ptLogger.error(e.getErrorMessage(), e);

            return new ResponseEntity<>(
                    new RestResponse<>(e.getErrorCode(), STATUS.FAILED.name(), e.getErrorMessage()),
                    getErrorHttpHeaders(String.valueOf(e.getErrorCode()), e.getErrorMessage(), e.getLocalizedMessage()),
                    e.getErrorCode()
            );
        }
    }

    //5. get test-patients by patient
    @GetMapping("/by-patient/{patientId}")
    public ResponseEntity getByPatientId(@PathVariable("patientId") long patientId) {

        try {

            HttpHeaders httpHeadersPT = new HttpHeaders();
            List<PatientTestingDTO> patientTesting = patientTestingService.getPatientTestingsByPatient(patientId);

            RestResponse<List<PatientTestingDTO>> cPatientRestResponse = new RestResponse<>(HttpStatus.CREATED.value(), STATUS.SUCCESS.name(), "Success", patientTesting);
            return new ResponseEntity<>(cPatientRestResponse, httpHeadersPT, HttpStatus.CREATED);


        } catch (MedLAppGeneralException e) {
            ptLogger.error(e.getErrorMessage(), e);

            return new ResponseEntity<>(
                    new RestResponse<>(e.getErrorCode(), STATUS.FAILED.name(), e.getErrorMessage()),
                    getErrorHttpHeaders(String.valueOf(e.getErrorCode()), e.getErrorMessage(), e.getLocalizedMessage()),
                    e.getErrorCode()
            );
        }
    }

    //TODO 6. get test-patients by patient name
    //TODO 7. get test-patients by patient phone

    //5. get test-patients by patient
    @GetMapping("/by-testing/{testingId}")
    public ResponseEntity getByTestingId(@PathVariable("testingId") long testingId) {

        try {

            HttpHeaders httpHeadersPT = new HttpHeaders();
            List<PatientTestingDTO> patientTesting = patientTestingService.getPatientTestingsByTesting(testingId);

            RestResponse<List<PatientTestingDTO>> cPatientRestResponse = new RestResponse<>(HttpStatus.CREATED.value(), STATUS.SUCCESS.name(), "Success", patientTesting);
            return new ResponseEntity<>(cPatientRestResponse, httpHeadersPT, HttpStatus.CREATED);


        } catch (MedLAppGeneralException e) {
            ptLogger.error(e.getErrorMessage(), e);

            return new ResponseEntity<>(
                    new RestResponse<>(e.getErrorCode(), STATUS.FAILED.name(), e.getErrorMessage()),
                    getErrorHttpHeaders(String.valueOf(e.getErrorCode()), e.getErrorMessage(), e.getLocalizedMessage()),
                    e.getErrorCode()
            );
        }
    }

}
