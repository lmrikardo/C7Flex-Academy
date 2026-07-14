package br.com.c7flex.academia.aula.controller;

import br.com.c7flex.academia.aula.dto.AulaRequest;
import br.com.c7flex.academia.aula.dto.AulaResponse;
import br.com.c7flex.academia.aula.service.AulaService;
import br.com.c7flex.academia.common.response.ApiResponse;
import br.com.c7flex.academia.common.response.PageResponse;
import br.com.c7flex.academia.common.response.ResponseFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/aulas")
@RequiredArgsConstructor
public class AulaController {

    private final AulaService service;

    @PostMapping
    public ResponseEntity<ApiResponse<AulaResponse>> salvar(
            @RequestBody @Valid AulaRequest request) {

        AulaResponse response = service.salvar(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseFactory.success(
                        "Aula cadastrada com sucesso.",
                        response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AulaResponse>> buscar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ResponseFactory.success(
                        "Aula localizada com sucesso.",
                        service.buscar(id)));
    }

    @GetMapping("/modulo/{moduloId}")
    public ResponseEntity<ApiResponse<PageResponse<AulaResponse>>> listar(
            @PathVariable Long moduloId,
            @PageableDefault(sort = "ordem") Pageable pageable) {

        return ResponseEntity.ok(
                ResponseFactory.success(
                        "Aulas carregadas com sucesso.",
                        service.listar(moduloId, pageable)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AulaResponse>> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid AulaRequest request) {

        AulaResponse response = service.atualizar(id, request);

        return ResponseEntity.ok(
                ResponseFactory.success(
                        "Aula atualizada com sucesso.",
                        response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> excluir(
            @PathVariable Long id) {

        service.excluir(id);

        return ResponseEntity.ok(
                ResponseFactory.success(
                        "Aula excluída com sucesso."));
    }

}