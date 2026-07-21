package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.dto.ControleDTO;
import com.ProjetILEIC.ILIEC.dto.ControleRequestDTO;
import com.ProjetILEIC.ILIEC.entity.Controle;
import com.ProjetILEIC.ILIEC.entity.Cours;
import com.ProjetILEIC.ILIEC.entity.Filiere;
import com.ProjetILEIC.ILIEC.exception.ResourceNotFoundException;
import com.ProjetILEIC.ILIEC.repository.ControleRepository;
import com.ProjetILEIC.ILIEC.repository.CoursRepository;
import com.ProjetILEIC.ILIEC.repository.FiliereRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ControleService {

    private final ControleRepository controleRepository;
    private final CoursRepository coursRepository;
    private final FiliereRepository filiereRepository;

    public ControleService(ControleRepository controleRepository,
                           CoursRepository coursRepository,
                           FiliereRepository filiereRepository) {
        this.controleRepository = controleRepository;
        this.coursRepository = coursRepository;
        this.filiereRepository = filiereRepository;
    }

    // --- READ ---

    @Transactional(readOnly = true)
    public List<ControleDTO> getAllControles() {
        return controleRepository.findAll()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ControleDTO getControleById(Long id) {
        return toDTO(findOrThrow(id));
    }

    @Transactional(readOnly = true)
    public List<ControleDTO> getByCours(Long coursId) {
        return controleRepository.findByCours_Id(coursId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ControleDTO> getByFiliere(Long filiereId) {
        return controleRepository.findByFiliere_Id(filiereId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    // --- CREATE ---

    public ControleDTO createControle(ControleRequestDTO dto) {
        Cours cours = coursRepository.findById(dto.getCoursId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cours not found: " + dto.getCoursId()));

        Filiere filiere = filiereRepository.findById(dto.getFiliereId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Filiere not found: " + dto.getFiliereId()));

        // Cours must belong to the same filière it's being attached to
        if (!cours.getFiliere().getId().equals(filiere.getId())) {
            throw new IllegalArgumentException(
                    "Cours does not belong to filière " + dto.getFiliereId());
        }

        Controle controle = new Controle();
        controle.setCours(cours);
        controle.setFiliere(filiere);
        controle.setTitle(dto.getTitle());
        controle.setType(dto.getType());
        controle.setExamDate(dto.getExamDate());
        controle.setCoefficient(dto.getCoefficient());
        controle.setStatus(dto.getStatus());

        return toDTO(controleRepository.save(controle));
    }

    // --- UPDATE ---

    public ControleDTO updateControle(Long id, ControleRequestDTO dto) {
        Controle existing = findOrThrow(id);
        existing.setTitle(dto.getTitle());
        existing.setType(dto.getType());
        existing.setExamDate(dto.getExamDate());
        existing.setCoefficient(dto.getCoefficient());
        existing.setStatus(dto.getStatus());
        return toDTO(controleRepository.save(existing));
    }

    // --- DELETE ---

    public void deleteControle(Long id) {
        if (!controleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Controle not found with id: " + id);
        }
        controleRepository.deleteById(id);
    }

    // --- HELPERS ---

    private Controle findOrThrow(Long id) {
        return controleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Controle not found with id: " + id));
    }

    // --- DTO CONVERSION ---

    public ControleDTO toDTO(Controle c) {
        return new ControleDTO(
                c.getId(),
                c.getCours().getId(),
                c.getCours().getName(),
                c.getFiliere().getId(),
                c.getFiliere().getName(),
                c.getTitle(),
                c.getType(),
                c.getExamDate(),
                c.getCoefficient(),
                c.getStatus()
        );
    }
}