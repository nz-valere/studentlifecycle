package gp2.StudentLifeCycle.StudentLifecylce.serviceimpl;

import gp2.StudentLifeCycle.StudentLifecylce.dtos.ApplicationDto;
import gp2.StudentLifeCycle.StudentLifecylce.models.Application;
import gp2.StudentLifeCycle.StudentLifecylce.models.Candidate;
import gp2.StudentLifeCycle.StudentLifecylce.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceImplement {
    @Autowired
    private ApplicationRepository applicationRepository;

    public List<Application> getAllApplications() {
        return (List<Application>) applicationRepository.findAll();
    }

    // Get application by ID
    public Optional<Application> getApplicationById(Long id) {
        return applicationRepository.findById(id);
    }


    public Application updateApplicationStatus(Long id, Application.AppState status) {
        return applicationRepository.findById(id)
                .map(application -> {
                    application.setState(status);
                    return applicationRepository.save(application);
                })
                .orElseThrow(() -> new RuntimeException("Candidate not found"));
    }


    public Application updateApplication(Long id, Application updateApplication) {
        return applicationRepository.findById(id)
                .map(existingapp -> {
                    existingapp.setId(updateApplication.getId());
                    existingapp.setCandidate(updateApplication.getCandidate());
                    existingapp.setEnrolled(updateApplication.isEnrolled());
                    existingapp.setState(updateApplication.getState());
                    existingapp.setSubmit_date(updateApplication.getSubmit_date());

                    return applicationRepository.save(updateApplication);
                })
                .orElseThrow(() -> new RuntimeException("Application not found"));
    }

    public Application CreateApplication(ApplicationDto appDto) {
            Application app = new Application();
            app.setState(Application.AppState.ONGOING);
            app.setSubmit_date(LocalDate.now());
            app.setEnrolled(false);
            app.setCandidate(appDto.getCandidateId());
            return applicationRepository.save(app);
    }

}
