package br.com.c7flex.academia.module.dto;

public record ModuloResponse(

        Long id,

        String nome,

        Integer ordem,

        Long cursoId,

        String curso

) {
}