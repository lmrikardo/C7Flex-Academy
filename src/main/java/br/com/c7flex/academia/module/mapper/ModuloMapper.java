package br.com.c7flex.academia.module.mapper;

import br.com.c7flex.academia.module.dto.ModuloResponse;
import br.com.c7flex.academia.module.entity.Modulo;
import org.springframework.stereotype.Component;

@Component
public class ModuloMapper {

    public ModuloResponse toResponse(Modulo modulo){

        return new ModuloResponse(

                modulo.getId(),
                modulo.getNome(),
                modulo.getOrdem(),
                modulo.getCurso().getId(),
                modulo.getCurso().getNome()

        );

    }

}