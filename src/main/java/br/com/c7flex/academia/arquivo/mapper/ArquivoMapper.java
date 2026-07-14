package br.com.c7flex.academia.arquivo.mapper;

import br.com.c7flex.academia.arquivo.dto.ArquivoResponse;
import br.com.c7flex.academia.arquivo.entity.Arquivo;
import org.springframework.stereotype.Component;

@Component
public class ArquivoMapper {

    public ArquivoResponse toResponse(Arquivo arquivo){

        return new ArquivoResponse(

                arquivo.getId(),

                arquivo.getNomeOriginal(),

                arquivo.getTipo(),

                arquivo.getTamanho(),

                arquivo.getOrdem()

        );

    }

}