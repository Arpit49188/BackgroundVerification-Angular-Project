package com.background.verification.entity;



import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "verification_details")
public class VerificationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String requestId;
    private Date requestStartDate;
    private Date requestEndDate;

    // Getters and setters

    // Constructors

    public VerificationDetails() {
    }

    public VerificationDetails(Long id, String requestId, Date requestStartDate, Date requestEndDate) {
        this.id = id;
        this.requestId = requestId;
        this.requestStartDate = requestStartDate;
        this.requestEndDate = requestEndDate;
    }
    // Other methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Date getRequestStartDate() {
        return requestStartDate;
    }

    public void setRequestStartDate(Date requestStartDate) {
        this.requestStartDate = requestStartDate;
    }

    public Date getRequestEndDate() {
        return requestEndDate;
    }

    public void setRequestEndDate(Date requestEndDate) {
        this.requestEndDate = requestEndDate;
    }
}
