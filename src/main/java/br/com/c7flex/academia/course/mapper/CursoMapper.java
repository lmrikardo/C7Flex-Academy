package br.com.c7flex.academia.course.mapper;

import br.com.c7flex.academia.course.dto.CursoRequest;
import br.com.c7flex.academia.course.dto.CursoResponse;
import br.com.c7flex.academia.course.entity.Curso;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    Curso toEntity(CursoRequest dto);

    CursoResponse toResponse(Curso entity);

}