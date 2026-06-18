package com.ProjetILEIC.ILIEC.controller;

import com.ProjetILEIC.ILIEC.dto.CoursDTO;
import com.ProjetILEIC.ILIEC.dto.CoursRequestDTO;
import com.ProjetILEIC.ILIEC.service.CoursService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cours")
@RequiredArgsConstructor
public class CoursController {

    private final CoursService coursService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE', 'FORMATEUR', 'STAGIAIRE')")
    public ResponseEntity<List<CoursDTO>> getAllCours(
            @RequestParam(required = false) Long filiereId) {

        List<CoursDTO> result = (filiereId != null
                ? coursService.getByFiliere(filiereId)
                : coursService.getAllCours())
                .stream()
                .map(coursService::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE', 'FORMATEUR', 'STAGIAIRE')")
    public ResponseEntity<CoursDTO> getCoursById(@PathVariable Long id) {
        return ResponseEntity.ok(coursService.toDTO(coursService.getCoursById(id)));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE', 'FORMATEUR')")
    public ResponseEntity<CoursDTO> createCours(@RequestBody CoursRequestDTO dto) {
        return new ResponseEntity<>(coursService.createCours(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public ResponseEntity<Void> deleteCours(@PathVariable Long id) {
        coursService.deleteCours(id);
        return ResponseEntity.noContent().build();
    }
}