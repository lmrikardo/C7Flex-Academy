package br.com.c7flex.academia.module.service;

import br.com.c7flex.academia.common.response.PageResponse;
import br.com.c7flex.academia.module.dto.ModuloRequest;
import br.com.c7flex.academia.module.dto.ModuloResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ModuloService {

    ModuloResponse salvar(ModuloRequest dto);

    PageResponse<ModuloResponse> listar(Long cursoId, Pageable pageable);

    void excluir(Long id);

}