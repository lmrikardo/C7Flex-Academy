package br.com.c7flex.academia.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "ultima_alteracao")
    private LocalDateTime ultimaAlteracao;

    @PrePersist
    protected void prePersist() {
        dataCadastro = LocalDateTime.now();
        ultimaAlteracao = LocalDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        ultimaAlteracao = LocalDateTime.now();
    }
}