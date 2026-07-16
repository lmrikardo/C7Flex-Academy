package br.com.c7flex.academia.matricula.service;

import br.com.c7flex.academia.course.entity.Curso;
import br.com.c7flex.academia.matricula.entity.Matricula;
import br.com.c7flex.academia.user.entity.Usuario;

public interface MatriculaService {

    void validarAcesso(Long cursoId);

    boolean possuiAcesso(Long cursoId);

    Matricula matricular(Long usuarioId, Long cursoId);

    void cancelar(Long matriculaId);

}