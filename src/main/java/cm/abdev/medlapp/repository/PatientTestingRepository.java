package cm.abdev.medlapp.repository;

import cm.abdev.medlapp.model.Patient;
import cm.abdev.medlapp.model.PatientTesting;
import cm.abdev.medlapp.model.PatientTestingKey;
import cm.abdev.medlapp.model.Testing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PatientTestingRepository extends JpaRepository<PatientTesting, PatientTestingKey> {

    Optional<List<PatientTesting>> findByPatient(Patient patient);
    Optional<List<PatientTesting>> findByTesting(Testing testing);
    Optional<List<PatientTesting>> findByResult(String result);
    Optional<List<PatientTesting>> findByTestingDate(Date date);

    Optional<PatientTesting> findBySampleCode(String code);

}
