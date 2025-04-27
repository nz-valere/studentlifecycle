package gp2.StudentLifeCycle.StudentLifecylce.controller;

import gp2.StudentLifeCycle.StudentLifecylce.dtos.LevelDto;
import gp2.StudentLifeCycle.StudentLifecylce.dtos.StudentDto;
import gp2.StudentLifeCycle.StudentLifecylce.models.Level;
import gp2.StudentLifeCycle.StudentLifecylce.models.Student;
import gp2.StudentLifeCycle.StudentLifecylce.serviceimpl.LevelServiceImplement;
import gp2.StudentLifeCycle.StudentLifecylce.serviceimpl.StudentServiceImplement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Level Management", description = "Endpoints for managing levels")
@RestController
@RequestMapping("/lifecycle/levels")
public class LevelController {

    @Autowired
    private LevelServiceImplement levelServiceImplement;

    @Operation(summary = "Get all levels", description = "Retrieves a list of all levels.")
    @ApiResponse(responseCode = "200", description = "List of levels retrieved successfully")
    @GetMapping
    public ResponseEntity<List<Level>> getAllLevels() {
        List<Level> levels = levelServiceImplement.getAllLevels();
        return ResponseEntity.ok(levels);
    }

    @Operation(summary = "Update level", description = "Updates an existing level by its ID.")
    @ApiResponse(responseCode = "200", description = "Level updated successfully")
    @ApiResponse(responseCode = "404", description = "Level not found")
    @PutMapping("/{id}")
    public ResponseEntity<Level> updateLevel(@PathVariable Long id, @RequestBody Level updateLevel) {
        Level savedLevel = levelServiceImplement.updateLevel(id, updateLevel);
        return ResponseEntity.ok(savedLevel);
    }

    @Operation(summary = "Get level by ID", description = "Retrieves a level by its ID.")
    @ApiResponse(responseCode = "200", description = "Level retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Level not found")
    @GetMapping("/{id}")
    public ResponseEntity<Level> getLevelById(@PathVariable Long id) {
        Level savedLevel = levelServiceImplement.getLevelById(id);
        return ResponseEntity.ok(savedLevel);
    }

    @Operation(summary = "Create a new level", description = "Creates a new level and saves it to the database.")
    @ApiResponse(responseCode = "201", description = "Level created successfully")
    @PostMapping
    public ResponseEntity<Level> registerLevel(@RequestBody LevelDto levelDto) {
        Level level = levelServiceImplement.createLevel(levelDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(level);
    }

    @Operation(summary = "Delete level", description = "Deletes a level by its ID.")
    @ApiResponse(responseCode = "204", description = "Level deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLevel(@PathVariable Long id) {
        levelServiceImplement.deleteLevel(id);
        return ResponseEntity.noContent().build();
    }
}
