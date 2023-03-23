package cm.abdev.medlapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "patient")
@Setter
@Getter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "names", nullable = false)
    String names;

    @Column(name = "code", unique = true, nullable = false)
    String code;

    String phone;

    //@ManyToMany
    //@JoinTable(name = "patient_testing", joinColumns = @JoinColumn(name = "patient_id"), inverseJoinColumns = @JoinColumn(name = "testing_id"))
    //private Set<Testing> testings;

    @OneToMany(mappedBy = "patient", targetEntity = PatientTesting.class, cascade = CascadeType.ALL)
    private Set<PatientTesting> patientTestings;

    public boolean requiredFilled() {
        return this.getCode()!=null && !this.getCode().isEmpty() && this.getNames()!=null && !this.getNames().isEmpty();
    }

}
