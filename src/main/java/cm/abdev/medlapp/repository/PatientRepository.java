package cm.abdev.medlapp.repository;

import cm.abdev.medlapp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<List<Patient>> findByNames(String name);
    Optional<List<Patient>> findByPhone(String phone);
    Optional<Patient> findByCode(String code);

}
