package com.ProjetILEIC.ILIEC.service;

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
    public List<Filiere> getAllFilieres() {
        return filiereRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Filiere> getByCentre(Long centreId) {
        return filiereRepository.findByCentre_Id(centreId);
    }

    @Transactional(readOnly = true)
    public Filiere getFiliereById(Long id) {
        return filiereRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filiere not found with id: " + id));
    }

    public Filiere createFiliere(Filiere filiere, Long centreId, Long programId) {
        Centre centre = centreRepository.findById(centreId)
                .orElseThrow(() -> new ResourceNotFoundException("Centre not found with id: " + centreId));
        Program program = programeRepository.findById(programId)
                .orElseThrow(() -> new ResourceNotFoundException("Program not found with id: " + programId));

        filiere.setCentre(centre);
        filiere.setProgram(program);
        return filiereRepository.save(filiere);
    }

    public void deleteFiliere(Long id) {
        if (!filiereRepository.existsById(id)) {
            throw new ResourceNotFoundException("Filiere not found with id: " + id);
        }
        filiereRepository.deleteById(id);
    }
}
