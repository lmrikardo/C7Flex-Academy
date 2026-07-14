package br.com.c7flex.academia.module.entity;

import br.com.c7flex.academia.common.entity.BaseEntity;
import br.com.c7flex.academia.course.entity.Curso;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "modulo")
@Getter
@Setter
public class Modulo extends BaseEntity {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer ordem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

}