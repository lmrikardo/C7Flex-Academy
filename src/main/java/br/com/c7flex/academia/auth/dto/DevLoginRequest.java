package br.com.c7flex.academia.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DevLoginRequest {

    @NotBlank
    @Email
    private String email;

}