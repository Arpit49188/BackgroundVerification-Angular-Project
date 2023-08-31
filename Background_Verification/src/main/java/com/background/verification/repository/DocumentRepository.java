package com.background.verification.repository;




import com.background.verification.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByUsername(String username);
    Document findByUsernameAndRequestId(String username, String requestId);

    List<Document> findByStatus(String status);
}
