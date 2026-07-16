package br.com.c7flex.academia.common.security;

import br.com.c7flex.academia.user.entity.Usuario;

public interface CurrentUser {

    Long getId();

    String getEmail();

    Usuario getUsuario();

}