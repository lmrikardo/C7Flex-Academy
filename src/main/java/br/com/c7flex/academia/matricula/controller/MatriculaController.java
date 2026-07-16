package br.com.c7flex.academia.matricula.controller;

import br.com.c7flex.academia.common.response.ApiResponse;
import br.com.c7flex.academia.common.response.ResponseFactory;
import br.com.c7flex.academia.matricula.dto.MatriculaRequest;
import br.com.c7flex.academia.matricula.dto.MatriculaResponse;
import br.com.c7flex.academia.matricula.mapper.MatriculaMapper;
import br.com.c7flex.academia.matricula.service.MatriculaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matriculas")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatriculaService service;
    private final MatriculaMapper mapper;

    @PostMapping
    public ResponseEntity<ApiResponse<MatriculaResponse>> matricular(
            @Valid @RequestBody MatriculaRequest request) {

        MatriculaResponse response = mapper.toResponse(
                service.matricular(
                        request.getUsuarioId(),
                        request.getCursoId()
                )
        );

        return ResponseEntity.ok(
                ResponseFactory.success(
                        "Matrícula realizada com sucesso.",
                        response
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> cancelar(
            @PathVariable Long id) {

        service.cancelar(id);

        return ResponseEntity.ok(
                ResponseFactory.success(
                        "Matrícula cancelada com sucesso.",
                        null
                )
        );
    }

}