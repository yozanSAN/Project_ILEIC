package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.dto.CoursDTO;
import com.ProjetILEIC.ILIEC.dto.CoursRequestDTO;
import com.ProjetILEIC.ILIEC.entity.Cours;
import com.ProjetILEIC.ILIEC.entity.Filiere;
import com.ProjetILEIC.ILIEC.exception.DuplicateResourceException;
import com.ProjetILEIC.ILIEC.exception.ResourceNotFoundException;
import com.ProjetILEIC.ILIEC.repository.CoursRepository;
import com.ProjetILEIC.ILIEC.repository.FiliereRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CoursService {

    private final CoursRepository coursRepository;
    private final FiliereRepository filiereRepository;

    public CoursService(CoursRepository coursRepository, FiliereRepository filiereRepository) {
        this.coursRepository = coursRepository;
        this.filiereRepository = filiereRepository;
    }

    @Transactional(readOnly = true)
    public List<CoursDTO> getAllCours() {
        return coursRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CoursDTO> getByFiliere(Long filiereId) {
        return coursRepository.findByFiliere_Id(filiereId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CoursDTO getCoursById(Long id) {
        return coursRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Cours not found with id: " + id));
    }


    // DTO-based create: maps DTO → entity, reuses existing createCours(Cours, Long) for validation
    public CoursDTO createCours(CoursRequestDTO dto) {
        Cours cours = new Cours();
        cours.setName(dto.getName());
        cours.setCode(dto.getCode());
        cours.setYearLevel(dto.getYearLevel());
        cours.setSemester(dto.getSemester());
        cours.setHoursTotal(dto.getHoursTotal());
        Cours savedCours = saveAndValidateEntity(cours, dto.getFiliereId());
        return toDTO(savedCours);
    }
    // PRIVATE HELPER: Kept private to protect raw entity mapping rules from escaping the service
    private Cours saveAndValidateEntity(Cours cours, Long filiereId) {
        if (coursRepository.existsByCode(cours.getCode())) {
            throw new DuplicateResourceException("Cours code already exists: " + cours.getCode());
        }
        Filiere filiere = filiereRepository.findById(filiereId)
                .orElseThrow(() -> new ResourceNotFoundException("Filiere not found with id: " + filiereId));

        cours.setFiliere(filiere);
        return coursRepository.save(cours);
    }

    public CoursDTO toDTO(Cours c) {
        return new CoursDTO(
                c.getId(),
                c.getCode(),
                c.getName(),
                c.getFiliere().getId(),
                c.getFiliere().getName(),
                c.getFiliere().getCentre().getId(),
                c.getFiliere().getCentre().getName(),
                c.getYearLevel(),
                c.getSemester(),
                c.getHoursTotal()
        );
    }

    public void deleteCours(Long id) {
        if (!coursRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cours not found with id: " + id);
        }
        coursRepository.deleteById(id);
    }
}
