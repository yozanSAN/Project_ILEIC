package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.dto.ProgramDTO;
import com.ProjetILEIC.ILIEC.dto.ProgramRequestDTO;
import com.ProjetILEIC.ILIEC.entity.Program;
import com.ProjetILEIC.ILIEC.exception.ResourceNotFoundException;
import com.ProjetILEIC.ILIEC.repository.ProgrameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProgramService {

    private final ProgrameRepository programeRepository;

    public ProgramService(ProgrameRepository programeRepository) {
        this.programeRepository = programeRepository;
    }

    @Transactional(readOnly = true)
    public List<ProgramDTO> getAllPrograms() {
        return programeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProgramDTO getProgramById(Long id) {
        Program program = programeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Program not found with id: " + id));
        return convertToDTO(program);
    }

    public ProgramDTO createProgram(ProgramRequestDTO requestDTO) {
        Program program = new Program();
        program.setName(requestDTO.getName());
        program.setDurationYears(requestDTO.getDurationYears());
        program.setMonthlyFee(requestDTO.getMonthlyFee());

        Program savedProgram = programeRepository.save(program);
        return convertToDTO(savedProgram);
    }

    public ProgramDTO updateProgram(Long id, ProgramRequestDTO requestDTO) {
        Program existing = programeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Program not found with id: " + id));

        existing.setName(requestDTO.getName());
        existing.setDurationYears(requestDTO.getDurationYears());
        existing.setMonthlyFee(requestDTO.getMonthlyFee());

        Program updatedProgram = programeRepository.save(existing);
        return convertToDTO(updatedProgram);
    }

    public void deleteProgram(Long id) {
        if (!programeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Program not found with id: " + id);
        }
        programeRepository.deleteById(id);
    }

    // Helper method to convert Entity -> Outgoing DTO
    private ProgramDTO convertToDTO(Program program) {
        ProgramDTO dto = new ProgramDTO();
        dto.setId(program.getId());
        dto.setName(program.getName());
        dto.setDurationYears(program.getDurationYears());
        dto.setMonthlyFee(program.getMonthlyFee());
        return dto;
    }
}