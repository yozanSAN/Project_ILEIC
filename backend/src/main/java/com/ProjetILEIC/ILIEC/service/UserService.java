package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.dto.UserDTO;
import com.ProjetILEIC.ILIEC.entity.User;
import com.ProjetILEIC.ILIEC.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    //CONSTRUCTOR FOR THE SERVICE CLASS WITH userRepository AS ARGUMENT
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //GET ALL USERS
    public List<User> getAllUsers(){
        return  userRepository.findAll();
    }
    //POST A NEW USER
    public User createNewUser(User user){
        return  userRepository.save(user);
    }
    //DTA USER
    public UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole(),
                user.getIsActive(),
                user.getCreatedAt()
        );
    }
}