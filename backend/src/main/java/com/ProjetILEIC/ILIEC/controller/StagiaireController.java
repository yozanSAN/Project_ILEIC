package com.ProjetILEIC.ILIEC.controller;

import com.ProjetILEIC.ILIEC.dto.StagiaireDTO;
import com.ProjetILEIC.ILIEC.service.StagiaireService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stagiaires")
public class StagiaireController {
    private final StagiaireService stagiaireService;

    public StagiaireController(StagiaireService stagiaireService) {
        this.stagiaireService = stagiaireService;
    }

    //Get all stagiaires with optional filtering by Centre or Filiere.
    // Example: /api/stagiaires?centreId=1&filiereId=2
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public ResponseEntity<List<StagiaireDTO>> getAllStagiaires(
            @RequestParam(required = false) Long centreId,
            @RequestParam(required = false) Long filiereId) {
        // If both filters are provided
        if (centreId != null && filiereId != null) {
            return ResponseEntity.ok(stagiaireService.getByCentreAndFiliere(centreId, filiereId));
        }
        // Filter by Centre only
        else if (centreId != null) {
            return ResponseEntity.ok(stagiaireService.getByCentre(centreId));
        }
        // Filter by Filiere only
        else if (filiereId != null) {
            return ResponseEntity.ok(stagiaireService.getByFiliere(filiereId));
        }
        // No filters: return all
        else {
            return ResponseEntity.ok(stagiaireService.getAllStagiaires());
        }
    }

    //GET A SINGLE ONE BY ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE', 'FORMATEUR')")
    public ResponseEntity<StagiaireDTO> getStagiaireById(@PathVariable Long id) {
        return ResponseEntity.ok(stagiaireService.getStagiaireById(id));
    }

    //-----------------------------CREATE----------



}
