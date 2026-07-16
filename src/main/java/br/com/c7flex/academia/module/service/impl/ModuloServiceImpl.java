package br.com.c7flex.academia.module.service.impl;

import br.com.c7flex.academia.auth.authorization.TipoRecurso;
import br.com.c7flex.academia.auth.authorization.annotation.ValidarAcesso;
import br.com.c7flex.academia.common.exception.ApiException;
import br.com.c7flex.academia.common.exception.ErrorCode;
import br.com.c7flex.academia.common.response.PageResponse;
import br.com.c7flex.academia.course.entity.Curso;
import br.com.c7flex.academia.course.repository.CursoRepository;
import br.com.c7flex.academia.module.dto.ModuloRequest;
import br.com.c7flex.academia.module.dto.ModuloResponse;
import br.com.c7flex.academia.module.entity.Modulo;
import br.com.c7flex.academia.module.mapper.ModuloMapper;
import br.com.c7flex.academia.module.repository.ModuloRepository;
import br.com.c7flex.academia.module.service.ModuloService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModuloServiceImpl implements ModuloService {

    private final ModuloRepository repository;

    private final CursoRepository cursoRepository;

    private final ModuloMapper mapper;

    @Override
    public ModuloResponse salvar(ModuloRequest dto){

        Curso curso = cursoRepository.findById(dto.cursoId())

                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "Curso não encontrado"));

        Modulo modulo = new Modulo();

        modulo.setNome(dto.nome());

        modulo.setOrdem(dto.ordem());

        modulo.setCurso(curso);

        return mapper.toResponse(repository.save(modulo));

    }

    @ValidarAcesso(tipo = TipoRecurso.CURSO, parametro = "cursoId")
    @Override
    public PageResponse<ModuloResponse> listar(Long cursoId, Pageable pageable) {

        Page<Modulo> page = repository.findByCursoId(cursoId, pageable);

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
    public void excluir(Long id){

        repository.deleteById(id);

    }

}