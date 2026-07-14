package br.com.c7flex.academia.arquivo.service;

import br.com.c7flex.academia.arquivo.dto.ArquivoResponse;
import br.com.c7flex.academia.common.response.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ArquivoService {

    ArquivoResponse upload(
            Long aulaId,
            MultipartFile arquivo
    );

    PageResponse<ArquivoResponse> listar(
            Long aulaId,
            Pageable pageable
    );

    void excluir(Long id);

}