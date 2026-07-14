package br.com.c7flex.academia.module.repository;

import br.com.c7flex.academia.module.entity.Modulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuloRepository extends JpaRepository<Modulo,Long>{

    Page<Modulo> findByCursoId(Long cursoId, Pageable pageable);

}