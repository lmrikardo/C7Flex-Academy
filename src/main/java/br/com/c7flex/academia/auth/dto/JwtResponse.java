package br.com.c7flex.academia.auth.dto;

import br.com.c7flex.academia.user.dto.UsuarioResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtResponse {

    private String token;

    private UsuarioResponse usuario;

}
