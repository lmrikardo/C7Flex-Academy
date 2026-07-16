package br.com.c7flex.academia.auth.service.impl;

import br.com.c7flex.academia.auth.dto.*;
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
import jakarta.transaction.Transactional;
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

    @Override
    @Transactional
    public JwtResponse devLogin(DevLoginRequest request) {

        Usuario usuario = repository
                .findByEmail(request.getEmail())
                .orElseGet(() ->
                        criarUsuario("Usuário Desenvolvimento", request.getEmail(), Role.ROLE_ADMIN)
                );

        String token = jwtService.gerarToken(usuario);

        return JwtResponse.builder()
                .token(token)
                .usuario(mapper.toResponse(usuario))
                .build();

    }

    private Usuario criarUsuario(String nome, String email, Role role) {

        Usuario usuario = new Usuario();

        usuario.setNome(nome);
        usuario.setEmail(email);

        usuario.setAtivo(true);
        usuario.setStatus(StatusUsuario.ATIVO);
        usuario.setRole(role);

        return repository.save(usuario);

    }

    private Usuario criarUsuario(GoogleUser googleUser) {

        Usuario usuario = criarUsuario(googleUser.nome(), googleUser.email(), Role.ROLE_ALUNO);

        usuario.setGoogleId(googleUser.googleId());
        usuario.setFoto(googleUser.foto());

        return repository.save(usuario);

    }

    @Override
    public MeResponse me() {

        Usuario usuario = getUsuarioLogado();

        return new MeResponse(

                mapper.toResponse(usuario)

        );

    }

    public Usuario getUsuarioLogado() {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        if (authentication == null) {

            throw new ApiException(ErrorCode.INTERNAL_ERROR, "Usuário não autenticado.");

        }

        return (Usuario) authentication.getPrincipal();

    }
}