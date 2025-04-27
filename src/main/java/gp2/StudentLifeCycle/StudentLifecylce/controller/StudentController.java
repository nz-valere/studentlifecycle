package gp2.StudentLifeCycle.StudentLifecylce.controller;

import gp2.StudentLifeCycle.StudentLifecylce.dtos.StudentDto;
import gp2.StudentLifeCycle.StudentLifecylce.models.Student;
import gp2.StudentLifeCycle.StudentLifecylce.service.StudentService;
import gp2.StudentLifeCycle.StudentLifecylce.serviceimpl.StudentServiceImplement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Student Management", description = "Endpoints for managing students")
@RestController
@RequestMapping("/lifecycle/students")
public class StudentController {

    @Autowired
    private StudentServiceImplement studentService;

    @Operation(summary = "Get all students", description = "Retrieves a list of all students.")
    @ApiResponse(responseCode = "200", description = "List of students retrieved successfully")
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @Operation(summary = "Update student", description = "Updates an existing student by its ID.")
    @ApiResponse(responseCode = "200", description = "Student updated successfully")
    @ApiResponse(responseCode = "404", description = "Student not found")
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        Student savedStudent = studentService.updateStudent(id, updatedStudent);
        return ResponseEntity.ok(savedStudent);
    }

    @Operation(summary = "Get student by ID", description = "Retrieves a student by its ID.")
    @ApiResponse(responseCode = "200", description = "Student retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Student not found")
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @Operation(summary = "Register a new student", description = "Creates a new student and saves it to the database.")
    @ApiResponse(responseCode = "201", description = "Student registered successfully")
    @PostMapping
    public ResponseEntity<Student> registerStudent(@RequestBody StudentDto student) {
        Student savedStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @Operation(summary = "Delete a student", description = "Deletes a student by its ID.")
    @ApiResponse(responseCode = "204", description = "Student deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
