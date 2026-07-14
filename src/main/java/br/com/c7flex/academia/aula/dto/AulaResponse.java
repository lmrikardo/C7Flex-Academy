package br.com.c7flex.academia.aula.dto;

public record AulaResponse(

        Long id,

        String titulo,

        String descricao,

        String youtubeUrl,

        Integer ordem,

        Boolean publicado

){}