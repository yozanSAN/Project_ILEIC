package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.dto.DocumentDTO;
import com.ProjetILEIC.ILIEC.dto.DocumentRequestDTO;
import com.ProjetILEIC.ILIEC.entity.Document;
import com.ProjetILEIC.ILIEC.entity.Stagiaire;
import com.ProjetILEIC.ILIEC.entity.User;
import com.ProjetILEIC.ILIEC.exception.ResourceNotFoundException;
import com.ProjetILEIC.ILIEC.repository.DocumentRepository;
import com.ProjetILEIC.ILIEC.repository.StagiaireRepository;
import com.ProjetILEIC.ILIEC.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DocumentService {

    // Injecting all necessary repositories to validate relationships
    private final DocumentRepository repository;
    private final StagiaireRepository stagiaireRepository;
    private final UserRepository userRepository;

    public DocumentService(DocumentRepository repository,
                           StagiaireRepository stagiaireRepository,
                           UserRepository userRepository) {
        this.repository = repository;
        this.stagiaireRepository = stagiaireRepository;
        this.userRepository = userRepository;
    }

    /**
     * READ: Fetches all files associated with a specific student.
     * Useful for showing a student's dossier profile on the frontend.
     */
    @Transactional(readOnly = true)
    public List<DocumentDTO> getDocumentsByStagiaire(Long stagiaireId) {
        return repository.findByStagiaire_Id(stagiaireId)
                .stream()
                .map(this::toDTO) // Convert database entities to clean DTO maps
                .collect(Collectors.toList());
    }

    /**
     * READ: Filters files by validation status (e.g., PENDING, APPROVED).
     * Critical for secretary back-office review dashboards.
     */
    @Transactional(readOnly = true)
    public List<DocumentDTO> getDocumentsByStatus(String status) {
        // Enforce uppercase so "pending" or "Pending" matches "PENDING" in the database
        return repository.findByStatus(status.toUpperCase())
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * CREATE: Saves a text reference metadata record pointing to where the file is stored.
     */
    public DocumentDTO uploadDocument(DocumentRequestDTO requestDTO) {
        // Step 1: Verify the student actually exists in our system
        Stagiaire stagiaire = stagiaireRepository.findById(requestDTO.getStagiaireId())
                .orElseThrow(() -> new ResourceNotFoundException("Stagiaire not found with ID: " + requestDTO.getStagiaireId()));

        // Step 2: Verify the staff member or user saving this record exists
        User uploader = userRepository.findById(requestDTO.getUploadedById())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + requestDTO.getUploadedById()));

        // Step 3: Instantiate a new Document model and populate the values
        Document doc = new Document();
        doc.setStagiaire(stagiaire);
        doc.setUploadedBy(uploader);

        // Normalize document types to uppercase (e.g., "cin" -> "CIN") for consistent querying
        doc.setDocumentType(requestDTO.getDocumentType().toUpperCase());

        // Storing the local frontend path or cloud URL string link
        doc.setFileUrl(requestDTO.getFileUrl());

        // Crucial Default Rule: Newly added documents must start as "PENDING" until verified by staff
        doc.setStatus("PENDING");

        // Auto-stamp the exact arrival timestamp
        doc.setUploadedAt(LocalDateTime.now());

        // Step 4: Write to database, map the result back to an outgoing DTO, and return it
        return toDTO(repository.save(doc));
    }

    /**
     * UPDATE (Partial): Modifies just the verification status flag of an uploaded document.
     */
    public DocumentDTO updateStatus(Long id, String status) {
        // Step 1: Pull target file metadata row or fail if invalid ID passed
        Document doc = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document metadata record not found with id: " + id));

        // Step 2: Set the updated status string (e.g., "APPROVED" or "REJECTED")
        doc.setStatus(status.toUpperCase());

        // Step 3: Commit updates and return fresh state map
        return toDTO(repository.save(doc));
    }

    /**
     * DELETE: Detaches and removes file registry row out of database history logs completely.
     */
    public void deleteDocument(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Document metadata record not found with id: " + id);
        }
        repository.deleteById(id);
    }

    /**
     * CONVERSION UTILITY: Flattens database objects into flat, clean JSON objects for the frontend.
     * Prevents endless nested recursive database reference loops.
     */
    private DocumentDTO toDTO(Document d) {
        return new DocumentDTO(
                d.getId(),
                d.getStagiaire().getId(),
                d.getStagiaire().getUser().getFullName(), // Fetch nested student full name string
                d.getUploadedBy().getId(),
                d.getUploadedBy().getFullName(),         // Fetch nested uploader creator name string
                d.getDocumentType(),
                d.getFileUrl(),
                d.getStatus(),
                d.getUploadedAt()
        );
    }
}