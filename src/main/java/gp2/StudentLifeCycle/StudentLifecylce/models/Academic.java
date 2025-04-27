package gp2.StudentLifeCycle.StudentLifecylce.models;

import jakarta.persistence.*;

@Table(name = "Academics")
@Entity
public class Academic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "Candidate_id", referencedColumnName = "Id", nullable = false)
    private Long candidateId;

    private boolean haveAL;

    private int gradesAL;

    private int gradesOL;

    private String alYear;

    private String field;

    private String college;

    private String town;

    private int classNum;

    private int classPosition1;

    private int classPosition2;

    private int classPosition3;


    public String getAlYear() {
        return alYear;
    }

    public Academic setAlYear(String alYear) {
        this.alYear = alYear;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Academic setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public Academic setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
        return this;
    }

    public boolean isHaveAL() {
        return haveAL;
    }

    public Academic setHaveAL(boolean haveAL) {
        this.haveAL = haveAL;
        return this;
    }

    public int getGradesAL() {
        return gradesAL;
    }

    public Academic setGradesAL(int gradesAL) {
        this.gradesAL = gradesAL;
        return this;
    }

    public int getGradesOL() {
        return gradesOL;
    }

    public Academic setGradesOL(int gradesOL) {
        this.gradesOL = gradesOL;
        return this;
    }

    public String getField() {
        return field;
    }

    public Academic setField(String field) {
        this.field = field;
        return this;
    }

    public String getCollege() {
        return college;
    }

    public Academic setCollege(String college) {
        this.college = college;
        return this;
    }

    public String getTown() {
        return town;
    }

    public Academic setTown(String town) {
        this.town = town;
        return this;
    }

    public int getClassNum() {
        return classNum;
    }

    public Academic setClassNum(int classNum) {
        this.classNum = classNum;
        return this;
    }

    public int getClassPosition1() {
        return classPosition1;
    }

    public Academic setClassPosition1(int classPosition1) {
        this.classPosition1 = classPosition1;
        return this;
    }

    public int getClassPosition2() {
        return classPosition2;
    }

    public Academic setClassPosition2(int classPosition2) {
        this.classPosition2 = classPosition2;
        return this;
    }

    public int getClassPosition3() {
        return classPosition3;
    }

    public Academic setClassPosition3(int classPosition3) {
        this.classPosition3 = classPosition3;
        return this;
    }
}
