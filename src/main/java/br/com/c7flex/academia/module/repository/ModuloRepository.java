package br.com.c7flex.academia.module.repository;

import br.com.c7flex.academia.module.entity.Modulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ModuloRepository extends JpaRepository<Modulo,Long>{

    Page<Modulo> findByCursoId(Long cursoId, Pageable pageable);

    @Query("""
        select m.curso.id
        from Modulo m
        where m.id = :moduloId
    """)
    Long buscarCursoId(Long moduloId);

}