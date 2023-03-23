package cm.abdev.medlapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class PatientTesting {
    @EmbeddedId
    PatientTestingKey id;

    @ManyToOne
    @MapsId("patientId")
    @JoinColumn(name = "patient_id")
    Patient patient;

    @ManyToOne
    @MapsId("testingId")
    @JoinColumn(name = "testing_id")
    Testing testing;

    @Column(name = "sample_code", unique = true, nullable = false)
    private String sampleCode;

    private String result;

    @Temporal(TemporalType.DATE)
    @Column(name = "testing_date", nullable = false)
    private Date testingDate;


    public boolean requiredFilled() {
        return this.getSampleCode()!=null && !this.getSampleCode().isEmpty();
    }
}
