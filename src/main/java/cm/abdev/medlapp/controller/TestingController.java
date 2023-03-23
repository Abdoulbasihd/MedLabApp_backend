package cm.abdev.medlapp.controller;

import cm.abdev.medlapp.dto.RestResponse;
import cm.abdev.medlapp.dto.STATUS;
import cm.abdev.medlapp.dto.testing.TestingDTO;
import cm.abdev.medlapp.exception.MedLAppGeneralException;
import cm.abdev.medlapp.model.Testing;
import cm.abdev.medlapp.service.TestingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cm.abdev.medlapp.util.Utils.getErrorHttpHeaders;

@RestController
@RequestMapping("/api/v1/testings")
public class TestingController {

    private TestingService testingService;
    private Logger tcLogger = LoggerFactory.getLogger(TestingService.class);


    public TestingController(TestingService testingService) {
        this.testingService = testingService;
    }

    @GetMapping("/")
    ResponseEntity getAllTestings(@RequestParam int pageNumber, @RequestParam int pageSize) {

        try {
            Page<Testing> testingPage = testingService.getAllTesting(pageNumber, pageSize);

            RestResponse<Page<Testing>> pagePatientsRestResponse = new RestResponse<>(HttpStatus.CREATED.value(), STATUS.SUCCESS.name(), "Patient successfully gotten...", testingPage);

            return new ResponseEntity<>(pagePatientsRestResponse,  new HttpHeaders(), HttpStatus.CREATED);

        } catch (MedLAppGeneralException e) {
            tcLogger.error(e.getErrorMessage(), e);
            return new ResponseEntity<>(
                    new RestResponse<>(e.getErrorCode(), STATUS.FAILED.name(), e.getErrorMessage()),
                    getErrorHttpHeaders(String.valueOf(e.getErrorCode()), e.getErrorMessage(), e.getLocalizedMessage()),
                    e.getErrorCode()
            );
        }
    }

    @GetMapping("/entitle/{title}")
    ResponseEntity getTestings(@PathVariable String title){

        try {
            List<Testing> testingPage = testingService.getTestings(title);

            RestResponse<List<Testing>> pagePatientsRestResponse = new RestResponse<>(HttpStatus.OK.value(), STATUS.SUCCESS.name(), "Patient successfully gotten...", testingPage);

            return new ResponseEntity<>(pagePatientsRestResponse,  new HttpHeaders(), HttpStatus.OK);

        } catch (MedLAppGeneralException e) {
            tcLogger.error(e.getErrorMessage(), e);
            return new ResponseEntity<>(
                    new RestResponse<>(e.getErrorCode(), STATUS.FAILED.name(), e.getErrorMessage()),
                    getErrorHttpHeaders(String.valueOf(e.getErrorCode()), e.getErrorMessage(), e.getLocalizedMessage()),
                    e.getErrorCode()
            );
        }
    }

    @GetMapping("/price/{price}")
    ResponseEntity getTestingsByPrice(@PathVariable double price){

        try {
            List<Testing> testingPage = testingService.getTestingByPrice(price);

            RestResponse<List<Testing>> pagePatientsRestResponse = new RestResponse<>(HttpStatus.OK.value(), STATUS.SUCCESS.name(), "Patient successfully gotten...", testingPage);

            return new ResponseEntity<>(pagePatientsRestResponse,  new HttpHeaders(), HttpStatus.OK);

        } catch (MedLAppGeneralException e) {
            tcLogger.error(e.getErrorMessage(), e);
            return new ResponseEntity<>(
                    new RestResponse<>(e.getErrorCode(), STATUS.FAILED.name(), e.getErrorMessage()),
                    getErrorHttpHeaders(String.valueOf(e.getErrorCode()), e.getErrorMessage(), e.getLocalizedMessage()),
                    e.getErrorCode()
            );
        }
    }

    @GetMapping("/code/{code}")
    ResponseEntity getTestingsByCode(@PathVariable String code){

        try {
            Testing testing = testingService.getTestingByCode(code);

            return this.getResponseEntityInDTO(testing);

        } catch (MedLAppGeneralException e) {
            tcLogger.error(e.getErrorMessage(), e);
            return new ResponseEntity<>(
                    new RestResponse<>(e.getErrorCode(), STATUS.FAILED.name(), e.getErrorMessage()),
                    getErrorHttpHeaders(String.valueOf(e.getErrorCode()), e.getErrorMessage(), e.getLocalizedMessage()),
                    e.getErrorCode()
            );
        }
    }

    @GetMapping("/id/{id}")
    ResponseEntity getTestingsById(@PathVariable long id){

        try {
            Testing testing = testingService.getTestingById(id);
            return this.getResponseEntityInDTO(testing);

        } catch (MedLAppGeneralException e) {
            tcLogger.error(e.getErrorMessage(), e);
            return new ResponseEntity<>(
                    new RestResponse<>(e.getErrorCode(), STATUS.FAILED.name(), e.getErrorMessage()),
                    getErrorHttpHeaders(String.valueOf(e.getErrorCode()), e.getErrorMessage(), e.getLocalizedMessage()),
                    e.getErrorCode()
            );
        }
    }

    @PostMapping("/new")
    public ResponseEntity<RestResponse<TestingDTO>> createPatient(@RequestBody TestingDTO testingDTO) {

        try {
            Testing cTesting = testingService.createTesting(testingDTO.convertToTesting());
            TestingDTO createdTesting = new TestingDTO();
            createdTesting.initFromTesting(cTesting);

            HttpHeaders httpHeadersCP = new HttpHeaders();
            httpHeadersCP.add("testing", "/api/v1/testing/"+cTesting.getId());

            RestResponse<TestingDTO> cPatientRestResponse = new RestResponse<>(HttpStatus.CREATED.value(), STATUS.SUCCESS.name(), "Patient successfully created", createdTesting);
            return new ResponseEntity<>(cPatientRestResponse, httpHeadersCP, HttpStatus.CREATED);

        } catch (MedLAppGeneralException medLAppGenExc) {
            tcLogger.error(medLAppGenExc.getErrorMessage(), medLAppGenExc);
            return new ResponseEntity<>(
                    new RestResponse<>(medLAppGenExc.getErrorCode(), STATUS.FAILED.name(), medLAppGenExc.getErrorMessage()),
                    getErrorHttpHeaders(String.valueOf(medLAppGenExc.getErrorCode()), medLAppGenExc.getErrorMessage(), medLAppGenExc.getLocalizedMessage()),
                    medLAppGenExc.getErrorCode()
            );

        }

    }


    private ResponseEntity getResponseEntityInDTO(Testing testing) throws MedLAppGeneralException {
        TestingDTO dto = new TestingDTO();
        dto.initFromTesting(testing);

        RestResponse<TestingDTO> pagePatientsRestResponse = new RestResponse<>(HttpStatus.CREATED.value(), STATUS.SUCCESS.name(), "Patient successfully gotten...", dto);

        return new ResponseEntity<>(pagePatientsRestResponse,  new HttpHeaders(), HttpStatus.CREATED);
    }
}
