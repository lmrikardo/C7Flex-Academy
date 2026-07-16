package br.com.c7flex.academia.matricula.dto;

import br.com.c7flex.academia.matricula.entity.StatusMatricula;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MatriculaResponse {

    private Long id;

    private Long usuarioId;

    private String usuario;

    private Long cursoId;

    private String curso;

    private StatusMatricula status;

    private LocalDateTime dataMatricula;

    private LocalDateTime dataExpiracao;

}