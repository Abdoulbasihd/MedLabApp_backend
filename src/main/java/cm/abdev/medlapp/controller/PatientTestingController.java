package cm.abdev.medlapp.controller;

import cm.abdev.medlapp.dto.RestResponse;
import cm.abdev.medlapp.dto.STATUS;
import cm.abdev.medlapp.dto.patiesting.PatientTestingDTO;
import cm.abdev.medlapp.dto.testing.TestingDTO;
import cm.abdev.medlapp.exception.MedLAppGeneralException;
import cm.abdev.medlapp.model.PatientTesting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cm.abdev.medlapp.util.Utils.getErrorHttpHeaders;

@RestController
@RequestMapping("/api/v1/patient-testing")
public class PatientTestingController {

    private Logger ptLogger = LoggerFactory.getLogger(PatientTestingController.class);


    /*@PostMapping("/new")
    public ResponseEntity createPatientTesting(@RequestBody PatientTestingDTO patientTestingDTO){

        try {
            PatientTesting patientTesting = testingService.createTesting(testingDTO.convertToTesting());
            TestingDTO createdTesting = new TestingDTO();
            createdTesting.initFromTesting(cTesting);

            HttpHeaders httpHeadersCP = new HttpHeaders();
            httpHeadersCP.add("testing", "/api/v1/testing/"+cTesting.getId());

            RestResponse<TestingDTO> cPatientRestResponse = new RestResponse<>(HttpStatus.CREATED.value(), STATUS.SUCCESS.name(), "Patient successfully created", createdTesting);
            return new ResponseEntity<>(cPatientRestResponse, httpHeadersCP, HttpStatus.CREATED);

        } catch (MedLAppGeneralException medLAppGenExc) {
            ptLogger.error(medLAppGenExc.getErrorMessage(), medLAppGenExc);
            return new ResponseEntity<>(
                    new RestResponse<>(medLAppGenExc.getErrorCode(), STATUS.FAILED.name(), medLAppGenExc.getErrorMessage()),
                    getErrorHttpHeaders(String.valueOf(medLAppGenExc.getErrorCode()), medLAppGenExc.getErrorMessage(), medLAppGenExc.getLocalizedMessage()),
                    medLAppGenExc.getErrorCode()
            );

        }
    }*/


}
