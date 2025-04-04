package com.filo.users.service;

import com.filo.users.dtos.UserDTO;
import com.filo.users.enums.UserRole;
import com.filo.users.model.UserModel;
import com.filo.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class UserService {

    @Value("${ADM_MAIL}")
    private String ADM_MAIL;
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel createUser(UserDTO userDTO) {
        UserRole userRole = this.handleUserRole(userDTO.email());
        String cryptPass = new BCryptPasswordEncoder().encode(userDTO.password());
        UserModel user = UserModel.fromDataCreateUser(userDTO, cryptPass, userRole);
        return this.userRepository.save(user);
    }

    public UserModel updateUser(UserDTO userDTO, String id) {
        UserRole userRole = this.handleUserRole(userDTO.email());
        UserModel user = this.findUserOrThrow(id);
        user.updateFromUserDTO(userDTO, userRole);
        return this.userRepository.save(user);
    }

    private UserRole handleUserRole(String email){
        UserRole userRole = Objects.equals(email, this.ADM_MAIL) ? UserRole.ADMIN : UserRole.USER;
        return userRole;
    }

    private UserModel findUserOrThrow(String id) {
        return this.userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("User not found"));
    }

}
