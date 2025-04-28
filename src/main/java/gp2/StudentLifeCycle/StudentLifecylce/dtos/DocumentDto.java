package gp2.StudentLifeCycle.StudentLifecylce.dtos;

public class DocumentDto {
    private String name;
    private String url;

    private Long candidateId;

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public String getName() {
        return name;
    }

    public DocumentDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public DocumentDto setUrl(String url) {
        this.url = url;
        return this;
    }

    // Getters and setters
}
