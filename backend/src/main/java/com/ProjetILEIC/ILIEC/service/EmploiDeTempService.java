package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.dto.EmploiDeTempDTO;
import com.ProjetILEIC.ILIEC.dto.EmploiDeTempRequestDTO;
import com.ProjetILEIC.ILIEC.entity.*;
import com.ProjetILEIC.ILIEC.exception.ResourceNotFoundException;
import com.ProjetILEIC.ILIEC.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmploiDeTempService {

    private final EmploiDeTempRepository emploiDeTempRepository;
    private final CentreRepository centreRepository;
    private final FiliereRepository filiereRepository;
    private final CoursRepository coursRepository;
    private final FormateurRepository formateurRepository;

    public EmploiDeTempService(EmploiDeTempRepository emploiDeTempRepository,
                               CentreRepository centreRepository,
                               FiliereRepository filiereRepository,
                               CoursRepository coursRepository,
                               FormateurRepository formateurRepository) {
        this.emploiDeTempRepository = emploiDeTempRepository;
        this.centreRepository = centreRepository;
        this.filiereRepository = filiereRepository;
        this.coursRepository = coursRepository;
        this.formateurRepository = formateurRepository;
    }

    // --- READ ---

    @Transactional(readOnly = true)
    public List<EmploiDeTempDTO> getTimetableByFiliere(Long filiereId) {
        return emploiDeTempRepository.findByFiliere_Id(filiereId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EmploiDeTempDTO> getTimetableByCentre(Long centreId) {
        return emploiDeTempRepository.findByCentre_Id(centreId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EmploiDeTempDTO> getTimetableByFormateur(Long formateurId) {
        return emploiDeTempRepository.findByFormateur_Id(formateurId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    // --- CREATE ---
    public EmploiDeTempDTO createScheduleSlot(EmploiDeTempRequestDTO requestDTO) {
        Centre centre = centreRepository.findById(requestDTO.getCentreId())
                .orElseThrow(() -> new ResourceNotFoundException("Centre not found: " + requestDTO.getCentreId()));

        Filiere filiere = filiereRepository.findById(requestDTO.getFiliereId())
                .orElseThrow(() -> new ResourceNotFoundException("Filiere not found: " + requestDTO.getFiliereId()));

        Cours cours = coursRepository.findById(requestDTO.getCoursId())
                .orElseThrow(() -> new ResourceNotFoundException("Cours not found: " + requestDTO.getCoursId()));

        Formateur formateur = formateurRepository.findById(requestDTO.getFormateurId())
                .orElseThrow(() -> new ResourceNotFoundException("Formateur not found: " + requestDTO.getFormateurId()));

        // Enforce that the scheduled course matches the selected filiere
        if (!cours.getFiliere().getId().equals(filiere.getId())) {
            throw new IllegalArgumentException("The selected course does not belong to this filiere");
        }

        EmploiDeTemp slot = new EmploiDeTemp();
        slot.setCentre(centre);
        slot.setFiliere(filiere);
        slot.setCours(cours);
        slot.setFormateur(formateur);
        slot.setDayOfWeek(requestDTO.getDayOfWeek().toUpperCase());
        slot.setStartTime(requestDTO.getStartTime());
        slot.setEndTime(requestDTO.getEndTime());
        slot.setRoom(requestDTO.getRoom());

        return toDTO(emploiDeTempRepository.save(slot));
    }

    // ---UPDATE ---
    // --- UPDATE ---

    public EmploiDeTempDTO updateScheduleSlot(Long id, EmploiDeTempRequestDTO requestDTO) {
        EmploiDeTemp slot = emploiDeTempRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule slot not found with id: " + id));

        // Fetch updated dependencies
        Centre centre = centreRepository.findById(requestDTO.getCentreId())
                .orElseThrow(() -> new ResourceNotFoundException("Centre not found: " + requestDTO.getCentreId()));

        Filiere filiere = filiereRepository.findById(requestDTO.getFiliereId())
                .orElseThrow(() -> new ResourceNotFoundException("Filiere not found: " + requestDTO.getFiliereId()));

        Cours cours = coursRepository.findById(requestDTO.getCoursId())
                .orElseThrow(() -> new ResourceNotFoundException("Cours not found: " + requestDTO.getCoursId()));

        Formateur formateur = formateurRepository.findById(requestDTO.getFormateurId())
                .orElseThrow(() -> new ResourceNotFoundException("Formateur not found: " + requestDTO.getFormateurId()));

        // OFPPT rule check: Enforce that the updated course matches the updated major
        if (!cours.getFiliere().getId().equals(filiere.getId())) {
            throw new IllegalArgumentException("The selected course does not belong to this filiere");
        }

        // Apply new values
        slot.setCentre(centre);
        slot.setFiliere(filiere);
        slot.setCours(cours);
        slot.setFormateur(formateur);
        slot.setDayOfWeek(requestDTO.getDayOfWeek().toUpperCase());
        slot.setStartTime(requestDTO.getStartTime());
        slot.setEndTime(requestDTO.getEndTime());
        slot.setRoom(requestDTO.getRoom());

        return toDTO(emploiDeTempRepository.save(slot));
    }

    // --- DELETE ---
    public void deleteScheduleSlot(Long id) {
        if (!emploiDeTempRepository.existsById(id)) {
            throw new ResourceNotFoundException("Schedule slot not found with id: " + id);
        }
        emploiDeTempRepository.deleteById(id);
    }

    // --- DTO CONVERSION ---

    public EmploiDeTempDTO toDTO(EmploiDeTemp e) {
        return new EmploiDeTempDTO(
                e.getId(),
                e.getCentre().getId(),
                e.getCentre().getName(),
                e.getFiliere().getId(),
                e.getFiliere().getName(),
                e.getCours().getId(),
                e.getCours().getName(),
                e.getFormateur().getId(),
                e.getFormateur().getUser().getFullName(), // Assuming Formateur maps back to base User
                e.getDayOfWeek(),
                e.getStartTime(),
                e.getEndTime(),
                e.getRoom()
        );
    }
}