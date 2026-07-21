package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.dto.CentreDTO;
import com.ProjetILEIC.ILIEC.dto.FiliereDTO;
import com.ProjetILEIC.ILIEC.dto.FiliereRequestDTO;
import com.ProjetILEIC.ILIEC.dto.ProgramDTO;
import com.ProjetILEIC.ILIEC.entity.Centre;
import com.ProjetILEIC.ILIEC.entity.Filiere;
import com.ProjetILEIC.ILIEC.entity.Program;
import com.ProjetILEIC.ILIEC.exception.ResourceNotFoundException;
import com.ProjetILEIC.ILIEC.repository.CentreRepository;
import com.ProjetILEIC.ILIEC.repository.FiliereRepository;
import com.ProjetILEIC.ILIEC.repository.ProgrameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FiliereService {

    private final FiliereRepository filiereRepository;
    private final CentreRepository centreRepository;
    private final ProgrameRepository programeRepository;

    public FiliereService(FiliereRepository filiereRepository,
                          CentreRepository centreRepository,
                          ProgrameRepository programeRepository) {
        this.filiereRepository = filiereRepository;
        this.centreRepository = centreRepository;
        this.programeRepository = programeRepository;
    }

    @Transactional(readOnly = true)
    public List<FiliereDTO> getAllFilieres() {
        return filiereRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FiliereDTO> getFiliereByCentre(Long centreId) {
        return filiereRepository.findByCentre_Id(centreId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FiliereDTO getFiliereById(Long id) {
        Filiere filiere = filiereRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filiere not found with id: " + id));
        return convertToDTO(filiere);
    }

    public FiliereDTO createFiliere(FiliereRequestDTO requestDTO) {

        Centre centre = centreRepository.findById(requestDTO.getCentreId())
                .orElseThrow(() -> new ResourceNotFoundException("Centre not found with id: " + requestDTO.getCentreId()));
        Program program = programeRepository.findById(requestDTO.getProgramId())
                .orElseThrow(() -> new ResourceNotFoundException("Program not found with id: " + requestDTO.getProgramId()));

        // Security Check: Prevent active linkages to inactive or soft-deleted parents
        if (!centre.getIsActive()) {
            throw new IllegalArgumentException("Cannot create a Filiere under an inactive Centre.");
        }
        // program.isDeleted() is visible because we modified the entity in the previous step
        if (program.isDeleted()) {
            throw new IllegalArgumentException("Cannot create a Filiere assigned to a deleted Program.");
        }

        Filiere filiere = new Filiere();
        filiere.setName(requestDTO.getName());
        filiere.setCentre(centre);
        filiere.setProgram(program);

        Filiere savedFiliere = filiereRepository.save(filiere);
        return convertToDTO(savedFiliere);
    }

    public FiliereDTO updateFiliere(Long id, FiliereRequestDTO requestDTO) {
        Filiere existing = filiereRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filiere not found with id: " + id));

        Centre centre = centreRepository.findById(requestDTO.getCentreId())
                .orElseThrow(() -> new ResourceNotFoundException("Centre not found with id: " + requestDTO.getCentreId()));
        Program program = programeRepository.findById(requestDTO.getProgramId())
                .orElseThrow(() -> new ResourceNotFoundException("Program not found with id: " + requestDTO.getProgramId()));

        // Security Check: Enforce structural relational validation rules
        if (!centre.getIsActive()) {
            throw new IllegalArgumentException("Cannot update a Filiere to link with an inactive Centre.");
        }
        if (program.isDeleted()) {
            throw new IllegalArgumentException("Cannot update a Filiere to link with a deleted Program.");
        }

        existing.setName(requestDTO.getName());
        existing.setCentre(centre);
        existing.setProgram(program);

        Filiere updatedFiliere = filiereRepository.save(existing);
        return convertToDTO(updatedFiliere);
    }

    public void deleteFiliere(Long id) {
        Filiere filiere = filiereRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filiere not found with id: " + id));

        // Passing the actual entity to delete ensures clean Hibernate intercept tracking
        filiereRepository.delete(filiere);
    }

    // Helper method handling clean relational nesting Entity -> DTO mapping
    private FiliereDTO convertToDTO(Filiere filiere) {
        FiliereDTO dto = new FiliereDTO();
        dto.setId(filiere.getId());
        dto.setName(filiere.getName());

        if (filiere.getCentre() != null) {
            CentreDTO cDto = new CentreDTO();
            cDto.setId(filiere.getCentre().getId());
            cDto.setName(filiere.getCentre().getName());
            cDto.setAddress(filiere.getCentre().getAddress());
            cDto.setPhone(filiere.getCentre().getPhone());
            cDto.setEmail(filiere.getCentre().getEmail());
            cDto.setIsActive(filiere.getCentre().getIsActive());
            dto.setCentre(cDto);
        }

        if (filiere.getProgram() != null) {
            ProgramDTO pDto = new ProgramDTO();
            pDto.setId(filiere.getProgram().getId());
            pDto.setName(filiere.getProgram().getName());
            pDto.setDurationYears(filiere.getProgram().getDurationYears());
            pDto.setMonthlyFee(filiere.getProgram().getMonthlyFee());
            dto.setProgram(pDto);
        }

        return dto;
    }
}