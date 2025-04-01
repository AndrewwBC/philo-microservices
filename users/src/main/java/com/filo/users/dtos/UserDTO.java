package com.filo.users.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserDTO(
        @NotBlank(message = "Nome completo deve ser preenchido")
        String username,
        @NotBlank(message = "Nome de usuário deve ser preenchido")
        String fullname,
        @NotBlank(message = "Email deve ser preenchido")
        @Email(regexp = "^[^@]+@[^@]+\\.[^@]+$" ,message = "Email inválido.")
        String email,
        @NotBlank
        @Size(min = 8, max = 32)
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$", message = "Senha não está no formato esperado.")
        String password
)
{}
