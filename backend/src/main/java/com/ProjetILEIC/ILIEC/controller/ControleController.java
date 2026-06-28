package com.ProjetILEIC.ILIEC.controller;

import com.ProjetILEIC.ILIEC.dto.ControleDTO;
import com.ProjetILEIC.ILIEC.dto.ControleRequestDTO;
import com.ProjetILEIC.ILIEC.service.ControleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/controles")
@RequiredArgsConstructor
public class ControleController {

    private final ControleService controleService;

    //GET all controlles /exams
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE', 'FORMATEUR', 'STAGIAIRE')")
    public ResponseEntity<List<ControleDTO>> getAllControles(
            @RequestParam(required = false) Long filiereId,
            @RequestParam(required = false) Long coursId) {

        if (coursId != null)   return ResponseEntity.ok(controleService.getByCours(coursId));
        if (filiereId != null) return ResponseEntity.ok(controleService.getByFiliere(filiereId));
        return ResponseEntity.ok(controleService.getAllControles());
    }
    //GET a single controlle/exam by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE', 'FORMATEUR', 'STAGIAIRE')")
    public ResponseEntity<ControleDTO> getControleById(@PathVariable Long id) {
        return ResponseEntity.ok(controleService.getControleById(id));
    }

    // Create a new controle/exam
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE', 'FORMATEUR')")
    public ResponseEntity<ControleDTO> createControle(@Valid @RequestBody ControleRequestDTO dto) {
        return new ResponseEntity<>(controleService.createControle(dto), HttpStatus.CREATED);
    }

    // Modify a controle/exam
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE', 'FORMATEUR')")
    public ResponseEntity<ControleDTO> updateControle(
            @PathVariable Long id,
            @Valid @RequestBody ControleRequestDTO dto) {
        return ResponseEntity.ok(controleService.updateControle(id, dto));
    }

    // Delete a controle/exam
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public ResponseEntity<Void> deleteControle(@PathVariable Long id) {
        controleService.deleteControle(id);
        return ResponseEntity.noContent().build();
    }
}