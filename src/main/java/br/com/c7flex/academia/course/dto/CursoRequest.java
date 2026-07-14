package br.com.c7flex.academia.course.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoRequest(

        @NotBlank
        String nome,

        String descricao,

        String imagem,

        @NotNull
        Boolean ativo

) {
}