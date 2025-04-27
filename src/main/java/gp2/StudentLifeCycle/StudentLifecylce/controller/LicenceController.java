package gp2.StudentLifeCycle.StudentLifecylce.controller;

import gp2.StudentLifeCycle.StudentLifecylce.models.Licence;
import gp2.StudentLifeCycle.StudentLifecylce.serviceimpl.LicenceServiceImplement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Licence Management", description = "Endpoints for managing licences")
@RestController
@RequestMapping("/lifecycle/licence")
public class LicenceController {

    private LicenceServiceImplement licenceService;

    @Autowired
    public LicenceController(LicenceServiceImplement licenceService) {
        this.licenceService = licenceService;
    }

    @Operation(summary = "Create a new licence", description = "Creates a new licence and saves it to the database.")
    @ApiResponse(responseCode = "201", description = "Licence created successfully")
    @PostMapping
    public ResponseEntity<Licence> createLicence(@RequestBody Licence licence) {
        Licence savedLicence = licenceService.saveLicence(licence);
        return new ResponseEntity<>(savedLicence, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all licences", description = "Retrieves a list of all licences.")
    @ApiResponse(responseCode = "200", description = "List of licences retrieved successfully")
    @GetMapping
    public ResponseEntity<List<Licence>> getAllLicences() {
        List<Licence> licences = licenceService.getAllLicences();
        return ResponseEntity.ok(licences);
    }

    @Operation(summary = "Get licence by ID", description = "Retrieves a licence by its ID.")
    @ApiResponse(responseCode = "200", description = "Licence retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Licence not found")
    @GetMapping("/{id}")
    public ResponseEntity<Licence> getLicenceById(@PathVariable Long id) {
        return licenceService.getLicenceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a licence", description = "Deletes a licence by its ID.")
    @ApiResponse(responseCode = "204", description = "Licence deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLicence(@PathVariable Long id) {
        licenceService.deleteLicence(id);
        return ResponseEntity.noContent().build();
    }
}
