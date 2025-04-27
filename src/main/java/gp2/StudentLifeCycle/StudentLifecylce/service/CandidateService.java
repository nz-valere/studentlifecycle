package gp2.StudentLifeCycle.StudentLifecylce.service;

import gp2.StudentLifeCycle.StudentLifecylce.models.Candidate;

public interface CandidateService {
    Candidate findByemail(String email);
    Candidate saveCandidate(Candidate user);
}
