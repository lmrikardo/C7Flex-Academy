package br.com.c7flex.academia.matricula.repository;

import br.com.c7flex.academia.matricula.entity.Matricula;
import br.com.c7flex.academia.matricula.entity.StatusMatricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    boolean existsByUsuarioIdAndCursoId(Long usuarioId, Long cursoId);

    boolean existsByUsuarioIdAndCursoIdAndStatus(
            Long usuarioId,
            Long cursoId,
            StatusMatricula status
    );

    Optional<Matricula> findByUsuarioIdAndCursoId(
            Long usuarioId,
            Long cursoId
    );

    List<Matricula> findAllByUsuarioIdAndStatusOrderByDataCadastroDesc(
            Long usuarioId,
            StatusMatricula status
    );

}