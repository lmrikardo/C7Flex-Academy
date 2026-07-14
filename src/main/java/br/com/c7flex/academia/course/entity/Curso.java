package br.com.c7flex.academia.course.entity;

import br.com.c7flex.academia.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "curso")
@Getter
@Setter
public class Curso extends BaseEntity {

    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "nvarchar(250)")
    private String descricao;

    private String imagem;

    private Boolean ativo = true;

}