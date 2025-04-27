package gp2.StudentLifeCycle.StudentLifecylce.controller;

import gp2.StudentLifeCycle.StudentLifecylce.dtos.CandidateDto;
import gp2.StudentLifeCycle.StudentLifecylce.dtos.StatusDto;
import gp2.StudentLifeCycle.StudentLifecylce.models.Candidate;
import gp2.StudentLifeCycle.StudentLifecylce.models.Student;
import gp2.StudentLifeCycle.StudentLifecylce.serviceimpl.CandidateServiceImplement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name = "Candidate Management", description = "Endpoints for managing candidates")
@RestController
@RequestMapping("/lifecycle/candidates")
public class CandidateController {

    @Autowired
    private CandidateServiceImplement candidateService;

    @Operation(summary = "Create a new candidate", description = "Creates a new candidate with an associated application.")
    @ApiResponse(responseCode = "201", description = "Candidate created successfully")
    @PostMapping
    public ResponseEntity<Candidate> createCandidate(@RequestBody CandidateDto candidateDto) {
        Candidate candidate = candidateService.createCandidateWithApplication(candidateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(candidate);
    }

    @Operation(summary = "Get all candidates", description = "Retrieves a list of all candidates.")
    @ApiResponse(responseCode = "200", description = "List of candidates retrieved successfully")
    @GetMapping
    public ResponseEntity<List<Candidate>> getAllStudents() {
        List<Candidate> candidates = candidateService.getAllCandidates();
        return ResponseEntity.ok(candidates);
    }

    @Operation(summary = "Get candidate by ID", description = "Retrieves a candidate by its ID.")
    @ApiResponse(responseCode = "200", description = "Candidate retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Candidate not found")
    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id) {
        Candidate candidate = candidateService.getCandidateById(id);
        return ResponseEntity.ok(candidate);
    }

    @Operation(summary = "Get candidate by ID", description = "Retrieves a candidate by its Email.")
    @ApiResponse(responseCode = "200", description = "Candidate retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Candidate not found")
    @GetMapping("/email/{email}")
    public ResponseEntity<Candidate> getCandidateByEmail(@PathVariable String email) {
        Candidate candidate = candidateService.getCandidatebyEmail(email);
        return ResponseEntity.ok(candidate);
    }

    @Operation(summary = "Get candidates by status", description = "Retrieves a list of candidates by status.")
    @ApiResponse(responseCode = "200", description = "List of candidates with the specified status retrieved successfully")
    @GetMapping("/Status")
    public List<Candidate> getCandidatesById(@PathVariable Candidate.Status status) {
        return candidateService.getCandidatesByStatus(status);
    }

    @Operation(summary = "Update a candidate", description = "Updates an existing candidate by its ID.")
    @ApiResponse(responseCode = "200", description = "Candidate updated successfully")
    @ApiResponse(responseCode = "404", description = "Candidate not found")
    @PutMapping("/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable Long id, @RequestBody CandidateDto updatedCandidate) {
        Candidate savedCandidate = candidateService.updateCandidate(id, updatedCandidate);
        return ResponseEntity.ok(savedCandidate);
    }

    @Operation(summary = "Update candidate status", description = "Updates the status of a candidate by its ID.")
    @ApiResponse(responseCode = "200", description = "Candidate status updated successfully")
    @ApiResponse(responseCode = "404", description = "Candidate not found")
    @PutMapping("/status/{id}")
    public ResponseEntity<Candidate> updateCandidateStatus(@PathVariable Long id, @RequestBody StatusDto status) {
        Candidate.Status state = null;
        switch (status.getStatus()) {
            case "NEW":
                state = Candidate.Status.NEW;
                break;
            case "QUALIFIED":
                state = Candidate.Status.QUALIFIED;
                break;
            case "NEGOTIATION":
                state = Candidate.Status.NEGOTIATION;
                break;
            case "STUDENT":
                state = Candidate.Status.STUDENT;
                break;
        }
        Candidate updatedCandidate = candidateService.updateCandidateStatus(id, state);
        return ResponseEntity.ok(updatedCandidate);
    }

    @Operation(summary = "Delete a candidate", description = "Deletes a candidate by its ID.")
    @ApiResponse(responseCode = "204", description = "Candidate deleted successfully")
    @ApiResponse(responseCode = "404", description = "Candidate not found")
    @DeleteMapping("/{id}")
    public void deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
    }
}