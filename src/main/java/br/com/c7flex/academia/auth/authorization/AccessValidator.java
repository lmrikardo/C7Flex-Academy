package br.com.c7flex.academia.auth.authorization;

public interface AccessValidator {

    void validar(TipoRecurso tipoRecurso, Long id);

}