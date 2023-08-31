package com.background.verification.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "document_category")
public class DocumentCategory {

    @Id
    @Column(length=100)
    private String doctypeId;


    private String documentName;

    public String getDoctypeId() {
        return doctypeId;
    }

    public void setDoctypeId(String doctypeId) {
        this.doctypeId = doctypeId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public DocumentCategory(String doctypeId, String documentName) {
        this.doctypeId = doctypeId;
        this.documentName = documentName;
    }

    public DocumentCategory() {
    }
    // Getters and setters

    // Constructors

    // Other methods
}
