package com.ProjetILEIC.ILIEC.controller;

import com.ProjetILEIC.ILIEC.dto.ProgramDTO;
import com.ProjetILEIC.ILIEC.dto.ProgramRequestDTO;
import com.ProjetILEIC.ILIEC.service.ProgramService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
public class ProgramController {

    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping
    public ResponseEntity<List<ProgramDTO>> getAllPrograms() {
        return ResponseEntity.ok(programService.getAllPrograms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramDTO> getProgramById(@PathVariable Long id) {
        return ResponseEntity.ok(programService.getProgramById(id));
    }

    @PostMapping
    public ResponseEntity<ProgramDTO> createProgram(@Valid @RequestBody ProgramRequestDTO requestDTO) {
        ProgramDTO created = programService.createProgram(requestDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgramDTO> updateProgram(
            @PathVariable Long id,
            @Valid @RequestBody ProgramRequestDTO requestDTO) {
        return ResponseEntity.ok(programService.updateProgram(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id) {
        programService.deleteProgram(id);
        return ResponseEntity.noContent().build();
    }
}