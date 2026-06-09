package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.entity.Program;
import com.ProjetILEIC.ILIEC.exception.ResourceNotFoundException;
import com.ProjetILEIC.ILIEC.repository.ProgrameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProgramService {

    private final ProgrameRepository programeRepository;

    public ProgramService(ProgrameRepository programeRepository) {
        this.programeRepository = programeRepository;
    }

    @Transactional(readOnly = true)
    public List<Program> getAllPrograms() {
        return programeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Program getProgramById(Long id) {
        return programeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Program not found with id: " + id));
    }

    public Program createProgram(Program program) {
        return programeRepository.save(program);
    }

    public Program updateProgram(Long id, Program updated) {
        Program existing = getProgramById(id);
        existing.setName(updated.getName());
        existing.setDurationYears(updated.getDurationYears());
        existing.setMonthlyFee(updated.getMonthlyFee());
        return programeRepository.save(existing);
    }

    public void deleteProgram(Long id) {
        if (!programeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Program not found with id: " + id);
        }
        programeRepository.deleteById(id);
    }
}
