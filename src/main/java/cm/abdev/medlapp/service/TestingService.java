package cm.abdev.medlapp.service;

import cm.abdev.medlapp.exception.MedLAppGeneralException;
import cm.abdev.medlapp.model.Testing;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TestingService {
    Testing createTesting(Testing testing) throws MedLAppGeneralException;

    Testing getTestingById(long id) throws MedLAppGeneralException;
    Testing getTestingByCode(String code) throws MedLAppGeneralException;

    List<Testing> getTestings(String entitled) throws MedLAppGeneralException;
    List<Testing> getTestingByPrice(double price) throws MedLAppGeneralException;

    Page<Testing> getAllTesting(int page, int size) throws MedLAppGeneralException;

}
