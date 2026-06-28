package com.ProjetILEIC.ILIEC.controller;

import com.ProjetILEIC.ILIEC.dto.SecretaryDTO;
import com.ProjetILEIC.ILIEC.dto.SecretaryRequestDTO;
import com.ProjetILEIC.ILIEC.service.SecretaryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/secretaries")
public class SecretaireController {

    private final SecretaryService secretaryService ;

    public SecretaireController(SecretaryService secretaryService) {
        this.secretaryService = secretaryService;
    }

     //GET /api/secretaries
     //Fetches all secretaries currently registered in the database.
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<SecretaryDTO>> getAllSecretaries() {
        return ResponseEntity.ok(secretaryService.getAllSecretaries());
    }

     //GET /api/secretaries/{id}
     //Fetches a single secretary record matching the given database primary ID.
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<SecretaryDTO> getSecretaryById(@PathVariable Long id) {
        return ResponseEntity.ok(secretaryService.getSecretaryById(id));
    }

    // GET /api/secretaries/centre/{centreId}
    //get all secretaries working for a specific center by centreId
    @GetMapping("/centre/{centreId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<SecretaryDTO>> getSecretariesByCentre(@PathVariable Long centreId) {
        return ResponseEntity.ok(secretaryService.getByCentre(centreId));
    }

    //POST /api/secretaries
    //Create a new secretary
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<SecretaryDTO> createSecretary(@Valid @RequestBody SecretaryRequestDTO requestDTO) {
        SecretaryDTO created = secretaryService.createSecretary(requestDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

     //PUT /api/secretaries/{id}
     //Updates an existing secretary
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<SecretaryDTO> updateSecretary(
            @PathVariable Long id,
            @Valid @RequestBody SecretaryRequestDTO requestDTO) {
        return ResponseEntity.ok(secretaryService.updateSecretary(id, requestDTO));
    }

     //DELETE /api/secretaries/{id}
     //Unlinks and destroys the secretary record assignment map context from the database table.
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> deleteSecretary(@PathVariable Long id) {
        secretaryService.deleteSecretary(id);
        return ResponseEntity.noContent().build();
    }
}