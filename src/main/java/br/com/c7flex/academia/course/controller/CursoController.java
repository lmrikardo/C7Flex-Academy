package br.com.c7flex.academia.course.controller;

import br.com.c7flex.academia.common.constants.ApiPaths;
import br.com.c7flex.academia.common.response.ApiResponse;
import br.com.c7flex.academia.common.response.PageResponse;
import br.com.c7flex.academia.common.response.ResponseFactory;
import br.com.c7flex.academia.course.dto.CursoRequest;
import br.com.c7flex.academia.course.dto.CursoResponse;
import br.com.c7flex.academia.course.service.CursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.CURSOS)
@RequiredArgsConstructor
public class CursoController {

    private final CursoService service;

    @PostMapping
    public ResponseEntity<ApiResponse<CursoResponse>> salvar(
            @RequestBody @Valid CursoRequest dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseFactory.success(
                        "Curso cadastrado com sucesso",
                                service.salvar(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CursoResponse>>> listar(@PageableDefault() Pageable pageable) {

        /*PageResponse<CursoResponse> page = service.listar(pageable);

        return ResponseEntity.ok(
                ApiResponse.<PageResponse<CursoResponse>>builder()
                        .success(true)
                        .message("Cursos carregados com sucesso.")
                        .data(page)
                        .build());*/

        return ResponseEntity.ok(
                ResponseFactory.success(
                        "Cursos carregados com sucesso.",
                        service.listar(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CursoResponse>> buscar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ResponseFactory.success(
                        "Curso localizado com sucesso.",
                        service.buscar(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CursoResponse>> atualizar(
            @PathVariable Long id,
            @RequestBody CursoRequest dto) {

        CursoResponse curso = service.atualizar(id,dto);

        return ResponseEntity.ok(
                ApiResponse.<CursoResponse>builder()
                        .success(true)
                        .message("Curso atualizado com sucesso.")
                        .data(curso)
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> excluir(@PathVariable Long id) {

            service.excluir(id);

        return ResponseEntity.ok(
                ResponseFactory.success("Curso removido com sucesso."));
    }

}