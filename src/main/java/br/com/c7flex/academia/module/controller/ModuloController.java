package br.com.c7flex.academia.module.controller;

import br.com.c7flex.academia.common.constants.ApiPaths;
import br.com.c7flex.academia.common.response.ApiResponse;
import br.com.c7flex.academia.common.response.PageResponse;
import br.com.c7flex.academia.common.response.ResponseFactory;
import br.com.c7flex.academia.module.dto.ModuloRequest;
import br.com.c7flex.academia.module.dto.ModuloResponse;
import br.com.c7flex.academia.module.service.ModuloService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.MODULO)
@RequiredArgsConstructor
public class ModuloController {

    private final ModuloService service;

    @PostMapping
    public ResponseEntity<ApiResponse<ModuloResponse>> salvar(
            @RequestBody @Valid ModuloRequest dto){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseFactory.success(
                        "Modulo cadastrado com sucesso",
                        service.salvar(dto)));
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<ApiResponse<PageResponse<ModuloResponse>>> listar(
            @PathVariable Long cursoId,
            @PageableDefault(sort = "ordem") Pageable pageable){

        PageResponse<ModuloResponse> modulos = service.listar(cursoId, pageable);

        return ResponseEntity.status(HttpStatus.FOUND).body(
                ApiResponse.<PageResponse<ModuloResponse>>builder()
                        .success(true)
                        .message("Modulos carregados com sucesso")
                        .data(modulos)
                        .build());
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<ApiResponse<Void>> excluir(@PathVariable Long id){

        service.excluir(id);

        return ResponseEntity.ok(
                ResponseFactory.success("Modulo removido com sucesso."));

    }

}