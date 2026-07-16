package br.com.c7flex.academia.auth.dto;

import br.com.c7flex.academia.user.entity.Role;
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