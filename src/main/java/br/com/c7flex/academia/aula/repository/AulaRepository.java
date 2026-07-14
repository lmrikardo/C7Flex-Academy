package br.com.c7flex.academia.aula.repository;

import br.com.c7flex.academia.aula.entity.Aula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AulaRepository extends JpaRepository<Aula, Long> {

    Page<Aula> findByModuloId(Long moduloId, Pageable pageable);

}