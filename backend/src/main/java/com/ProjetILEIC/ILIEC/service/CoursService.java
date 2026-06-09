package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.entity.Cours;
import com.ProjetILEIC.ILIEC.entity.Filiere;
import com.ProjetILEIC.ILIEC.exception.DuplicateResourceException;
import com.ProjetILEIC.ILIEC.exception.ResourceNotFoundException;
import com.ProjetILEIC.ILIEC.repository.CoursRepository;
import com.ProjetILEIC.ILIEC.repository.FiliereRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Cours> getAllCours() {
        return coursRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Cours> getByFiliere(Long filiereId) {
        return coursRepository.findByFiliere_Id(filiereId);
    }

    @Transactional(readOnly = true)
    public Cours getCoursById(Long id) {
        return coursRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cours not found with id: " + id));
    }

    public Cours createCours(Cours cours, Long filiereId) {
        if (coursRepository.existsByCode(cours.getCode())) {
            throw new DuplicateResourceException("Cours code already exists: " + cours.getCode());
        }
        Filiere filiere = filiereRepository.findById(filiereId)
                .orElseThrow(() -> new ResourceNotFoundException("Filiere not found with id: " + filiereId));

        cours.setFiliere(filiere);
        return coursRepository.save(cours);
    }

    public void deleteCours(Long id) {
        if (!coursRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cours not found with id: " + id);
        }
        coursRepository.deleteById(id);
    }
}
