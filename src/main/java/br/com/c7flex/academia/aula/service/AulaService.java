package br.com.c7flex.academia.aula.service;

import br.com.c7flex.academia.aula.dto.AulaRequest;
import br.com.c7flex.academia.aula.dto.AulaResponse;
import br.com.c7flex.academia.common.response.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AulaService {

    AulaResponse salvar(AulaRequest dto);

    AulaResponse buscar(Long id);

    PageResponse<AulaResponse> listar(Long moduloId, Pageable pageable);

    AulaResponse atualizar(Long id, AulaRequest dto);

    void excluir(Long id);

}