package com.ProjetILEIC.ILIEC.controller;

import com.ProjetILEIC.ILIEC.dto.NoteDTO;
import com.ProjetILEIC.ILIEC.dto.NoteRequestDTO;
import com.ProjetILEIC.ILIEC.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/stagiaire/{stagiaireId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE', 'FORMATEUR', 'STAGIAIRE')")
    public ResponseEntity<List<NoteDTO>> getNotesByStagiaire(@PathVariable Long stagiaireId) {
        return ResponseEntity.ok(noteService.getNotesByStagiaire(stagiaireId));
    }

    @GetMapping("/controle/{controleId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE', 'FORMATEUR')")
    public ResponseEntity<List<NoteDTO>> getNotesByControle(@PathVariable Long controleId) {
        return ResponseEntity.ok(noteService.getNotesByControle(controleId));
    }

    @GetMapping("/stagiaire/{stagiaireId}/cours/{coursId}/average")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE', 'FORMATEUR', 'STAGIAIRE')")
    public ResponseEntity<BigDecimal> getAverage(
            @PathVariable Long stagiaireId,
            @PathVariable Long coursId) {
        return ResponseEntity.ok(
                noteService.getAverageForStagiaireInCours(stagiaireId, coursId));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE', 'FORMATEUR')")
    public ResponseEntity<NoteDTO> createNote(@Valid @RequestBody NoteRequestDTO dto) {
        return new ResponseEntity<>(noteService.createNote(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE', 'FORMATEUR')")
    public ResponseEntity<NoteDTO> updateNote(
            @PathVariable Long id,
            @Valid @RequestBody NoteRequestDTO dto) {
        return ResponseEntity.ok(noteService.updateNote(id, dto));
    }
}