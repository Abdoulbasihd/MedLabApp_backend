package cm.abdev.medlapp.service.impl;

import cm.abdev.medlapp.exception.MedLAppGeneralException;
import cm.abdev.medlapp.model.Testing;
import cm.abdev.medlapp.repository.TestingRepository;
import cm.abdev.medlapp.service.TestingService;
import cm.abdev.medlapp.util.CodedMessages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestingServiceImpl implements TestingService {

    private final TestingRepository testingRepository;

    public TestingServiceImpl(TestingRepository testingRepository) {
        this.testingRepository = testingRepository;
    }


    @Override
    public Testing createTesting(Testing testing) throws MedLAppGeneralException {

        if (testing == null)
            throw new MedLAppGeneralException(CodedMessages.PARAM_NULL_NOT_ALLOWED.getCode(), CodedMessages.PARAM_NULL_NOT_ALLOWED.getMessage());

        if (!testing.requiredFilled())
            throw new MedLAppGeneralException(CodedMessages.REQUIRED_PARAM_NOT_SENT.getCode(), CodedMessages.REQUIRED_PARAM_NOT_SENT.getMessage());

        if (this.testingAlreadyRegistered(testing))
            throw new MedLAppGeneralException(CodedMessages.ALREADY_EXIST.getCode(), CodedMessages.ALREADY_EXIST.getMessage());

        testing = testingRepository.save(testing);

        return testing;
    }

    @Override
    public Testing getTestingById(long id) throws MedLAppGeneralException{

        Optional<Testing> optionalTesting =  testingRepository.findById(id);

        if (optionalTesting.isPresent())
            return optionalTesting.get();
        else
            throw new MedLAppGeneralException(CodedMessages.NOT_FOUND.getCode(), CodedMessages.NOT_FOUND.getMessage());

    }

    @Override
    public Testing getTestingByCode(String code) throws MedLAppGeneralException {
        Optional<Testing> optionalTesting =  testingRepository.findByTestingCode(code);

        if (optionalTesting.isPresent())
            return optionalTesting.get();
        else
            throw new MedLAppGeneralException(CodedMessages.NOT_FOUND.getCode(), CodedMessages.NOT_FOUND.getMessage());

    }

    @Override
    public List<Testing> getTestings(String entitled) throws MedLAppGeneralException {
        Optional<List<Testing>> optionalTestings =  testingRepository.findByTestingEntitledLike(entitled);

        if (optionalTestings.isPresent())
            return optionalTestings.get();
        else
            throw new MedLAppGeneralException(CodedMessages.NOT_FOUND.getCode(), CodedMessages.NOT_FOUND.getMessage());

    }

    @Override
    public List<Testing> getTestingByPrice(double price) throws MedLAppGeneralException {
        Optional<List<Testing>> optionalTestings =  testingRepository.findByTestingPrice(price);

        if (optionalTestings.isPresent())
            return optionalTestings.get();
        else
            throw new MedLAppGeneralException(CodedMessages.NOT_FOUND.getCode(), CodedMessages.NOT_FOUND.getMessage());

    }

    @Override
    public Page<Testing> getAllTesting(int page, int size) throws MedLAppGeneralException {
        Pageable pageable = PageRequest.of(page, size);

        try {
            return testingRepository.findAll(pageable);
        } catch (Exception exc) {
            throw new MedLAppGeneralException(500, exc.getMessage());
        }
    }

    private boolean testingAlreadyRegistered(Testing testing) throws MedLAppGeneralException {
        if (testing==null || !testing.requiredFilled()) throw new MedLAppGeneralException(CodedMessages.PARAM_NULL_NOT_ALLOWED.getCode(), CodedMessages.PARAM_NULL_NOT_ALLOWED.getMessage());

        Optional<Testing> optionalTesting = testingRepository.findByTestingCode(testing.getTestingCode());

        return  optionalTesting.isPresent();
    }

}
