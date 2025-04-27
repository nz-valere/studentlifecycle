package gp2.StudentLifeCycle.StudentLifecylce.models;

import jakarta.persistence.*;

@Table(name = "licences")
@Entity
public class Licence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Candidate_id", referencedColumnName = "Id", nullable = false)
    private Candidate CandidateId;

    private String University;

    private String field;

    private String Speciality;

    public Long getId() {
        return id;
    }

    public Licence setId(Long id) {
        this.id = id;
        return this;
    }

    public Candidate getCandidateId() {
        return CandidateId;
    }

    public Licence setCandidateId(Candidate candidateId) {
        CandidateId = candidateId;
        return this;
    }

    public String getUniversity() {
        return University;
    }

    public Licence setUniversity(String university) {
        University = university;
        return this;
    }

    public String getField() {
        return field;
    }

    public Licence setField(String field) {
        this.field = field;
        return this;
    }

    public String getSpeciality() {
        return Speciality;
    }

    public Licence setSpeciality(String speciality) {
        Speciality = speciality;
        return this;
    }
}
