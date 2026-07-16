package br.com.c7flex.academia.arquivo.repository;

import br.com.c7flex.academia.arquivo.entity.Arquivo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {

    Page<Arquivo> findByAulaIdAndAtivoTrue(
            Long aulaId,
            Pageable pageable
    );

    @Query("""
        select a.modulo.curso.id
        from Arquivo ar
        join ar.aula a
        where ar.id = :arquivoId
    """)
    Long buscarCursoId(Long arquivoId);

}