package br.com.c7flex.academia.storage.service.impl;

import br.com.c7flex.academia.common.exception.ApiException;
import br.com.c7flex.academia.common.exception.ErrorCode;
import br.com.c7flex.academia.storage.config.StorageProperties;
import br.com.c7flex.academia.storage.dto.UploadResponse;
import br.com.c7flex.academia.storage.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocalStorageService implements StorageService {

    private final StorageProperties properties;

    @Override
    public UploadResponse upload(MultipartFile arquivo) {

        if (arquivo == null || arquivo.isEmpty()) {
            throw new ApiException(
                    ErrorCode.BUSINESS_ERROR,
                    "Nenhum arquivo foi informado."
            );
        }

        try {

            Path diretorio = Paths.get(properties.getPath());

            Files.createDirectories(diretorio);

            String nomeOriginal = StringUtils.cleanPath(
                    arquivo.getOriginalFilename()
            );

            String extensao = "";

            int indice = nomeOriginal.lastIndexOf('.');

            if (indice != -1) {
                extensao = nomeOriginal.substring(indice + 1);
            }

            String nomeArquivo = UUID.randomUUID() +
                    (extensao.isBlank() ? "" : "." + extensao);

            Path destino = diretorio.resolve(nomeArquivo);

            Files.copy(
                    arquivo.getInputStream(),
                    destino,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return new UploadResponse(
                    nomeArquivo,
                    nomeOriginal,
                    arquivo.getContentType(),
                    extensao,
                    arquivo.getSize(),
                    destino.toString()
            );

        } catch (IOException ex) {

            throw new ApiException(
                    ErrorCode.INTERNAL_ERROR,
                    "Erro ao armazenar o arquivo."
            );

        }

    }

    @Override
    public void delete(String caminho) {

        try {

            Files.deleteIfExists(Paths.get(caminho));

        } catch (IOException ex) {

            throw new ApiException(
                    ErrorCode.INTERNAL_ERROR,
                    "Erro ao excluir o arquivo."
            );

        }

    }

}