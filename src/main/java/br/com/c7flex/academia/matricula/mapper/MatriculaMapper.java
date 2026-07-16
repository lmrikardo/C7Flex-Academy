package br.com.c7flex.academia.matricula.mapper;

import br.com.c7flex.academia.matricula.dto.MatriculaResponse;
import br.com.c7flex.academia.matricula.entity.Matricula;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MatriculaMapper {

    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "usuario", source = "usuario.nome")
    @Mapping(target = "cursoId", source = "curso.id")
    @Mapping(target = "curso", source = "curso.nome")
    MatriculaResponse toResponse(Matricula matricula);

}