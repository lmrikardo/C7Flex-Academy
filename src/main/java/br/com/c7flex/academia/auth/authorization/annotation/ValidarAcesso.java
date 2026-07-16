package br.com.c7flex.academia.auth.authorization.annotation;

import br.com.c7flex.academia.auth.authorization.TipoRecurso;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidarAcesso {

    TipoRecurso tipo();

    String parametro();

}