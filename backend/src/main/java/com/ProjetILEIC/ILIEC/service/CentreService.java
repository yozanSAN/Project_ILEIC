package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.dto.CentreDTO;
import com.ProjetILEIC.ILIEC.dto.CentreRequestDTO;
import com.ProjetILEIC.ILIEC.entity.Centre;
import com.ProjetILEIC.ILIEC.exception.ResourceNotFoundException;
import com.ProjetILEIC.ILIEC.repository.CentreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CentreService {

    private final CentreRepository centreRepository;

    public CentreService(CentreRepository centreRepository) {
        this.centreRepository = centreRepository;
    }

    @Transactional(readOnly = true)
    public List<CentreDTO> getAllCentres() {
        return centreRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CentreDTO getCentreById(Long id) {
        Centre centre = centreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Centre not found with id: " + id));
        return convertToDTO(centre);
    }

    public CentreDTO createCentre(CentreRequestDTO requestDTO) {
        Centre centre = new Centre();
        centre.setName(requestDTO.getName());
        centre.setAddress(requestDTO.getAddress());
        centre.setPhone(requestDTO.getPhone());
        centre.setEmail(requestDTO.getEmail());

        Centre savedCentre = centreRepository.save(centre);
        return convertToDTO(savedCentre);
    }

    public CentreDTO updateCentre(Long id, CentreRequestDTO requestDTO) {
        // Fetch the existing entity directly from the DB
        Centre existing = centreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Centre not found with id: " + id));

        existing.setName(requestDTO.getName());
        existing.setAddress(requestDTO.getAddress());
        existing.setPhone(requestDTO.getPhone());
        existing.setEmail(requestDTO.getEmail());

        Centre updatedCentre = centreRepository.save(existing);
        return convertToDTO(updatedCentre);
    }

    public void deleteCentre(Long id) {
        if (!centreRepository.existsById(id)) {
            throw new ResourceNotFoundException("Centre not found with id: " + id);
        }
        centreRepository.deleteById(id);
    }

    // Helper method to cleanly convert Entity -> Outgoing DTO
    private CentreDTO convertToDTO(Centre centre) {
        CentreDTO dto = new CentreDTO();
        dto.setId(centre.getId());
        dto.setName(centre.getName());
        dto.setAddress(centre.getAddress());
        dto.setPhone(centre.getPhone());
        dto.setEmail(centre.getEmail());
        return dto;
    }
}