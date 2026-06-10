package com.ProjetILEIC.ILIEC.repository;

import com.ProjetILEIC.ILIEC.entity.Secretary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SecretaryRepository extends JpaRepository<Secretary, Long> {

    Optional<Secretary> findByUser_Id(Long userId);

    List<Secretary> findByCentre_Id(Long centreId);
}
