package cm.abdev.medlapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class PatientTestingKey implements Serializable {

    @Column(name = "patient_id")
    private long patientId;

    @Column(name = "testing_id")
    private long testingId;
}
