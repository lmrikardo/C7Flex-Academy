package br.com.c7flex.academia.auth.dto;

public record GoogleUser(

        String googleId,

        String nome,

        String email,

        String foto

) {
}
