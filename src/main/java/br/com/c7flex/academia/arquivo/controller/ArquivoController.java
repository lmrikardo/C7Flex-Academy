package br.com.c7flex.academia.arquivo.controller;

import br.com.c7flex.academia.arquivo.dto.ArquivoResponse;
import br.com.c7flex.academia.arquivo.service.ArquivoService;
import br.com.c7flex.academia.common.response.ApiResponse;
import br.com.c7flex.academia.common.response.PageResponse;
import br.com.c7flex.academia.common.response.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/arquivos")
@RequiredArgsConstructor
public class ArquivoController {

    private final ArquivoService service;

    @PostMapping(
            value = "aula/{aulaId}/arquivos",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ApiResponse<ArquivoResponse>> upload(

            @PathVariable Long aulaId,

            @RequestParam("arquivo")
            MultipartFile arquivo){

        return ResponseEntity.ok(

                ResponseFactory.success(

                        "Arquivo enviado com sucesso.",

                        service.upload(aulaId, arquivo)

                )

        );

    }

    @GetMapping("/aula/{aulaId}")
    public ResponseEntity<ApiResponse<PageResponse<ArquivoResponse>>> listar(

            @PathVariable Long aulaId,
            @PageableDefault(sort = "ordem") Pageable pageable){

        return ResponseEntity.ok(

                ResponseFactory.success(

                        "Arquivos carregados.",

                        service.listar(aulaId, pageable)

                )

        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> excluir(
            @PathVariable Long id){

        service.excluir(id);

        return ResponseEntity.ok(
                ResponseFactory.success(
                        "Arquivo excluído com sucesso."
                )
        );

    }

}