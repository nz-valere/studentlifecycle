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
    private byte[] url;

    public enum Doctype {
        CV,BirthCertificate,coverLetter
    }
    private Long applicationId;


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

    public byte[] getUrl() {
        return url;
    }



    public Document setUrl(byte[] url) {
        this.url = url;
        return this;
    }

}
