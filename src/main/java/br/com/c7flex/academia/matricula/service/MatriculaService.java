package br.com.c7flex.academia.matricula.service;

import br.com.c7flex.academia.matricula.entity.Matricula;

public interface MatriculaService {

    void validarAcesso(Long cursoId);

    boolean possuiAcesso(Long cursoId);

    Matricula matricular(Long usuarioId, Long cursoId);

    void cancelar(Long matriculaId);

}