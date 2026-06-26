package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.dto.ModulesFormateurDTO;
import com.ProjetILEIC.ILIEC.dto.ModulesFormateurRequestDTO;
import com.ProjetILEIC.ILIEC.entity.Cours;
import com.ProjetILEIC.ILIEC.entity.Formateur;
import com.ProjetILEIC.ILIEC.entity.ModulesFormateur;
import com.ProjetILEIC.ILIEC.exception.ResourceNotFoundException;
import com.ProjetILEIC.ILIEC.repository.CoursRepository;
import com.ProjetILEIC.ILIEC.repository.FormateurRepository;
import com.ProjetILEIC.ILIEC.repository.ModulesFormateurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ModulesFormateurService {

    private final ModulesFormateurRepository repository;
    private final FormateurRepository formateurRepository;
    private final CoursRepository coursRepository;

    public ModulesFormateurService(ModulesFormateurRepository repository,
                                   FormateurRepository formateurRepository,
                                   CoursRepository coursRepository) {
        this.repository = repository;
        this.formateurRepository = formateurRepository;
        this.coursRepository = coursRepository;
    }

    // --- READ ---

    @Transactional(readOnly = true)
    public List<ModulesFormateurDTO> getAssignmentsByFormateur(Long formateurId) {
        return repository.findByFormateur_Id(formateurId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ModulesFormateurDTO> getAssignmentsByCours(Long coursId) {
        return repository.findByCours_Id(coursId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    // --- CREATE ---

    public ModulesFormateurDTO assignModule(ModulesFormateurRequestDTO requestDTO) {
        // Business Rule: Ensure teacher isn't assigned to the same course twice
        if (repository.existsByFormateur_IdAndCours_Id(requestDTO.getFormateurId(), requestDTO.getCoursId())) {
            throw new IllegalArgumentException("This teacher is already assigned to this subject.");
        }

        Formateur formateur = formateurRepository.findById(requestDTO.getFormateurId())
                .orElseThrow(() -> new ResourceNotFoundException("Formateur not found: " + requestDTO.getFormateurId()));

        Cours cours = coursRepository.findById(requestDTO.getCoursId())
                .orElseThrow(() -> new ResourceNotFoundException("Cours not found: " + requestDTO.getCoursId()));

        ModulesFormateur assignment = new ModulesFormateur();
        assignment.setFormateur(formateur);
        assignment.setCours(cours);
        assignment.setAcademicYear(requestDTO.getAcademicYear());
        assignment.setSemester(requestDTO.getSemester());

        return toDTO(repository.save(assignment));
    }

    // --- UPDATE ---

    public ModulesFormateurDTO updateAssignment(Long id, ModulesFormateurRequestDTO requestDTO) {
        ModulesFormateur assignment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with id: " + id));

        Formateur formateur = formateurRepository.findById(requestDTO.getFormateurId())
                .orElseThrow(() -> new ResourceNotFoundException("Formateur not found: " + requestDTO.getFormateurId()));

        Cours cours = coursRepository.findById(requestDTO.getCoursId())
                .orElseThrow(() -> new ResourceNotFoundException("Cours not found: " + requestDTO.getCoursId()));

        // Double check duplication if keys are changing
        if (!assignment.getFormateur().getId().equals(requestDTO.getFormateurId()) ||
                !assignment.getCours().getId().equals(requestDTO.getCoursId())) {
            if (repository.existsByFormateur_IdAndCours_Id(requestDTO.getFormateurId(), requestDTO.getCoursId())) {
                throw new IllegalArgumentException("This teacher assignment combination already exists.");
            }
        }

        assignment.setFormateur(formateur);
        assignment.setCours(cours);
        assignment.setAcademicYear(requestDTO.getAcademicYear());
        assignment.setSemester(requestDTO.getSemester());

        return toDTO(repository.save(assignment));
    }

    // --- DELETE ---

    public void removeAssignment(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Assignment not found with id: " + id);
        }
        repository.deleteById(id);
    }

    // --- DTO CONVERSION ---

    public ModulesFormateurDTO toDTO(ModulesFormateur mf) {
        return new ModulesFormateurDTO(
                mf.getId(),
                mf.getFormateur().getId(),
                mf.getFormateur().getUser().getFullName(),
                mf.getCours().getId(),
                mf.getCours().getName(),
                mf.getAcademicYear(),
                mf.getSemester()
        );
    }
}