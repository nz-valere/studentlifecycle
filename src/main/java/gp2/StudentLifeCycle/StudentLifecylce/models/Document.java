package gp2.StudentLifeCycle.StudentLifecylce.models;

import jakarta.persistence.*;

@Table(name = "documents")
@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Doctype doctype;
    @Lob
    private String url;

    public enum Doctype {
        CV,BirthCertificate,coverLetter
    }
    private Long applicationId;

    private Long candidateId;

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }


    public Long getApplication() {
        return applicationId;
    }

    public Document setApplication(Long applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Document setName(String name) {
        this.name = name;
        return this;
    }


    public Doctype getDoctype() {
        return doctype;
    }

    public Document setDoctype(Doctype doctype) {
        this.doctype = doctype;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
