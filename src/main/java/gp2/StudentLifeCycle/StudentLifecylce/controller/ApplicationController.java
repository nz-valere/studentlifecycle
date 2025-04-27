package gp2.StudentLifeCycle.StudentLifecylce.controller;

import gp2.StudentLifeCycle.StudentLifecylce.dtos.ApplicationDto;
import gp2.StudentLifeCycle.StudentLifecylce.dtos.CandidateDto;
import gp2.StudentLifeCycle.StudentLifecylce.models.Application;
import gp2.StudentLifeCycle.StudentLifecylce.models.Candidate;
import gp2.StudentLifeCycle.StudentLifecylce.service.ApplicationService;
import gp2.StudentLifeCycle.StudentLifecylce.serviceimpl.ApplicationServiceImplement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Application Management", description = "Endpoints for managing applications")
@RestController
@RequestMapping("/lifecycle/applications")
public class ApplicationController {

    @Autowired
    private ApplicationServiceImplement applicationService;

    @Operation(summary = "Get all applications", description = "Retrieves a list of all applications.")
    @ApiResponse(responseCode = "200", description = "List of applications retrieved successfully")
    @GetMapping
    public List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @Operation(summary = "Get application by ID", description = "Retrieves an application by its ID.")
    @ApiResponse(responseCode = "200", description = "Application retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Application not found")
    @GetMapping("/{id}")
    public Application getApplicationById(@PathVariable Long id) {
        return applicationService.getApplicationById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
    }

    @Operation(summary = "Create a new application", description = "Creates a new application and saves it to the database.")
    @ApiResponse(responseCode = "201", description = "Application created successfully")
    @PostMapping
    public ResponseEntity<Application> createApp(@RequestBody ApplicationDto appDto) {
        Application app = applicationService.CreateApplication(appDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(app);
    }

    @Operation(summary = "Update an application", description = "Updates an existing application by its ID.")
    @ApiResponse(responseCode = "200", description = "Application updated successfully")
    @ApiResponse(responseCode = "404", description = "Application not found")
    @PutMapping("/{id}")
    public ResponseEntity<Application> updateFamilyInfo(@PathVariable Long id, @RequestBody Application app) {
        try {
            Application updatedApp = applicationService.updateApplication(id, app);
            return ResponseEntity.ok(updatedApp);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Update application state", description = "Updates the state of an application by its ID.")
    @ApiResponse(responseCode = "200", description = "Application state updated successfully")
    @ApiResponse(responseCode = "404", description = "Application not found")
    @PutMapping("/state/{id}")
    public ResponseEntity<Application> updateApplicationState(@PathVariable Long id, @RequestBody String status) {
        Application.AppState state = null;
        switch (status){
            case "ONGOING":
                state = Application.AppState.ONGOING;
                break;
            case "ACCEPTED":
                state = Application.AppState.ACCEPTED;
                break;
            case "NEGOTIATING":
                state = Application.AppState.REJECTED;
                break;
        }
        Application updatedapp = applicationService.updateApplicationStatus(id, state);
        return ResponseEntity.ok(updatedapp);
    }
}
