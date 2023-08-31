    package com.background.verification.controller;

    import com.background.verification.dto.DocumentUploadRequest;
    import com.background.verification.entity.Document;
    import com.background.verification.entity.DocumentCategory;
    import com.background.verification.entity.VerificationDetails;
    import com.background.verification.repository.DocumentCategoryRepository;
    import com.background.verification.repository.DocumentRepository;
    import com.background.verification.repository.VerificationDetailsRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;

    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;

    import java.io.IOException;
    import java.util.*;

    @RestController
    @RequestMapping("/api/documents")
    public class EmployeeController {

        @Autowired
        private DocumentRepository documentRepository;

        @Autowired
        private VerificationDetailsRepository verificationDetailsRepository;

        @Autowired
        private DocumentCategoryRepository documentCategoryRepository;

        @PreAuthorize("hasRole('EMPLOYEE')")
        @PostMapping("/upload")
        public ResponseEntity<Map<String, Object>> uploadDocument(@ModelAttribute DocumentUploadRequest request) {
            try {
                MultipartFile file = request.getFile();

                // Save the document
                Document document = new Document();
                document.setDocFile(file.getBytes());
                document.setUsername(request.getUsername());
                document.setDoctypeId(request.getDoctypeId());

                // Generate a unique request ID
                String requestId = UUID.randomUUID().toString();
                document.setRequestId(requestId); // Set requestId in Document

                documentRepository.save(document);

                // Save verification details
                VerificationDetails verificationDetails = new VerificationDetails();
                verificationDetails.setRequestId(requestId);
                verificationDetails.setRequestStartDate(new Date());
                verificationDetails.setRequestEndDate(new Date(System.currentTimeMillis() + 10 * 24 * 60 * 60 * 1000)); // Adding 10 days
                verificationDetailsRepository.save(verificationDetails);

                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("requestId", requestId);
                responseBody.put("status", "success");

                return ResponseEntity.ok(responseBody);
            } catch (IOException e) {
                e.printStackTrace();
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("error", "Error uploading document.");
                errorResponse.put("status", "error");
                return ResponseEntity.badRequest().body(errorResponse);
            }
        }

        @PostMapping("/requested-documents")
        @PreAuthorize("hasRole('EMPLOYEE')")
        public ResponseEntity<Map<String, Object>> getRequestedDocumentsByRequestBody(@RequestBody Map<String, String> requestBody) {
            String username = requestBody.get("username");

            Map<String, Object> response = new HashMap<>();

            List<Map<String, Object>> result = new ArrayList<>();

            try {
                List<Document> documents = documentRepository.findByUsername(username);
                for (Document document : documents) {
                    Map<String, Object> docDetails = new HashMap<>();
                    docDetails.put("requestId", document.getRequestId());
                    docDetails.put("status", document.getStatus());

                    VerificationDetails verificationDetails = verificationDetailsRepository.findByRequestId(document.getRequestId());
                    if (verificationDetails != null) {
                        docDetails.put("requestStartDate", verificationDetails.getRequestStartDate());
                        docDetails.put("requestEndDate", verificationDetails.getRequestEndDate());
                    }

                    DocumentCategory documentCategory = documentCategoryRepository.findById(document.getDoctypeId()).orElse(null);
                    if (documentCategory != null) {
                        docDetails.put("documentName", documentCategory.getDocumentName());
                    }

                    result.add(docDetails);
                }

                response.put("data", result);
                response.put("status", "success");
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                e.printStackTrace();
                response.put("error", "Error retrieving documents.");
                response.put("status", "error");
                return ResponseEntity.badRequest().body(response);
            }
        }
    }
