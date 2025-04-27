package gp2.StudentLifeCycle.StudentLifecylce.controller;

import gp2.StudentLifeCycle.StudentLifecylce.models.FamilyInfo;
import gp2.StudentLifeCycle.StudentLifecylce.serviceimpl.FamilyInfoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Family Information Management", description = "Endpoints for managing family information")
@RestController
@RequestMapping("/lifecycle/family-info")
public class FamilyInfoController {

    private FamilyInfoServiceImpl familyInfoService;

    @Autowired
    public FamilyInfoController(FamilyInfoServiceImpl familyInfoService) {
        this.familyInfoService = familyInfoService;
    }

    @Operation(summary = "Create family info", description = "Creates a new family information record.")
    @ApiResponse(responseCode = "201", description = "Family information created successfully")
    @PostMapping
    public ResponseEntity<FamilyInfo> createFamilyInfo(@RequestBody FamilyInfo familyInfo) {
        FamilyInfo savedFamilyInfo = familyInfoService.createFamilyInfo(familyInfo);
        return new ResponseEntity<>(savedFamilyInfo, HttpStatus.CREATED);
    }

    @Operation(summary = "Get family info by ID", description = "Retrieves family information by its ID.")
    @ApiResponse(responseCode = "200", description = "Family information retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Family information not found")
    @GetMapping("/{id}")
    public ResponseEntity<FamilyInfo> getFamilyInfoById(@PathVariable String id) {
        return familyInfoService.getFamilyInfoById(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Get family info by candidate ID", description = "Retrieves family information by candidate ID.")
    @ApiResponse(responseCode = "200", description = "Family information retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Family information not found")
    @GetMapping("/candidate/{id}")
    public ResponseEntity<FamilyInfo> getFamilyInfoByCandidateId(@PathVariable Long id) {
        return familyInfoService.getFamilyInfoByCandidateId(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Get all family infos", description = "Retrieves a list of all family information records.")
    @ApiResponse(responseCode = "200", description = "List of family information retrieved successfully")
    @GetMapping
    public List<FamilyInfo> getAllFamilyInfos() {
        return familyInfoService.getAllFamilyInfos();
    }

    @Operation(summary = "Update family info", description = "Updates an existing family information record by its ID.")
    @ApiResponse(responseCode = "200", description = "Family information updated successfully")
    @ApiResponse(responseCode = "404", description = "Family information not found")
    @PutMapping("/{id}")
    public ResponseEntity<FamilyInfo> updateFamilyInfo(@PathVariable String id, @RequestBody FamilyInfo familyInfo) {
        try {
            FamilyInfo updatedFamilyInfo = familyInfoService.updateFamilyInfo(id, familyInfo);
            return ResponseEntity.ok(updatedFamilyInfo);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete family info", description = "Deletes a family information record by its ID.")
    @ApiResponse(responseCode = "204", description = "Family information deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFamilyInfo(@PathVariable String id) {
        familyInfoService.deleteFamilyInfo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}