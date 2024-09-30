package com.auth.authtesteuser.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;


public record RegisterDTO(
    @NotEmpty(message = "Email não pode ser nulo ou vazia") @Email(message = "Email invalido") String email, 
    @NotEmpty(message = "Senha não pode ser nulo ou vazia") String password) {
    
}
