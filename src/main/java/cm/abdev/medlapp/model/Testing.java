package cm.abdev.medlapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "testing")
@Setter
@Getter
public class Testing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "code", unique = true, nullable = false)
    private String testingCode;

    @Column(name = "entitled", nullable = false)
    private String testingEntitled;

    @Column(name = "price", nullable = false)
    private double testingPrice;

    @Column(name = "comment")
    private String testingComment;

    @OneToMany(mappedBy = "testing", targetEntity = PatientTesting.class, cascade = CascadeType.ALL)
    private Set<PatientTesting> patientTestings;

    public boolean requiredFilled() {
        return this.getTestingCode()!=null && this.getTestingEntitled()!=null && this.getTestingPrice()>=0;
    }
}
