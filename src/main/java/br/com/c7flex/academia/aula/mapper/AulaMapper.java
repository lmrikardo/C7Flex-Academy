package br.com.c7flex.academia.aula.mapper;

import br.com.c7flex.academia.aula.dto.AulaResponse;
import br.com.c7flex.academia.aula.entity.Aula;
import org.springframework.stereotype.Component;

@Component
public class AulaMapper {

    public AulaResponse toResponse(Aula aula) {

        return new AulaResponse(
                aula.getId(),
                aula.getTitulo(),
                aula.getDescricao(),
                aula.getYoutubeUrl(),
                aula.getOrdem(),
                aula.getPublicado()
        );

    }

}