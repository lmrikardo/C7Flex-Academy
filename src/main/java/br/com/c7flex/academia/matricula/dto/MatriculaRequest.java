package br.com.c7flex.academia.matricula.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatriculaRequest {

    @NotNull
    private Long usuarioId;

    @NotNull
    private Long cursoId;

}
