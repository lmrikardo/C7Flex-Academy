package br.com.c7flex.academia.auth.controller;

import br.com.c7flex.academia.auth.dto.*;
import br.com.c7flex.academia.auth.service.AuthService;
import br.com.c7flex.academia.common.constants.ApiPaths;
import br.com.c7flex.academia.common.response.ApiResponse;
import br.com.c7flex.academia.common.response.ResponseFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/google")
    public ResponseEntity<ApiResponse<LoginResponse>> loginGoogle(
            @Valid @RequestBody GoogleLoginRequest request) {

        return ResponseEntity.ok(
                ResponseFactory.success(
                        "Login realizado com sucesso.",
                        service.loginGoogle(request)
                )
        );

    }

    @PostMapping("/dev-login")
    public ResponseEntity<ApiResponse<JwtResponse>> devLogin(
            @Valid @RequestBody DevLoginRequest request) {

        return ResponseEntity.ok(
                ResponseFactory.success(
                        "Login realizado com sucesso.",
                        service.devLogin(request)
                )
        );
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<MeResponse>> me() {

        return ResponseEntity.ok(

                ResponseFactory.success(

                        "Usuário carregado com sucesso.",

                        service.me()

                )

        );

    }

}
