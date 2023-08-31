package com.background.verification.dto;

public class PendingDocumentDTO {
    private String username;
    private String requestId;
    private String status;
    private String documentName;

    public PendingDocumentDTO(String username, String requestId, String status, String documentName) {
        this.username = username;
        this.requestId = requestId;
        this.status = status;
        this.documentName = documentName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }
    // Getters and setters
}
