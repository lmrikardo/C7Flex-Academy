package br.com.c7flex.academia.auth.service;

import br.com.c7flex.academia.auth.dto.*;
import br.com.c7flex.academia.user.entity.Usuario;

public interface AuthService {

    LoginResponse loginGoogle(GoogleLoginRequest request);

    JwtResponse devLogin(DevLoginRequest request);

    MeResponse me();

    Usuario getUsuarioLogado();

}