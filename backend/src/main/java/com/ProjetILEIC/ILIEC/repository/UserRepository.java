package com.ProjetILEIC.ILIEC.repository;

import com.ProjetILEIC.ILIEC.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
