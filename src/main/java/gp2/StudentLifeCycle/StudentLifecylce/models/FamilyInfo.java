package gp2.StudentLifeCycle.StudentLifecylce.models;

import jakarta.persistence.*;

@Table(name = "FamilyInfo")
@Entity
public class FamilyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String dadName;
    private String dadJob;
    private int dadNum;

    private String momName;
    private String momJob;
    private int momNum;

    @Enumerated(EnumType.STRING)
    private ParentStatus parentStatus;

    public enum ParentStatus {
        SINGLE, MARRIED, DIVORCED
    }

    private int siblings;

    private Long candidateId;

    public Long getId() {
        return Id;
    }

    public FamilyInfo setId(Long id) {
        Id = id;
        return this;
    }

    public String getDadName() {
        return dadName;
    }

    public FamilyInfo setDadName(String dadName) {
        this.dadName = dadName;
        return this;
    }

    public String getDadJob() {
        return dadJob;
    }

    public FamilyInfo setDadJob(String dadJob) {
        this.dadJob = dadJob;
        return this;
    }

    public int getDadNum() {
        return dadNum;
    }

    public FamilyInfo setDadNum(int dadNum) {
        this.dadNum = dadNum;
        return this;
    }

    public String getMomName() {
        return momName;
    }

    public FamilyInfo setMomName(String momName) {
        this.momName = momName;
        return this;
    }

    public String getMomJob() {
        return momJob;
    }

    public FamilyInfo setMomJob(String momJob) {
        this.momJob = momJob;
        return this;
    }

    public int getMomNum() {
        return momNum;
    }

    public FamilyInfo setMomNum(int momNum) {
        this.momNum = momNum;
        return this;
    }

    public ParentStatus getParentStatus() {
        return parentStatus;
    }

    public FamilyInfo setParentStatus(ParentStatus parentStatus) {
        this.parentStatus = parentStatus;
        return this;
    }

    public int getSiblings() {
        return siblings;
    }

    public FamilyInfo setSiblings(int siblings) {
        this.siblings = siblings;
        return this;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public FamilyInfo setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
        return this;
    }
}
