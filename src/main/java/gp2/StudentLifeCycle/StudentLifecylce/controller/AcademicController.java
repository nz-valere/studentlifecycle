package gp2.StudentLifeCycle.StudentLifecylce.controller;

import gp2.StudentLifeCycle.StudentLifecylce.models.Academic;
import gp2.StudentLifeCycle.StudentLifecylce.serviceimpl.AcademicServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Academic Management", description = "Endpoints for managing academic records")
@RestController
@RequestMapping("/lifecycle/academics")
public class AcademicController {

    @Autowired
    private AcademicServiceImpl academicService;

    @Operation(summary = "Create a new academic record", description = "Creates and saves a new academic record.")
    @ApiResponse(responseCode = "201", description = "Academic record created successfully")
    @PostMapping
    public ResponseEntity<Academic> createAcademic(@RequestBody Academic academic) {
        Academic savedAcademic = academicService.saveAcademic(academic);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAcademic);
    }

    @Operation(summary = "Get academic by ID", description = "Retrieves an academic record by its ID.")
    @ApiResponse(responseCode = "200", description = "Academic record retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Academic record not found")
    @GetMapping("/{id}")
    public ResponseEntity<Academic> getAcademicById(@PathVariable Long id) {
        return academicService.getAcademicById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get academic by Candidate ID", description = "Retrieves an academic record by Candidate ID.")
    @ApiResponse(responseCode = "200", description = "Academic record retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Academic record not found")
    @GetMapping("/candidate/{id}")
    public ResponseEntity<Academic> getAcademicByCandidateId(@PathVariable Long id) {
        return academicService.getAcademicByCandidateId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all academic records", description = "Retrieves a list of all academic records.")
    @ApiResponse(responseCode = "200", description = "List of academic records retrieved successfully")
    @GetMapping
    public ResponseEntity<List<Academic>> getAllAcademics() {
        return ResponseEntity.ok(academicService.getAllAcademics());
    }

    @Operation(summary = "Update an academic record", description = "Updates an existing academic record by its ID.")
    @ApiResponse(responseCode = "200", description = "Academic record updated successfully")
    @ApiResponse(responseCode = "404", description = "Academic record not found")
    @PutMapping("/{id}")
    public ResponseEntity<Academic> updateAcademic(@PathVariable Long id, @RequestBody Academic academic) {
        Academic updatedAcademic = academicService.updateAcademic(id, academic);
        if (updatedAcademic != null) {
            return ResponseEntity.ok(updatedAcademic);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete an academic record", description = "Deletes an academic record by its ID.")
    @ApiResponse(responseCode = "204", description = "Academic record deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAcademic(@PathVariable Long id) {
        academicService.deleteAcademic(id);
        return ResponseEntity.noContent().build();
    }
}