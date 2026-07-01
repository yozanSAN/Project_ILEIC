package com.ProjetILEIC.ILIEC.controller;

import com.ProjetILEIC.ILIEC.dto.FormateurDTO;
import com.ProjetILEIC.ILIEC.dto.FormateurRequestDTO;
import com.ProjetILEIC.ILIEC.service.FormateurService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formateurs")
@RequiredArgsConstructor
public class FormateurController {

    private final FormateurService formateurService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public ResponseEntity<List<FormateurDTO>> getAllFormateurs(
            @RequestParam(required = false) Long centreId) {

        if (centreId != null) {
            return ResponseEntity.ok(formateurService.getByCentre(centreId));
        }
        return ResponseEntity.ok(formateurService.getAllFormateurs());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE', 'FORMATEUR')")
    public ResponseEntity<FormateurDTO> getFormateurById(@PathVariable Long id) {
        return ResponseEntity.ok(formateurService.getFormateurById(id));
    }

    //CREATE a new FORMATEUR
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public ResponseEntity<FormateurDTO> createFormateur(@Valid @RequestBody FormateurRequestDTO dto) {
        return new ResponseEntity<>(formateurService.createFormateur(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public ResponseEntity<FormateurDTO> updateFormateur(
            @PathVariable Long id,
            @Valid @RequestBody FormateurRequestDTO dto) {
        return ResponseEntity.ok(formateurService.updateFormateur(id, dto));
    }

    //CREATE NEW FORMATEUR
    @PostMapping("/{id}/centres/{centreId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public ResponseEntity<FormateurDTO> assignCentre(
            @PathVariable Long id,
            @PathVariable Long centreId) {
        return ResponseEntity.ok(formateurService.assignCentre(id, centreId));
    }

    //SOFT DELETE
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN','SECRETAIRE')")
    public ResponseEntity<Void> deactivateFormateur(@PathVariable Long id) {
        formateurService.deactivateFormateur(id);
        return ResponseEntity.noContent().build();
    }

    //REMOVE A FORMATEUR FORM A CENTRE
    @DeleteMapping("/{id}/centres/{centreId}")
    @PreAuthorize("hasAuthority('ADMIN','SECRETAIRE)")
    public ResponseEntity<Void> removeFormateurFromCentre(
            @PathVariable("id") Long id,
            @PathVariable("centreId") Long centreId) {

        formateurService.removeFormateurFromCentre(id, centreId);
        return ResponseEntity.noContent().build();
    }

}