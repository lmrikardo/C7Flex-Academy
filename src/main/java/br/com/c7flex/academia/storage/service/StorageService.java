package br.com.c7flex.academia.storage.service;

import br.com.c7flex.academia.storage.dto.UploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    UploadResponse upload(MultipartFile arquivo);

    void delete(String caminho);

}