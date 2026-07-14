package br.com.c7flex.academia.course.service;

import br.com.c7flex.academia.common.response.PageResponse;
import br.com.c7flex.academia.course.dto.CursoRequest;
import br.com.c7flex.academia.course.dto.CursoResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CursoService {

    CursoResponse salvar(CursoRequest dto);

    PageResponse<CursoResponse> listar(Pageable pageable);

    CursoResponse buscar(Long id);

    CursoResponse atualizar(Long id, CursoRequest dto);

    void excluir(Long id);

}