package com.background.verification.controller;


import com.background.verification.dto.DocumentRequest;
import com.background.verification.dto.PendingDocumentDTO;
import com.background.verification.entity.Document;
import com.background.verification.service.DocumentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final DocumentService documentService;

    public AdminController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/documents")
    public List<Document> getDocumentsByUsername(@RequestParam String username) {
        return documentService.getDocumentsByUsername(username);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update-status")
    public ResponseEntity<String> updateDocumentStatus(@RequestBody Map<String, String> updateInfo) {
        String username = updateInfo.get("username");
        String requestId = updateInfo.get("requestId");
        String newStatus = updateInfo.get("newStatus");

        if (username == null || requestId == null || newStatus == null) {
            return ResponseEntity.badRequest().body("Incomplete data in request.");
        }

        boolean updated = documentService.updateDocumentStatus(username, requestId, newStatus);
        if (updated) {
            return ResponseEntity.ok("Document status updated successfully.");
        } else {
            return ResponseEntity.badRequest().body("Document not found for the provided username and requestId.");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/get-document")
    public ResponseEntity<byte[]> getDocumentFile(@RequestBody DocumentRequest documentRequest) {
        String username = documentRequest.getUsername();
        String requestId = documentRequest.getRequestId();

        if (username == null || requestId == null) {
            return ResponseEntity.badRequest().body("Incomplete data in request.".getBytes());
        }

        byte[] docFile = documentService.getDocumentFile(username, requestId);
        if (docFile != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "document.dat");
            return new ResponseEntity<>(docFile, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/pending-documents")
    public List<PendingDocumentDTO> getPendingDocuments() {
        return documentService.getPendingDocuments();
    }

    // Exception Handling
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
    }
}