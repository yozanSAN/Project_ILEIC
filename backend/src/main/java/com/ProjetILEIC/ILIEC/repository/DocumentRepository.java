package com.ProjetILEIC.ILIEC.repository;

import com.ProjetILEIC.ILIEC.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findByStagiaire_Id(Long stagiaireId);

    List<Document> findByStagiaire_IdAndDocumentType(Long stagiaireId, String documentType);

    List<Document> findByStatus(String status);
    long countByStatus(String status);
}
