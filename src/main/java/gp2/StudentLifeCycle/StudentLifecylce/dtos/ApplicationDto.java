package gp2.StudentLifeCycle.StudentLifecylce.dtos;

public class ApplicationDto {
    private Long CandidateId;

    public Long getCandidateId() {
        return CandidateId;
    }

    public ApplicationDto setCandidateId(Long candidateId) {
        CandidateId = candidateId;
        return this;
    }
}
