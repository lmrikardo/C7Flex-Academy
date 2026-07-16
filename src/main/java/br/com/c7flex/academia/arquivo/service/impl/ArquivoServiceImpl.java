package br.com.c7flex.academia.arquivo.service.impl;

import br.com.c7flex.academia.arquivo.dto.ArquivoResponse;
import br.com.c7flex.academia.arquivo.entity.Arquivo;
import br.com.c7flex.academia.arquivo.mapper.ArquivoMapper;
import br.com.c7flex.academia.arquivo.repository.ArquivoRepository;
import br.com.c7flex.academia.arquivo.service.ArquivoService;
import br.com.c7flex.academia.aula.entity.Aula;
import br.com.c7flex.academia.aula.repository.AulaRepository;
import br.com.c7flex.academia.auth.authorization.TipoRecurso;
import br.com.c7flex.academia.auth.authorization.annotation.ValidarAcesso;
import br.com.c7flex.academia.common.exception.ApiException;
import br.com.c7flex.academia.common.exception.ErrorCode;
import br.com.c7flex.academia.common.response.PageResponse;
import br.com.c7flex.academia.storage.dto.UploadResponse;
import br.com.c7flex.academia.storage.service.StorageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ArquivoServiceImpl implements ArquivoService {

    private final ArquivoRepository repository;
    private final AulaRepository aulaRepository;
    private final StorageService storageService;
    private final ArquivoMapper mapper;

    @Override
    public ArquivoResponse upload(Long aulaId, MultipartFile multipartFile) {

        Aula aula = aulaRepository.findById(aulaId)
                .orElseThrow(() -> new ApiException(
                        ErrorCode.NOT_FOUND,
                        "Aula não encontrada."
                ));

        UploadResponse upload = storageService.upload(multipartFile);

        Arquivo arquivo = new Arquivo();

        arquivo.setNome(upload.nome());
        arquivo.setNomeOriginal(upload.nomeOriginal());
        arquivo.setTipo(upload.tipo());
        arquivo.setExtensao(upload.extensao());
        arquivo.setTamanho(upload.tamanho());
        arquivo.setCaminho(upload.caminho());

        arquivo.setOrdem(1);
        arquivo.setAtivo(true);
        arquivo.setAula(aula);

        repository.save(arquivo);

        return mapper.toResponse(arquivo);

    }

    @ValidarAcesso(tipo = TipoRecurso.AULA, parametro = "aulaId")
    @Override
    public PageResponse<ArquivoResponse> listar(
            Long aulaId,
            Pageable pageable) {

        Page<Arquivo> page =
                repository.findByAulaIdAndAtivoTrue(
                        aulaId,
                        pageable
                );

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
    @Transactional
    public void excluir(Long id) {

        Arquivo arquivo = repository.findById(id)
                .orElseThrow(() -> new ApiException(
                        ErrorCode.NOT_FOUND,
                        "Arquivo não encontrado."
                ));

        storageService.delete(arquivo.getCaminho());

        repository.delete(arquivo);

    }
}