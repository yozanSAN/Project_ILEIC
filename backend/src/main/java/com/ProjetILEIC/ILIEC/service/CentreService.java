package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.entity.Centre;
import com.ProjetILEIC.ILIEC.exception.ResourceNotFoundException;
import com.ProjetILEIC.ILIEC.repository.CentreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CentreService {

    private final CentreRepository centreRepository;

    public CentreService(CentreRepository centreRepository) {
        this.centreRepository = centreRepository;
    }

    @Transactional(readOnly = true)
    public List<Centre> getAllCentres() {
        return centreRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Centre getCentreById(Long id) {
        return centreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Centre not found with id: " + id));
    }

    public Centre createCentre(Centre centre) {
        return centreRepository.save(centre);
    }

    public Centre updateCentre(Long id, Centre updated) {
        Centre existing = getCentreById(id);
        existing.setName(updated.getName());
        existing.setAddress(updated.getAddress());
        existing.setPhone(updated.getPhone());
        existing.setEmail(updated.getEmail());
        return centreRepository.save(existing);
    }

    public void deleteCentre(Long id) {
        if (!centreRepository.existsById(id)) {
            throw new ResourceNotFoundException("Centre not found with id: " + id);
        }
        centreRepository.deleteById(id);
    }
}
