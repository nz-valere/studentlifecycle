package gp2.StudentLifeCycle.StudentLifecylce.controller;

import gp2.StudentLifeCycle.StudentLifecylce.dtos.DocumentDto;
import gp2.StudentLifeCycle.StudentLifecylce.models.Document;
import gp2.StudentLifeCycle.StudentLifecylce.serviceimpl.DocumentServiceImplement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@Tag(name = "Document Management", description = "Endpoints for managing documents")
@RestController
@RequestMapping("/lifecycle/documents")
public class DocumentController {

    private DocumentServiceImplement documentService;

    public DocumentController(DocumentServiceImplement documentService) {
        this.documentService = documentService;
    }

    @Operation(summary = "Upload a document", description = "Uploads a new document and saves it to the database.")
    @ApiResponse(responseCode = "200", description = "Document uploaded successfully")
    @ApiResponse(responseCode = "500", description = "Failed to upload document")
    @PostMapping("/upload")
    public ResponseEntity<Document> uploadDocument(@RequestBody DocumentDto file) {
        try {
            Document document = documentService.saveDocument(file);
            return ResponseEntity.ok( document);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocumentById(@PathVariable Long id) {
        Document document = documentService.getDocumentById(id);
        if (document != null) {
            return ResponseEntity.ok(document);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/candidate/{id}")
    public ResponseEntity<Document> getDocumentsByCandidateId(@PathVariable Long id) {
        Document documents = documentService.getDocumentsByCandidateId(id).orElse(null);
        if (documents != null) {
            return ResponseEntity.ok(documents);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Delete an document record", description = "Deletes an document record by its ID.")
    @ApiResponse(responseCode = "204", description = "document record deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }
}
