package cm.abdev.medlapp.repository;

import cm.abdev.medlapp.model.Testing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestingRepository extends JpaRepository<Testing, Long> {

    Optional<Testing> findById(long id);
    Optional<Testing> findByTestingCode(String testingCode);
    Optional<List<Testing>> findByTestingEntitled(String entitled);
    Optional<List<Testing>> findByTestingEntitledLike(String entitled);
    Optional<List<Testing>> findByTestingPrice(double price);
    Optional<List<Testing>> findByTestingPriceAfter(double price);
    Optional<List<Testing>> findByTestingPriceBefore(double price);
    Optional<List<Testing>> findByTestingPriceBetween(double priceInf, double priceSup);

}
