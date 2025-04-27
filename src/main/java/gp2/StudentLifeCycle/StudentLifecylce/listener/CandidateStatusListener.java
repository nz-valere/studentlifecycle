package gp2.StudentLifeCycle.StudentLifecylce.listener;

import gp2.StudentLifeCycle.StudentLifecylce.models.Candidate;
import gp2.StudentLifeCycle.StudentLifecylce.serviceimpl.EmailServiceImplement;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CandidateStatusListener {

    @Autowired
    private EmailServiceImplement emailService;

    /*@PreUpdate
    public void onStatusChange(Candidate candidate) {
        if ("STUDENT".equals(candidate.getStatus())) {
            emailService.sendStatusChangeEmail(candidate);
        }
    }*/
}