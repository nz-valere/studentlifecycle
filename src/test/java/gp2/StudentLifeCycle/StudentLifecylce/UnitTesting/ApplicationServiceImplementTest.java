package gp2.StudentLifeCycle.StudentLifecylce.UnitTesting;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import gp2.StudentLifeCycle.StudentLifecylce.dtos.ApplicationDto;
import gp2.StudentLifeCycle.StudentLifecylce.models.Application;
import gp2.StudentLifeCycle.StudentLifecylce.repository.ApplicationRepository;
import gp2.StudentLifeCycle.StudentLifecylce.serviceimpl.ApplicationServiceImplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class ApplicationServiceImplementTest {

    @Mock
    private ApplicationRepository applicationRepository;

    @InjectMocks
    private ApplicationServiceImplement applicationService;

    private Application application;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        application = new Application();
        application.setId(1L);
        application.setCandidate(123L);
        application.setEnrolled(false);
        application.setState(Application.AppState.ONGOING);
        application.setSubmit_date(LocalDate.now());
    }

    @Test
    public void testGetAllApplications() {
        List<Application> applications = new ArrayList<>();
        applications.add(application);
        when(applicationRepository.findAll()).thenReturn(applications);

        List<Application> foundApplications = applicationService.getAllApplications();
        assertEquals(1, foundApplications.size());
        assertEquals(application, foundApplications.get(0));
    }

    @Test
    public void testGetApplicationById() {
        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));

        Optional<Application> foundApplication = applicationService.getApplicationById(1L);
        assertTrue(foundApplication.isPresent());
        assertEquals(application, foundApplication.get());
    }

    @Test
    public void testUpdateApplicationStatus() {
        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));
        when(applicationRepository.save(any(Application.class))).thenReturn(application);

        Application updatedApplication = applicationService.updateApplicationStatus(1L, Application.AppState.ACCEPTED);
        assertEquals(Application.AppState.ACCEPTED, updatedApplication.getState());
        verify(applicationRepository, times(1)).save(application);
    }

    @Test
    public void testUpdateApplication() {
        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));
        when(applicationRepository.save(any(Application.class))).thenReturn(application);

        Application updatedApplication = new Application();
        updatedApplication.setId(1L);
        updatedApplication.setCandidate(456L);
        updatedApplication.setEnrolled(true);
        updatedApplication.setState(Application.AppState.ACCEPTED);
        updatedApplication.setSubmit_date(LocalDate.now());

        Application result = applicationService.updateApplication(1L, updatedApplication);
        assertEquals(updatedApplication.getCandidate(), result.getCandidate());
        verify(applicationRepository, times(1)).save(updatedApplication);
    }

    @Test
    public void testCreateApplication() {
        ApplicationDto appDto = new ApplicationDto();
        appDto.setCandidateId(123L);

        when(applicationRepository.save(any(Application.class))).thenReturn(application);

        Application createdApplication = applicationService.CreateApplication(appDto);
        assertEquals(Application.AppState.ONGOING, createdApplication.getState());
        assertEquals(appDto.getCandidateId(), createdApplication.getCandidate());
        verify(applicationRepository, times(1)).save(any(Application.class));
    }
}