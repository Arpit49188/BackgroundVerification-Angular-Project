package com.background.verification.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long docId;

    @Lob
    private byte[] docFile;

    private String username;
    private String status = "Pending";
    private String doctypeId;
    private String requestId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public byte[] getDocFile() {
        return docFile;
    }

    public void setDocFile(byte[] docFile) {
        this.docFile = docFile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDoctypeId() {
        return doctypeId;
    }

    public void setDoctypeId(String doctypeId) {
        this.doctypeId = doctypeId;
    }
    // Getters and setters


    public Document(Long docId, byte[] docFile, String username, String status, String doctypeId, String requestId) {
        this.docId = docId;
        this.docFile = docFile;
        this.username = username;
        this.status = status;
        this.doctypeId = doctypeId;
        this.requestId = requestId;
    }

    public Document() {
    }
// Constructors

    // Other methods
}
