package com.filo.users.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.filo.users.dtos.UserDTO;
import com.filo.users.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "TB_USERS")
@Getter
@Setter
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotBlank
    private String fullname;
    @NotBlank
    private String username;
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;
    @NotBlank
    @JsonIgnore
    private String password;
    private UserRole role;
    private String postId;

    public UserModel(String fullname, String username, String email, String password, UserRole userRole, String postId) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = userRole;
        this.postId = postId;
    }

    public void updateFromUserDTO(UserDTO userDTO, UserRole userRole){
        this.username = userDTO.username();
        this.fullname = userDTO.fullname();
        this.email = userDTO.email();
        this.password = userDTO.password();
        this.role = userRole;
    }

    public String getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public String getPostId() {
        return postId;
    }

    public UserModel() {
    }
    public static UserModel fromDataCreateUser(UserDTO userDTO, String cryptPass, UserRole userRole) {
        return new UserModel(
                userDTO.fullname(),
                userDTO.username(),
                userDTO.email(),
                cryptPass,
                userRole,
                null
        );
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", postId='" + postId + '\'' +
                '}';
    }
}
