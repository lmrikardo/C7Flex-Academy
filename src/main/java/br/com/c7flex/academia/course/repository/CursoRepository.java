package br.com.c7flex.academia.course.repository;

import br.com.c7flex.academia.course.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}