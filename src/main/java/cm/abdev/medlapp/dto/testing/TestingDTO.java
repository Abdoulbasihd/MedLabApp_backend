package cm.abdev.medlapp.dto.testing;

import cm.abdev.medlapp.exception.MedLAppGeneralException;
import cm.abdev.medlapp.model.Testing;
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
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TestingDTO {
    private String tCode;
    private String tEntitled;
    private double tPrice;
    private String tComment;

    public void initFromTesting(Testing testing) throws MedLAppGeneralException {

        if (testing==null)
            throw new MedLAppGeneralException(CodedMessages.PARAM_NULL_NOT_ALLOWED.getCode(), CodedMessages.PARAM_NULL_NOT_ALLOWED.getMessage());

        this.setTEntitled(testing.getTestingEntitled());
        this.setTCode(testing.getTestingCode());
        this.setTPrice(testing.getTestingPrice());
        this.setTComment(testing.getTestingComment());
    }

    public Testing convertToTesting(){
        Testing patient = new Testing();
        patient.setTestingEntitled(this.getTEntitled());
        patient.setTestingCode(this.getTCode());
        patient.setTestingComment(this.getTComment());
        patient.setTestingPrice(this.getTPrice());
        return patient;
    }

    public static List<TestingDTO> convertTestingsToDTO(List<Testing> testings) throws MedLAppGeneralException {
        if (testings ==null)
            throw new MedLAppGeneralException(CodedMessages.PARAM_NULL_NOT_ALLOWED.getCode(), CodedMessages.PARAM_NULL_NOT_ALLOWED.getMessage());


        List<TestingDTO> testingDTOList = new ArrayList<>();

        for (Testing testing : testings) {
            TestingDTO dto = new TestingDTO();
            dto.setTComment(testing.getTestingComment());
            dto.setTPrice(testing.getTestingPrice());
            dto.setTCode(testing.getTestingCode());
            dto.setTEntitled(testing.getTestingEntitled());
            testingDTOList.add(dto);
        }

        return testingDTOList;

    }

    public static Page<TestingDTO> convertTestingsPageToDTO(Page<Testing> testings) throws MedLAppGeneralException {

        if (testings ==null)
            throw new MedLAppGeneralException(CodedMessages.PARAM_NULL_NOT_ALLOWED.getCode(), CodedMessages.PARAM_NULL_NOT_ALLOWED.getMessage());


        List<TestingDTO> patientDTOList = TestingDTO.convertTestingsToDTO(testings.getContent());


        Pageable pageable = PageRequest.of(testings.getPageable().getPageNumber(), testings.getPageable().getPageSize());


        return new PageImpl<>(patientDTOList, pageable, patientDTOList.size());
    }


}
