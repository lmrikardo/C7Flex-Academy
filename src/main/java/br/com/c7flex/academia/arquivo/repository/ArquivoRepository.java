package br.com.c7flex.academia.arquivo.repository;

import br.com.c7flex.academia.arquivo.entity.Arquivo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {

    Page<Arquivo> findByAulaIdAndAtivoTrue(
            Long aulaId,
            Pageable pageable
    );

}