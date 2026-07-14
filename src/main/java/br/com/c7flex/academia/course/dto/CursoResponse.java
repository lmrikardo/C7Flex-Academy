package br.com.c7flex.academia.course.dto;

public record CursoResponse(

        Long id,

        String nome,

        String descricao,

        String imagem,

        Boolean ativo

) {
}