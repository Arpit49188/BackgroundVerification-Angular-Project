package com.background.verification.service;


import com.background.verification.dto.PendingDocumentDTO;
import com.background.verification.entity.Document;
import com.background.verification.entity.DocumentCategory;
import com.background.verification.repository.DocumentCategoryRepository;
import com.background.verification.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentCategoryRepository documentCategoryRepository;

    public DocumentService(DocumentRepository documentRepository, DocumentCategoryRepository documentCategoryRepository) {
        this.documentRepository = documentRepository;
        this.documentCategoryRepository = documentCategoryRepository;
    }

    public List<Document> getDocumentsByUsername(String username) {
        return documentRepository.findByUsername(username);
    }

    public boolean updateDocumentStatus(String username, String requestId, String newStatus) {
        Document document = documentRepository.findByUsernameAndRequestId(username, requestId);
        if (document != null) {
            document.setStatus(newStatus);
            documentRepository.save(document);
            return true;
        }
        return false;
    }
    public byte[] getDocumentFile(String username, String requestId) {
        Document document = documentRepository.findByUsernameAndRequestId(username, requestId);
        if (document != null) {
            return document.getDocFile();
        }
        return null;
    }

    public List<PendingDocumentDTO> getPendingDocuments() {
        List<Document> pendingDocuments = documentRepository.findByStatus("Pending");
        List<PendingDocumentDTO> pendingDocumentDTOs = new ArrayList<>();

        for (Document document : pendingDocuments) {
            DocumentCategory documentCategory = documentCategoryRepository.findById(document.getDoctypeId()).orElse(null);
            if (documentCategory != null) {
                PendingDocumentDTO pendingDocumentDTO = new PendingDocumentDTO(
                        document.getUsername(),
                        document.getRequestId(),
                        document.getStatus(),
                        documentCategory.getDocumentName()
                );
                pendingDocumentDTOs.add(pendingDocumentDTO);
            }
        }

        return pendingDocumentDTOs;
    }

}
