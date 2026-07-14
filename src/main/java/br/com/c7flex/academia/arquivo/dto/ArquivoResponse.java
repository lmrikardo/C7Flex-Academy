package br.com.c7flex.academia.arquivo.dto;

public record ArquivoResponse(

        Long id,

        String nomeOriginal,

        String tipo,

        Long tamanho,

        Integer ordem

) {
}