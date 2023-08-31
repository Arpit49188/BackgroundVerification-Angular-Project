package com.background.verification.repository;


import com.background.verification.entity.VerificationDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationDetailsRepository extends JpaRepository<VerificationDetails, Long> {
    VerificationDetails findByRequestId(String requestId);
}
