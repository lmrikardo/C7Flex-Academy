package br.com.c7flex.academia.user.service.impl;

import br.com.c7flex.academia.common.exception.ApiException;
import br.com.c7flex.academia.common.exception.ErrorCode;
import br.com.c7flex.academia.common.response.PageResponse;
import br.com.c7flex.academia.user.dto.UsuarioResponse;
import br.com.c7flex.academia.user.entity.Usuario;
import br.com.c7flex.academia.user.mapper.UsuarioMapper;
import br.com.c7flex.academia.user.repository.UsuarioRepository;
import br.com.c7flex.academia.user.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    private final UsuarioMapper mapper;

    @Override
    public Usuario salvar(Usuario usuario) {

        return repository.save(usuario);

    }

    @Override
    public Usuario buscarPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ApiException(
                                ErrorCode.NOT_FOUND,
                                "Usuário não encontrado."
                        ));

    }

    @Override
    public Usuario buscarPorEmail(String email) {

        return repository.findByEmail(email)
                .orElseThrow(() ->
                        new ApiException(
                                ErrorCode.NOT_FOUND,
                                "Usuário não encontrado."
                        ));

    }

    @Override
    public PageResponse<UsuarioResponse> listar(Pageable pageable) {

        Page<Usuario> page = repository.findAll(pageable);

        return new PageResponse<>(

                page.getContent()
                        .stream()
                        .map(mapper::toResponse)
                        .toList(),

                page.getTotalElements(),

                page.getTotalPages(),

                page.getNumber(),

                page.getSize(),

                page.isFirst(),

                page.isLast()

        );

    }

}