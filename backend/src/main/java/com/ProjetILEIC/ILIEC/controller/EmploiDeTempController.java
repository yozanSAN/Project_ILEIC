package com.ProjetILEIC.ILIEC.controller;

import com.ProjetILEIC.ILIEC.dto.EmploiDeTempDTO;
import com.ProjetILEIC.ILIEC.dto.EmploiDeTempRequestDTO;
import com.ProjetILEIC.ILIEC.service.EmploiDeTempService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emploidetemp")
public class EmploiDeTempController {

    private final EmploiDeTempService emploiDeTempService;

    public EmploiDeTempController(EmploiDeTempService emploiDeTempService) {
        this.emploiDeTempService = emploiDeTempService;
    }

    // GET : retrieve the weekly schedule for a specific filiere
    @GetMapping("/filiere/{filiereId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE', 'FORMATEUR', 'STAGIAIRE')")
    public ResponseEntity<List<EmploiDeTempDTO>> getByFiliere(@PathVariable Long filiereId) {
        return ResponseEntity.ok(emploiDeTempService.getTimetableByFiliere(filiereId));
    }

    // GET : retrieve the schedule for a specific centre
    @GetMapping("/centre/{centreId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE', 'FORMATEUR', 'STAGIAIRE')")
    public ResponseEntity<List<EmploiDeTempDTO>> getByCentre(@PathVariable Long centreId) {
        return ResponseEntity.ok(emploiDeTempService.getTimetableByCentre(centreId));
    }

    // GET : retrieve the assigned schedule for a specific teacher(formateur)
    @GetMapping("/formateur/{formateurId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE', 'FORMATEUR', 'STAGIAIRE')")
    public ResponseEntity<List<EmploiDeTempDTO>> getByFormateur(@PathVariable Long formateurId) {
        return ResponseEntity.ok(emploiDeTempService.getTimetableByFormateur(formateurId));
    }

    // POST : create a new EmploiDeTemp slot assignment
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public ResponseEntity<EmploiDeTempDTO> createSlot(@Valid @RequestBody EmploiDeTempRequestDTO requestDTO) {
        EmploiDeTempDTO created = emploiDeTempService.createScheduleSlot(requestDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // PUT : update an existing emploi de temp slot full configuration
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public ResponseEntity<EmploiDeTempDTO> updateSlot(
            @PathVariable Long id,
            @Valid @RequestBody EmploiDeTempRequestDTO requestDTO) {
        EmploiDeTempDTO updated = emploiDeTempService.updateScheduleSlot(id, requestDTO);
        return ResponseEntity.ok(updated);
    }

    // DELETE : remove a scheduled EmploiDeTemp slot
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN' ,'SECRETAIRE')")
    public ResponseEntity<Void> deleteSlot(@PathVariable Long id) {
        emploiDeTempService.deleteScheduleSlot(id);
        return ResponseEntity.noContent().build();
    }
}