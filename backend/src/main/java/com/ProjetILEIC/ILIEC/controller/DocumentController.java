package com.ProjetILEIC.ILIEC.controller;

import com.ProjetILEIC.ILIEC.dto.DocumentDTO;
import com.ProjetILEIC.ILIEC.dto.DocumentRequestDTO;
import com.ProjetILEIC.ILIEC.service.DocumentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService service;

    public DocumentController(DocumentService service) {
        this.service = service;
    }

    // GET: Find all documents belonging to a single student profile
    @GetMapping("/stagiaire/{stagiaireId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public ResponseEntity<List<DocumentDTO>> getByStagiaire(@PathVariable Long stagiaireId) {
        return ResponseEntity.ok(service.getDocumentsByStagiaire(stagiaireId));
    }

    // GET: Filter and show documents matching a certain verification status
    @GetMapping("/status/{status}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public ResponseEntity<List<DocumentDTO>> getByStatus(@PathVariable String status) {
        return ResponseEntity.ok(service.getDocumentsByStatus(status));
    }

    // POST: Record metadata for a freshly saved registration document
    // Triggered right after the frontend commits a file upload to its local folder/cloud
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public ResponseEntity<DocumentDTO> uploadDocument(@Valid @RequestBody DocumentRequestDTO requestDTO) {
        DocumentDTO created = service.uploadDocument(requestDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // PATCH: Approve or Reject a document without updating any other fields
    // URL Context Example: PATCH /api/documents/1/status?status=APPROVED
    @Valid
    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public ResponseEntity<DocumentDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }

    // DELETE: delete a specific document's tracking record from logs
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        service.deleteDocument(id);
        return ResponseEntity.noContent().build(); // Return clean 204 No Content state
    }
}

/*
 * ============================================================================
 * ENDPOINT DESCRIPTIONS (HOW THIS CONTROLLER WORKS)
 * ============================================================================
 * * 1. GET /stagiaire/{id}
 * - Looks up all uploaded files linked to a student (e.g., CIN copy, Baccalaureate).
 *
 * * 2. GET /status/{status}
 * - Filters documents by status. Vital for back-office secretary review boards
 * to look up which submissions require checking.
 *
 * * 3. POST /
 * - Receives a text path link from the frontend app layer (e.g., "/uploads/bac_12.pdf")
 * and maps it to the database table. Automatically marks new uploads as "PENDING".
 *
 * * 4. PATCH /{id}/status?status=APPROVED
 * - A surgical update endpoint enabling staff clerks to click single "Approve" or "Reject"
 * dashboard triggers without manipulating user file profiles or URL paths.
 *
 * * 5. DELETE /{id}
 * - Permanently deletes a file tracking record line directly out of relational database indexes.
 */