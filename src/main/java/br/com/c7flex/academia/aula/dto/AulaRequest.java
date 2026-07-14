package br.com.c7flex.academia.aula.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AulaRequest(

        @NotBlank
        String titulo,

        String descricao,

        @NotBlank
        String youtubeUrl,

        Integer duracao,

        Integer ordem,

        @NotNull
        Long moduloId

){}