package br.com.c7flex.academia.aula.service.impl;

import br.com.c7flex.academia.aula.dto.AulaRequest;
import br.com.c7flex.academia.aula.dto.AulaResponse;
import br.com.c7flex.academia.aula.entity.Aula;
import br.com.c7flex.academia.aula.mapper.AulaMapper;
import br.com.c7flex.academia.aula.repository.AulaRepository;
import br.com.c7flex.academia.aula.service.AulaService;
import br.com.c7flex.academia.auth.authorization.TipoRecurso;
import br.com.c7flex.academia.auth.authorization.annotation.ValidarAcesso;
import br.com.c7flex.academia.common.exception.ApiException;
import br.com.c7flex.academia.common.exception.ErrorCode;
import br.com.c7flex.academia.common.response.PageResponse;
import br.com.c7flex.academia.common.utils.YoutubeUtils;
import br.com.c7flex.academia.module.entity.Modulo;
import br.com.c7flex.academia.module.repository.ModuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AulaServiceImpl implements AulaService {

    private final AulaRepository repository;
    private final ModuloRepository moduloRepository;
    private final AulaMapper mapper;

    @Override
    public AulaResponse salvar(AulaRequest dto) {

        Modulo modulo = moduloRepository.findById(dto.moduloId())
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "Módulo não encontrado."));

        Aula aula = new Aula();

        aula.setTitulo(dto.titulo());
        aula.setDescricao(dto.descricao());
        aula.setYoutubeUrl(dto.youtubeUrl());

        String youtubeId = YoutubeUtils.extrairVideoId(dto.youtubeUrl());

        aula.setYoutubeId(youtubeId);
        aula.setThumbnail(YoutubeUtils.thumbnail(youtubeId));

        aula.setDuracao(dto.duracao());
        aula.setOrdem(dto.ordem());
        aula.setPublicado(true);
        aula.setModulo(modulo);

        return mapper.toResponse(repository.save(aula));
    }

    @ValidarAcesso(tipo = TipoRecurso.AULA, parametro = "id")
    @Override
    public AulaResponse buscar(Long id) {

        Aula aula = repository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "Aula não encontrada."));

        return mapper.toResponse(aula);
    }

    @ValidarAcesso(tipo = TipoRecurso.MODULO, parametro = "moduloId")
    @Override
    public PageResponse<AulaResponse> listar(Long moduloId, Pageable pageable) {

        Page<Aula> page = repository.findByModuloId(moduloId, pageable);

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
    public AulaResponse atualizar(Long id, AulaRequest dto) {

        Aula aula = repository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "Aula não encontrada."));

        Modulo modulo = moduloRepository.findById(dto.moduloId())
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "Módulo não encontrado."));

        aula.setTitulo(dto.titulo());
        aula.setDescricao(dto.descricao());
        aula.setYoutubeUrl(dto.youtubeUrl());

        String youtubeId = YoutubeUtils.extrairVideoId(dto.youtubeUrl());

        aula.setYoutubeId(youtubeId);
        aula.setThumbnail(YoutubeUtils.thumbnail(youtubeId));

        aula.setDuracao(dto.duracao());
        aula.setOrdem(dto.ordem());
        aula.setModulo(modulo);

        return mapper.toResponse(repository.save(aula));
    }

    @Override
    public void excluir(Long id) {

        Aula aula = repository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "Aula não encontrada."));

        repository.delete(aula);
    }
}