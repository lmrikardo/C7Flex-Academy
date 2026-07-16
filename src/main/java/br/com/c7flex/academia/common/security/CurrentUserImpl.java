package br.com.c7flex.academia.common.security;

import br.com.c7flex.academia.auth.service.AuthService;
import br.com.c7flex.academia.user.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentUserImpl implements CurrentUser {

    private final AuthService authService;

    @Override
    public Long getId() {
        return getUsuario().getId();
    }

    @Override
    public String getEmail() {
        return getUsuario().getEmail();
    }

    @Override
    public Usuario getUsuario() {
        return authService.getUsuarioLogado();
    }
}