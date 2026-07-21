package com.ProjetILEIC.ILIEC.controller;

import com.ProjetILEIC.ILIEC.dto.ProgramDTO;
import com.ProjetILEIC.ILIEC.dto.ProgramRequestDTO;
import com.ProjetILEIC.ILIEC.service.ProgramService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
public class ProgramController {

    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    // GET : retrieve the complete list of all academic programs/modul in the system
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE', 'FORMATEUR', 'STAGIAIRE')")
    public ResponseEntity<List<ProgramDTO>> getAllPrograms() {
        return ResponseEntity.ok(programService.getAllPrograms());
    }

    // GET : retrieve details for one specific program using its ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE', 'FORMATEUR', 'STAGIAIRE')")
    public ResponseEntity<ProgramDTO> getProgramById(@PathVariable Long id) {
        return ResponseEntity.ok(programService.getProgramById(id));
    }

    // POST : create a brand new academic program/modul configuration row
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public ResponseEntity<ProgramDTO> createProgram(@Valid @RequestBody ProgramRequestDTO requestDTO) {
        ProgramDTO created = programService.createProgram(requestDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // PUT : update the entire structural definition details of an existing program
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public ResponseEntity<ProgramDTO> updateProgram(
            @PathVariable Long id,
            @Valid @RequestBody ProgramRequestDTO requestDTO) {
        return ResponseEntity.ok(programService.updateProgram(id, requestDTO));
    }

    // DELETE : remove a program configuration permanently from the system records
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id) {
        programService.deleteProgram(id);
        return ResponseEntity.noContent().build();
    }
}