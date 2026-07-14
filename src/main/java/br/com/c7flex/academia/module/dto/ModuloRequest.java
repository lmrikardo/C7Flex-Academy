package br.com.c7flex.academia.module.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ModuloRequest(

        @NotBlank
        String nome,

        @NotNull
        Integer ordem,

        @NotNull
        Long cursoId

) {
}