package gp2.StudentLifeCycle.StudentLifecylce.dtos;

import gp2.StudentLifeCycle.StudentLifecylce.models.Candidate;
import gp2.StudentLifeCycle.StudentLifecylce.models.Level;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class StudentDto {
    private String name;
    private String email;
    private String phone;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate birthdate;

    private Candidate CandidateId;

    private Level level;

    public Level getLevel() {
        return level;
    }

    public StudentDto setLevel(Level level) {
        this.level = level;
        return this;
    }

    public Candidate getCandidateId() {
        return CandidateId;
    }

    public StudentDto setCandidateId(Candidate candidateId) {
        CandidateId = candidateId;
        return this;
    }

    public String getName() {
        return name;
    }

    public StudentDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public StudentDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public StudentDto setPhone(String  phone) {
        this.phone = phone;
        return this;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public StudentDto setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
        return this;
    }
}
