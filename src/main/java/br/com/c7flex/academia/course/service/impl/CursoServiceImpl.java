package br.com.c7flex.academia.course.service.impl;

import br.com.c7flex.academia.common.exception.ApiException;
import br.com.c7flex.academia.common.exception.ErrorCode;
import br.com.c7flex.academia.common.response.PageResponse;
import br.com.c7flex.academia.course.dto.CursoRequest;
import br.com.c7flex.academia.course.dto.CursoResponse;
import br.com.c7flex.academia.course.entity.Curso;
import br.com.c7flex.academia.course.mapper.CursoMapper;
import br.com.c7flex.academia.course.repository.CursoRepository;
import br.com.c7flex.academia.course.service.CursoService;
import br.com.c7flex.academia.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository repository;
    private final CursoMapper mapper;

    @Override
    public CursoResponse salvar(CursoRequest dto) {

        Curso curso = mapper.toEntity(dto);

        return mapper.toResponse(
                repository.save(curso)
        );
    }

    @Override
    public PageResponse<CursoResponse> listar(Pageable pageable){

        Page<Curso> page = repository.findAll(pageable);

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

    @Override
    public CursoResponse buscar(Long id) {

        Curso curso = repository.findById(id)
                .orElseThrow(() ->
                        new ApiException(
                                ErrorCode.NOT_FOUND,
                                "Curso não encontrado"
                        ));

        return mapper.toResponse(curso);

    }

    @Override
    public CursoResponse atualizar(Long id, CursoRequest dto) {

        Curso curso = repository.findById(id)
                .orElseThrow(() ->
                        new ApiException(
                                ErrorCode.NOT_FOUND,
                                "Curso não encontrado"
                        ));

        curso.setNome(dto.nome());
        curso.setDescricao(dto.descricao());
        curso.setImagem(dto.imagem());
        curso.setAtivo(dto.ativo());

        return mapper.toResponse(
                repository.save(curso)
        );
    }

    @Override
    public void excluir(Long id) {

        Curso curso = repository.findById(id)
                .orElseThrow(() -> new ApiException(
                        ErrorCode.NOT_FOUND,
                        "Curso não encontrado."
                ));

        repository.delete(curso);

    }
}