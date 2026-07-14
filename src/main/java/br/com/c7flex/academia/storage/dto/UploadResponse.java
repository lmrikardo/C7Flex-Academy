package br.com.c7flex.academia.storage.dto;

public record UploadResponse(

        String nome,

        String nomeOriginal,

        String tipo,

        String extensao,

        Long tamanho,

        String caminho

) {
}