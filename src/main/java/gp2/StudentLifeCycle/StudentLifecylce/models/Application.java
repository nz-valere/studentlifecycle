package gp2.StudentLifeCycle.StudentLifecylce.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Table(name = "Applications")
@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate submit_date;
    private boolean enrolled;
    @Enumerated(EnumType.STRING)
    private AppState state;

    public enum AppState {
        ONGOING,ACCEPTED,REJECTED
    }

    private Long candidateId;

    @OneToMany
    private List<Document> documents;



    public Long getCandidate() {
        return candidateId;
    }

    public Application setCandidate(Long candidateId) {
        this.candidateId = candidateId;
        return this;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public Application setDocuments(List<Document> documents) {
        this.documents = documents;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Application setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getSubmit_date() {
        return submit_date;
    }

    public Application setSubmit_date(LocalDate submit_date) {
        this.submit_date = submit_date;
        return this;
    }


    public boolean isEnrolled() {
        return enrolled;
    }

    public Application setEnrolled(boolean enrolled) {
        this.enrolled = enrolled;
        return this;
    }

    public AppState getState() {
        return state;
    }

    public Application setState(AppState state) {
        this.state = state;
        return this;
    }





    public Application() {
    }




}
