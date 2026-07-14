package br.com.c7flex.academia.user.dto;

public record UsuarioResponse(

        Long id,

        String nome,

        String email,

        String foto,

        String role

) {
}