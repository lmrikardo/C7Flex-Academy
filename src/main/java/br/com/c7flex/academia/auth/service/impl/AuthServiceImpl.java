package br.com.c7flex.academia.auth.service.impl;

import br.com.c7flex.academia.auth.dto.GoogleLoginRequest;
import br.com.c7flex.academia.auth.dto.GoogleUser;
import br.com.c7flex.academia.auth.dto.LoginResponse;
import br.com.c7flex.academia.auth.dto.MeResponse;
import br.com.c7flex.academia.auth.service.AuthService;
import br.com.c7flex.academia.auth.service.GoogleTokenVerifier;
import br.com.c7flex.academia.auth.service.JwtService;
import br.com.c7flex.academia.common.exception.ApiException;
import br.com.c7flex.academia.common.exception.ErrorCode;
import br.com.c7flex.academia.user.entity.Role;
import br.com.c7flex.academia.user.entity.StatusUsuario;
import br.com.c7flex.academia.user.entity.Usuario;
import br.com.c7flex.academia.user.mapper.UsuarioMapper;
import br.com.c7flex.academia.user.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final GoogleTokenVerifier verifier;

    private final UsuarioRepository repository;

    private final UsuarioMapper mapper;

    private final JwtService jwtService;

    @Override
    public LoginResponse loginGoogle(GoogleLoginRequest request) {

        GoogleUser googleUser = verifier.verify(request.idToken());

        Usuario usuario = repository
                .findByEmail(googleUser.email())
                .orElseGet(() -> criarUsuario(googleUser));

        if (usuario.getStatus() != StatusUsuario.ATIVO) {
            throw new ApiException(ErrorCode.UNAUTHORIZED, "Usuário sem permissão para acessar a plataforma");
        }

        usuario.setUltimoAcesso(LocalDateTime.now());

        repository.save(usuario);

        String jwt = jwtService.gerarToken(usuario);

        return new LoginResponse(

                jwt,

                mapper.toResponse(usuario)

        );

    }

    private Usuario criarUsuario(GoogleUser googleUser) {

        Usuario usuario = new Usuario();

        usuario.setGoogleId(googleUser.googleId());

        usuario.setNome(googleUser.nome());

        usuario.setEmail(googleUser.email());

        usuario.setFoto(googleUser.foto());

        usuario.setRole(Role.ROLE_ALUNO);

        usuario.setAtivo(true);

        return repository.save(usuario);

    }

    @Override
    public MeResponse me() {

        Usuario usuario = getUsuarioLogado();

        return new MeResponse(

                mapper.toResponse(usuario)

        );

    }

    private Usuario getUsuarioLogado() {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        if (authentication == null) {

            throw new ApiException(ErrorCode.INTERNAL_ERROR, "Usuário não autenticado.");

        }

        return (Usuario) authentication.getPrincipal();

    }
}