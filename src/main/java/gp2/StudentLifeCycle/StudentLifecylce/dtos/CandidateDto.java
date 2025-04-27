package gp2.StudentLifeCycle.StudentLifecylce.dtos;

import gp2.StudentLifeCycle.StudentLifecylce.models.Application;
import gp2.StudentLifeCycle.StudentLifecylce.models.Candidate;
import gp2.StudentLifeCycle.StudentLifecylce.models.Level;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class CandidateDto {
    private String name;
    private String email;

    private String  phone;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate birthdate;
    private String cni;

    private String sex;

    private String Nationality;

    private String birthplace;

    private String region;

    private String Address;

    private String options;

    private Level level;

    public String getOptions() {
        return options;
    }

    public CandidateDto setOptions(String options) {
        this.options = options;
        return this;
    }

    public Candidate.Status getStatus() {
        return status;
    }

    public CandidateDto setStatus(Candidate.Status status) {
        this.status = status;
        return this;
    }

    public String getNationality() {
        return Nationality;
    }

    public CandidateDto setNationality(String nationality) {
        Nationality = nationality;
        return this;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public CandidateDto setBirthplace(String birthplace) {
        this.birthplace = birthplace;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public CandidateDto setRegion(String region) {
        this.region = region;
        return this;
    }

    public String getAddress() {
        return Address;
    }

    public CandidateDto setAddress(String address) {
        Address = address;
        return this;
    }

    //private List<DocumentDto> documents;

    @Enumerated(EnumType.STRING)
    private Candidate.Status status;

    public Candidate.Status getState() {
        return status;
    }

    public CandidateDto setState(Candidate.Status status) {
        this.status = status;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public CandidateDto setSex(String sex) {
        this.sex = sex;
        return this;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String  phone) {
        this.phone = phone;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    /*public List<DocumentDto> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentDto> documents) {
        this.documents = documents;
    }*/
}
