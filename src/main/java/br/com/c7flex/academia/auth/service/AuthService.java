package br.com.c7flex.academia.auth.service;

import br.com.c7flex.academia.auth.dto.GoogleLoginRequest;
import br.com.c7flex.academia.auth.dto.LoginResponse;
import br.com.c7flex.academia.auth.dto.MeResponse;
import br.com.c7flex.academia.auth.security.AuthenticatedUser;

public interface AuthService {

    LoginResponse loginGoogle(GoogleLoginRequest request);

    MeResponse me();

    //AuthenticatedUser getUsuarioLogado();

}