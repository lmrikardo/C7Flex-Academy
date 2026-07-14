package br.com.c7flex.academia.auth.service;

import br.com.c7flex.academia.user.entity.Usuario;

public interface JwtService {

    String gerarToken(Usuario usuario);

    String getEmail(String token);

    boolean validar(String token);

    boolean expirado(String token);

}