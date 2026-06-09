package com.ProjetILEIC.ILIEC.repository;

import com.ProjetILEIC.ILIEC.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgrameRepository extends JpaRepository<Program, Long> {

    Optional<Program> findByName(String name);
}
