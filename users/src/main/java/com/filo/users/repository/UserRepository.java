package com.filo.users.repository;

import com.filo.users.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, String> {
    Optional<UserModel> findByUsername(String username);

    @Query(value = "SELECT u FROM UserModel u WHERE u.email = :email")
    UserDetails findByEmailToReturnUserDetails(String email);
}
