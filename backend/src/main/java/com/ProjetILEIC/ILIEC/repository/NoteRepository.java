package com.ProjetILEIC.ILIEC.repository;

import com.ProjetILEIC.ILIEC.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByStagiaire_Id(Long stagiaireId);

    List<Note> findByControle_Id(Long controleId);

    Optional<Note> findByStagiaire_IdAndControle_Id(Long stagiaireId, Long controleId);

    List<Note> findByStagiaire_IdAndControle_Cours_Id(Long stagiaireId, Long coursId);
}
