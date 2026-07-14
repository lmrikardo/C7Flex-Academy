package br.com.c7flex.academia.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record GoogleLoginRequest(

        @NotBlank
        String idToken

) {
}