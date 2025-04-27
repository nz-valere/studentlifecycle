package gp2.StudentLifeCycle.StudentLifecylce.IntegrationTesting;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import gp2.StudentLifeCycle.StudentLifecylce.dtos.CandidateDto;
import gp2.StudentLifeCycle.StudentLifecylce.models.Candidate;
import gp2.StudentLifeCycle.StudentLifecylce.models.Level;
import gp2.StudentLifeCycle.StudentLifecylce.models.Student;
import gp2.StudentLifeCycle.StudentLifecylce.repository.ApplicationRepository;
import gp2.StudentLifeCycle.StudentLifecylce.repository.CandidateRepository;
import gp2.StudentLifeCycle.StudentLifecylce.repository.LevelRepository;
import gp2.StudentLifeCycle.StudentLifecylce.repository.StudentRepository;
import gp2.StudentLifeCycle.StudentLifecylce.serviceimpl.CandidateServiceImplement;
import gp2.StudentLifeCycle.StudentLifecylce.serviceimpl.EmailServiceImplement;
import gp2.StudentLifeCycle.StudentLifecylce.serviceimpl.SmsServiceImplement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CandidateServiceIntegrationTest {

    @Autowired
    private CandidateServiceImplement candidateService;

    @Mock
    private CandidateRepository candidateRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private EmailServiceImplement emailServiceImplement;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private SmsServiceImplement smsServiceImplement;

    private Candidate candidate;

    @BeforeEach
    public void setUp() {
        // Setup Level
        Level level = new Level();
        level.setId(1L);  // Set a valid ID for Level
        level.setFaculty("Engineering");
        level.setName("Engineering Level 1");
        levelRepository.save(level);  // Save it before creating the Candidate

        // Setup Candidate
        candidate = new Candidate();
        candidate.setName("John Doe");
        candidate.setEmail("john.doe@example.com");
        candidate.setLevel(level);  // Set the saved Level to the Candidate
        candidate.setBirthdate(LocalDate.of(1990, 1, 1));  // Added birthdate field
        candidate.setPhone("1234567890");
        candidate.setStatus(Candidate.Status.NEW);
        candidateRepository.save(candidate); // Save Candidate in repository

        // Log Candidate ID to check if it's saved properly
        System.out.println("Candidate ID after saving: " + candidate.getId());
    }

//    @Test
//    public void testCreateAndRetrieveCandidate() {
//        // Create a new CandidateDto
//        CandidateDto candidateDto = new CandidateDto();
//        Level level = new Level().setFaculty("Ing").setYear(2).setName("Ing 2 Anglo").setId(1L);
//        candidateDto.setName("John Doe");
//        candidateDto.setEmail("john.doe@example.com");
//        candidateDto.setCni("CNI123");
//        candidateDto.setBirthdate(LocalDate.of(1990, 1, 1));
//        candidateDto.setPhone("1234567890");
//        candidateDto.setAddress("123 Main St");
//        candidateDto.setRegion("Some Region");
//        candidateDto.setLevel(level);
//        candidateDto.setNationality("Some Nationality");
//        candidateDto.setBirthplace("Someplace");
//        candidateDto.setOptions("Some Options");
//        candidateDto.setSex("Male");
//
//        // Create the candidate with application
//        Candidate createdCandidate = candidateService.createCandidateWithApplication(candidateDto);
//
//        // Retrieve the candidate by ID
//        Optional<Candidate> foundCandidate = candidateRepository.findById(createdCandidate.getId());
//
//        // Assertions
//        assertThat(foundCandidate).isPresent();
//        assertThat(foundCandidate.get().getName()).isEqualTo(candidateDto.getName());
//        assertThat(foundCandidate.get().getEmail()).isEqualTo(candidateDto.getEmail());
//    }


//    @Test
//    public void testUpdateCandidate() {
//        // Ensure candidate ID is valid before attempting to update
//        Candidate candidateToUpdate = candidateRepository.findById(1L)
//                .orElseThrow(() -> new RuntimeException("Candidate not found"));
//
//        // Prepare CandidateDto to update the candidate's details
//        CandidateDto updatedCandidateDto = new CandidateDto();
//        Level level = new Level().setFaculty("Ing").setYear(2).setName("Ing 2 Anglo").setId(1L);
//        updatedCandidateDto.setName("Jane Doe");
//        updatedCandidateDto.setEmail("jane.doe@example.com");
//        updatedCandidateDto.setPhone("0987654321");
//        updatedCandidateDto.setStatus(Candidate.Status.STUDENT);
//        updatedCandidateDto.setBirthdate(candidate.getBirthdate());
//        updatedCandidateDto.setLevel(level);
//
//        // Act: Update the candidate's details
//        Candidate updatedCandidate = candidateService.updateCandidate(candidate.getId(), updatedCandidateDto);
//
//        // Assert: Verify the candidate's details are updated
//        assertEquals(updatedCandidateDto.getName(), updatedCandidate.getName());
//        assertEquals(updatedCandidateDto.getEmail(), updatedCandidate.getEmail());
//        assertEquals(updatedCandidateDto.getPhone(), updatedCandidate.getPhone());
//
//        // Verify: Ensure that a new Student was created
//        Optional<Student> savedStudent = studentRepository.findById(updatedCandidate.getId());
//        assertTrue(savedStudent.isPresent());
//        assertEquals(Student.Status.ENROLLED, savedStudent.get().getStatus());
//    }

//    @Test
//    public void testGetCandidateByIdNotFound() {
//        // Mock behavior for candidateRepository
//        when(candidateRepository.findById(1L)).thenReturn(Optional.empty());
//
//        // Assert: Verify that when the candidate is not found, an exception is thrown
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            candidateService.getCandidateById(1L);
//        });
//
//        // Verify the exception message
//        assertEquals("Candidate not found", exception.getMessage());
//    }

    @Test
    public void testUpdateCandidateNotFound() {
        CandidateDto updatedCandidateDto = new CandidateDto();
        updatedCandidateDto.setName("Jane Doe");

        // Mocking the repository behavior to return a valid Level
        Level level = new Level();
        level.setId(1L);  // Valid ID for Level

        when(candidateRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            candidateService.updateCandidate(0L, updatedCandidateDto);
        });

        assertEquals("Candidate not found", exception.getMessage());
    }
}
