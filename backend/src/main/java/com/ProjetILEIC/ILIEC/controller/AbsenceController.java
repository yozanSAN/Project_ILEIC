package com.ProjetILEIC.ILIEC.controller;

import com.ProjetILEIC.ILIEC.dto.AbsenceDTO;
import com.ProjetILEIC.ILIEC.dto.AbsenceRequestDTO;
import com.ProjetILEIC.ILIEC.service.AbsenceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/absences")
public class AbsenceController {

    private final AbsenceService absenceService;

    public AbsenceController(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }

    // GET : retrieve all absences for a specific student
    @GetMapping("/stagiaire/{stagiaireId}")
    @PreAuthorize("hasAnyAuthority ('ADMIN'),('SECRETAIRE')")
    public ResponseEntity<List<AbsenceDTO>> getAbsencesByStagiaire(@PathVariable Long stagiaireId) {
        return ResponseEntity.ok(absenceService.getAbsencesByStagiaire(stagiaireId));
    }

    // GET : retrieve all students that missed a specific class session
    @GetMapping("/cours/{coursId}")
    @PreAuthorize("hasAnyAuthority ('ADMIN'),('SECRETAIRE')")
    public ResponseEntity<List<AbsenceDTO>> getAbsencesByCours(@PathVariable Long coursId) {
        return ResponseEntity.ok(absenceService.getAbsencesByCours(coursId));
    }

    // GET : check if student crossed the 10 unjustified absences exclusion limit
    @GetMapping("/stagiaire/{stagiaireId}/risk")
    @PreAuthorize("hasAnyAuthority ('ADMIN'),('SECRETAIRE')")
    public ResponseEntity<Map<String, Object>> checkAbsenceRisk(@PathVariable Long stagiaireId) {
        long count = absenceService.countUnjustifiedAbsences(stagiaireId);
        boolean atRisk = absenceService.isAtRisk(stagiaireId);

        return ResponseEntity.ok(Map.of(
                "unjustifiedCount", count,
                "isAtRiskOfExclusion", atRisk,
                "limitThreshold", 10
        ));
    }

    // POST : record a new student absence
    @PostMapping
    @PreAuthorize("hasAnyAuthority ('ADMIN'),('SECRETAIRE')")
    public ResponseEntity<AbsenceDTO> recordAbsence(@Valid @RequestBody AbsenceRequestDTO requestDTO) {
        AbsenceDTO created = absenceService.recordAbsence(requestDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // PATCH : change an absence status to JUSTIFIED and add reason remarks
    @PatchMapping("/{id}/justify")
    @PreAuthorize("hasAnyAuthority ('ADMIN'),('SECRETAIRE')")
    public ResponseEntity<AbsenceDTO> justifyAbsence(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        String remarks = body.getOrDefault("remarks", "Justified via administration.");
        return ResponseEntity.ok(absenceService.justifyAbsence(id, remarks));
    }
}