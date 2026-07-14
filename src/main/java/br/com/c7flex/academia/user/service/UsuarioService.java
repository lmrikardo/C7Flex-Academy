package br.com.c7flex.academia.user.service;

import br.com.c7flex.academia.common.response.PageResponse;
import br.com.c7flex.academia.user.dto.UsuarioResponse;
import br.com.c7flex.academia.user.entity.Usuario;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {

    Usuario buscarPorId(Long id);

    Usuario buscarPorEmail(String email);

    Usuario salvar(Usuario usuario);

    PageResponse<UsuarioResponse> listar(Pageable pageable);

}