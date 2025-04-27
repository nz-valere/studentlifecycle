package gp2.StudentLifeCycle.StudentLifecylce.UnitTesting;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import gp2.StudentLifeCycle.StudentLifecylce.dtos.CandidateDto;
import gp2.StudentLifeCycle.StudentLifecylce.models.Candidate;
import gp2.StudentLifeCycle.StudentLifecylce.models.Level;
import gp2.StudentLifeCycle.StudentLifecylce.models.Student;
import gp2.StudentLifeCycle.StudentLifecylce.repository.ApplicationRepository;
import gp2.StudentLifeCycle.StudentLifecylce.repository.CandidateRepository;
import gp2.StudentLifeCycle.StudentLifecylce.repository.StudentRepository;
import gp2.StudentLifeCycle.StudentLifecylce.serviceimpl.CandidateServiceImplement;
import gp2.StudentLifeCycle.StudentLifecylce.serviceimpl.EmailServiceImplement;
import gp2.StudentLifeCycle.StudentLifecylce.serviceimpl.SmsServiceImplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

public class CandidateServiceImplementTest {

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private EmailServiceImplement emailServiceImplement;

    @Mock
    private SmsServiceImplement smsServiceImplement;

    @Mock
    private ApplicationRepository applicationRepository;

    @InjectMocks
    private CandidateServiceImplement candidateService;

    private Candidate candidate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Level level = new Level();
        level.setFaculty("Ing");
        level.setYear(2);
        level.setName("Ing 2 Anglo");

        candidate = new Candidate();
        candidate.setId(1L);
        candidate.setName("John Doe");
        candidate.setEmail("john.doe@example.com");
        candidate.setPhone("1234567890");
        candidate.setBirthdate(LocalDate.of(1990, 1, 1));
        candidate.setStatus(Candidate.Status.NEW);
        candidate.setLevel(level);
    }

    @Test
    public void testGetAllCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        candidates.add(candidate);
        when(candidateRepository.findAll()).thenReturn(candidates);

        List<Candidate> foundCandidates = candidateService.getAllCandidates();

        assertEquals(1, foundCandidates.size());
        assertEquals(candidate, foundCandidates.get(0));
    }

    @Test
    public void testGetCandidateById() {
        when(candidateRepository.findById(1L)).thenReturn(Optional.of(candidate));

        Candidate foundCandidate = candidateService.getCandidateById(1L);

        assertEquals(candidate, foundCandidate);
    }


    @Test
    public void testUpdateCandidate() {
        // Prepare the CandidateDto to update the candidate's details
        CandidateDto updatedCandidateDto = new CandidateDto();
        Level level = new Level().setFaculty("Ing").setYear(2).setName("Ing 2 Anglo");
        updatedCandidateDto.setName("Jane Doe");
        updatedCandidateDto.setEmail("jane.doe@example.com");
        updatedCandidateDto.setPhone("0987654321");
        updatedCandidateDto.setStatus(Candidate.Status.STUDENT);
        updatedCandidateDto.setBirthdate(candidate.getBirthdate());
        updatedCandidateDto.setLevel(level);

        // Mock the findCandidateById() method to return the candidate
        when(candidateRepository.findCandidateById(1L)).thenReturn(Optional.of(candidate));
        when(candidateRepository.save(any(Candidate.class))).thenReturn(candidate);
        when(studentRepository.save(any(Student.class))).thenReturn(new Student());

        // Act: Update the candidate's details
        Candidate updatedCandidate = candidateService.updateCandidate(1L, updatedCandidateDto);

        // Assert: Verify the candidate's details are updated and a new Student is created
        assertEquals(updatedCandidateDto.getName(), updatedCandidate.getName());
        assertEquals(updatedCandidateDto.getEmail(), updatedCandidate.getEmail());
        assertEquals(updatedCandidateDto.getPhone(), updatedCandidate.getPhone());
        verify(studentRepository, times(1)).save(any(Student.class));
        verify(candidateRepository, times(1)).save(any(Candidate.class));
    }

    @Test
    public void testGetCandidateByIdNotFound() {
        when(candidateRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            candidateService.getCandidateById(1L);
        });

        assertEquals("Candidate not found", exception.getMessage());
    }

    @Test
    public void testUpdateCandidateNotFound() {
        CandidateDto updatedCandidateDto = new CandidateDto();
        updatedCandidateDto.setName("Jane Doe");

        when(candidateRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            candidateService.updateCandidate(1L, updatedCandidateDto);
        });

        assertEquals("Candidate not found", exception.getMessage());
    }
}
