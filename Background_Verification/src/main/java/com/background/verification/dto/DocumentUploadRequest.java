package com.background.verification.dto;

import org.springframework.web.multipart.MultipartFile;

public class DocumentUploadRequest {
    private String username;
    private String doctypeId;
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDoctypeId() {
        return doctypeId;
    }

    public void setDoctypeId(String doctypeId) {
        this.doctypeId = doctypeId;
    }
// Other fields, getters, and setters
}
