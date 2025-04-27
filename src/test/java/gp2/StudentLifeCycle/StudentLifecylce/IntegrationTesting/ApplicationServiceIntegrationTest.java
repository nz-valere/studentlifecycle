package gp2.StudentLifeCycle.StudentLifecylce.IntegrationTesting;

import static org.assertj.core.api.Assertions.assertThat;

import gp2.StudentLifeCycle.StudentLifecylce.dtos.ApplicationDto;
import gp2.StudentLifeCycle.StudentLifecylce.models.Application;
import gp2.StudentLifeCycle.StudentLifecylce.repository.ApplicationRepository;
import gp2.StudentLifeCycle.StudentLifecylce.serviceimpl.ApplicationServiceImplement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test") // Ensures that each test runs in a transaction that is rolled back after the test
public class ApplicationServiceIntegrationTest {

    @Autowired
    private ApplicationServiceImplement applicationService;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    public void testCreateAndRetrieveApplication() {
        // Create a new ApplicationDto
        ApplicationDto appDto = new ApplicationDto();
        appDto.setCandidateId(123L);

        // Create the application
        Application createdApplication = applicationService.CreateApplication(appDto);

        // Retrieve the application by ID
        Optional<Application> foundApplication = applicationService.getApplicationById(createdApplication.getId());

        // Assertions
        assertThat(foundApplication).isPresent();
        assertThat(foundApplication.get().getCandidate()).isEqualTo(appDto.getCandidateId());
        assertThat(foundApplication.get().getState()).isEqualTo(Application.AppState.ONGOING);
    }

    @Test
    public void testUpdateApplicationStatus() {
        // Create a new ApplicationDto
        ApplicationDto appDto = new ApplicationDto();
        appDto.setCandidateId(123L);

        // Create the application
        Application createdApplication = applicationService.CreateApplication(appDto);

        // Update the application status
        Application updatedApplication = applicationService.updateApplicationStatus(createdApplication.getId(), Application.AppState.ACCEPTED);

        // Assertions
        assertThat(updatedApplication.getState()).isEqualTo(Application.AppState.ACCEPTED);
    }

    @Test
    public void testUpdateApplication() {
        // Create a new ApplicationDto
        ApplicationDto appDto = new ApplicationDto();
        appDto.setCandidateId(123L);

        // Create the application
        Application createdApplication = applicationService.CreateApplication(appDto);

        // Prepare an updated application
        Application updatedApplication = new Application();
        updatedApplication.setId(createdApplication.getId());
        updatedApplication.setCandidate(456L);
        updatedApplication.setEnrolled(true);
        updatedApplication.setState(Application.AppState.ACCEPTED);
        updatedApplication.setSubmit_date(LocalDate.now());

        // Update the application
        Application result = applicationService.updateApplication(createdApplication.getId(), updatedApplication);

        // Assertions
        assertThat(result.getCandidate()).isEqualTo(updatedApplication.getCandidate());
        assertThat(result.isEnrolled()).isTrue();
        assertThat(result.getState()).isEqualTo(Application.AppState.ACCEPTED);
    }

   /* @Test
    public void testGetAllApplications() {
        // Create a new ApplicationDto
        ApplicationDto appDto1 = new ApplicationDto();
        appDto1.setCandidateId(123L);
        applicationService.CreateApplication(appDto1);

        ApplicationDto appDto2 = new ApplicationDto();
        appDto2.setCandidateId(456L);
        applicationService.CreateApplication(appDto2);

        // Retrieve all applications
        List<Application> applications = applicationService.getAllApplications();

        // Assertions
        assertThat(applications).hasSize(2);
    }*/
}
