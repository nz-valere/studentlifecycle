package gp2.StudentLifeCycle.StudentLifecylce.serviceimpl;

import gp2.StudentLifeCycle.StudentLifecylce.dtos.CandidateDto;
import gp2.StudentLifeCycle.StudentLifecylce.dtos.DocumentDto;
import gp2.StudentLifeCycle.StudentLifecylce.models.*;
import gp2.StudentLifeCycle.StudentLifecylce.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CandidateServiceImplement {
    @Autowired
    private CandidateRepository candidateRepository;

    private StudentRepository studentRepository;

    private LevelRepository levelRepository;

    private DocumentRepository documentRepository;

    @Autowired
    private EmailServiceImplement emailServiceImplement;

    @Autowired
    private SmsServiceImplement smsServiceImplement;

    private DocumentServiceImplement docService;

    private ApplicationServiceImplement appService;
    private ApplicationRepository applicationRepository;


    public CandidateServiceImplement(CandidateRepository candidateRepository, StudentRepository studentRepository, DocumentServiceImplement docService, ApplicationServiceImplement appService, ApplicationRepository applicationRepository) {
        this.candidateRepository = candidateRepository;
        this.studentRepository = studentRepository;
        this.docService = docService;
        this.appService = appService;
        this.applicationRepository = applicationRepository;
    }

    public List<Candidate> getAllCandidates() {
        return (List<Candidate>) candidateRepository.findAll();
    }

    public List<Candidate> getCandidatesByStatus(Candidate.Status status) {
        return candidateRepository.findCandidateByStatus(status);
    }

    public Candidate updateCandidateStatus(Long id, Candidate.Status status) {
        return candidateRepository.findById(id)
                .map(candidate -> {
                    candidate.setStatus(status);
                    if (status == Candidate.Status.STUDENT){
                        emailServiceImplement.sendSimpleMailforcandi(candidate);
                        Student student = new Student()
                                .setName(candidate.getName())
                                .setEmail(candidate.getEmail())
                                .setBirthdate(candidate.getBirthdate())
                                .setPhone(candidate.getPhone())
                                .setCandidateId(candidate)
                                .setStatus(Student.Status.ENROLLED)
                                .setLevel(candidate.getLevel());

                        studentRepository.save(student);
                    }
                    return candidateRepository.save(candidate);
                })
                .orElseThrow(() -> new RuntimeException("Candidate not found"));
    }

    public Candidate updateCandidate(Long id, CandidateDto updatedCandidate) {
        return candidateRepository.findCandidateById(id)
                .map(existingCandidate -> {
                    existingCandidate.setName(updatedCandidate.getName());
                    existingCandidate.setEmail(updatedCandidate.getEmail());
                    existingCandidate.setPhone(updatedCandidate.getPhone());
                    existingCandidate.setStatus(updatedCandidate.getState());
                    existingCandidate.setBirthdate(updatedCandidate.getBirthdate());
                    existingCandidate.setBirthplace(updatedCandidate.getBirthplace());
                    existingCandidate.setNationality(updatedCandidate.getNationality());
                    existingCandidate.setRegion(updatedCandidate.getRegion());
                    existingCandidate.setAddress(updatedCandidate.getAddress());
                    existingCandidate.setOptions(updatedCandidate.getOptions());
                    existingCandidate.setCni(updatedCandidate.getCni());
                    existingCandidate.setSex(updatedCandidate.getSex());

                    //  FIX: Fetch Level from repository before setting it
                    if (updatedCandidate.getLevel() != null && updatedCandidate.getLevel().getId() != null) {
//                        Level level = levelRepository.findById(updatedCandidate.getLevel().getId())
//                                .orElseThrow(() -> new RuntimeException("Level not found"));
                        existingCandidate.setLevel(updatedCandidate.getLevel());
                    }

                    // Handle student creation
                    if (existingCandidate.getStatus() == Candidate.Status.STUDENT) {
                        Student student = new Student()
                                .setName(updatedCandidate.getName())
                                .setEmail(updatedCandidate.getEmail())
                                .setBirthdate(updatedCandidate.getBirthdate())
                                .setPhone(updatedCandidate.getPhone())
                                .setCandidateId(existingCandidate)
                                .setStatus(Student.Status.ENROLLED)
                                .setLevel(null);

                        studentRepository.save(student);
                    }

                    return candidateRepository.save(existingCandidate);
                })
                .orElseThrow(() -> new RuntimeException("Candidate not found"));
    }


    @Transactional
    public Candidate createCandidateWithLevel(Candidate candidate) {
        if (candidate.getLevel() != null && candidate.getLevel().getId() == null) {
            levelRepository.save(candidate.getLevel()); // Save the Level entity first
        }
        return candidateRepository.save(candidate);
    }

    @Transactional
    public Candidate createCandidateWithApplication(Candidate candidate, Application application) {
        // Ensure the Level is saved before persisting the Candidate
        if (candidate.getLevel() != null && candidate.getLevel().getId() == null) {
            levelRepository.save(candidate.getLevel()); // Save the Level if it's not yet saved
        }
        // Persist the Candidate with the associated Level
        return candidateRepository.save(candidate);
    }

    public Candidate getCandidateById(Long id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));
    }

    public Candidate createCandidateWithApplication(CandidateDto candidateDto) {
        // Create candidate
        if (candidateRepository.findCandidateByEmail(candidateDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }

        Candidate candidate = new Candidate();
        candidate.setName(candidateDto.getName());
        candidate.setEmail(candidateDto.getEmail());
        candidate.setCni(candidateDto.getCni());
        candidate.setBirthdate(candidateDto.getBirthdate());
        candidate.setPhone(candidateDto.getPhone());
        candidate.setAddress(candidateDto.getAddress());
        candidate.setRegion(candidateDto.getRegion());
        candidate.setLevel(candidateDto.getLevel());
        candidate.setNationality(candidateDto.getNationality());
        candidate.setBirthplace(candidateDto.getBirthplace());
        candidate.setOptions(candidateDto.getOptions());
        candidate.setSex(candidateDto.getSex());
        candidate.setStatus(Candidate.Status.NEW);

        //smsServiceImplement.notifyCandidate(candidateDto);
        emailServiceImplement.sendSimpleMail(candidateDto);


        // Create application
        Application application = new Application();
        application.setSubmit_date(LocalDate.now());
        application.setEnrolled(false);
        application.setState(Application.AppState.ONGOING);
        application.setCandidate(candidate.getId());


        // Link documents to the application
        application.setDocuments(null);
        List<Application> applications = new ArrayList<Application>();

        if (candidate.getApplications()!= null){
            candidate.getApplications().add(application);
        }else {
            candidate.setApplications(applications);
        }



        applicationRepository.save(application);


        return candidateRepository.save(candidate);
    }


    public Candidate getCandidatebyEmail(String email){
        return candidateRepository.findCandidateByEmail(email)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));
    }


    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }
}

