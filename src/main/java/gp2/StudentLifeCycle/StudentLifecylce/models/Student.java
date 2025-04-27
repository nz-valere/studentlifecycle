package gp2.StudentLifeCycle.StudentLifecylce.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Table(name = "students")
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String  phone;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne
    @JoinColumn(name = "Candidate_id", referencedColumnName = "Id", nullable = false)
    private Candidate CandidateId;

    @ManyToOne
    @JoinColumn(name = "Level_id", referencedColumnName = "Id", nullable = false)
    private Level level;

    public enum Status {
        ENROLLED, GRADUATED, DROPPED
    }

    public Student setStatus(Status status) {
        this.status = status;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public String  getPhone() {
        return phone;
    }
    public Student setPhone(String phone) {
        this.phone = phone;
        return this;
    }
    public LocalDate getBirthdate() {
        return birthdate;
    }
    public Student setBirthdate(LocalDate end) {
        this.birthdate = end;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Candidate getCandidateId() {
        return CandidateId;
    }

    public Student setCandidateId(Candidate candidateId) {
        CandidateId = candidateId;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    public Student setLevel(Level level) {
        this.level = level;
        return this;
    }

    public Student setId(Long id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }
    public Student setName(String name) {
        this.name = name;
        return this;
    }
    public String getEmail() {
        return email;
    }
    public Student setEmail(String email) {
        this.email = email;
        return this;
    }

}
