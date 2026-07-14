package br.com.c7flex.academia.aula.entity;

import br.com.c7flex.academia.common.entity.BaseEntity;
import br.com.c7flex.academia.arquivo.entity.Arquivo;
import br.com.c7flex.academia.module.entity.Modulo;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="aula")
@Getter
@Setter
public class Aula extends BaseEntity{

    @Column(nullable=false)
    private String titulo;

    @Column(columnDefinition="nvarchar(max)")
    private String descricao;

    @Column(name="youtube_id")
    private String youtubeId;

    @Column(name="youtube_url")
    private String youtubeUrl;

    private String thumbnail;

    private Integer duracao;

    private Integer ordem;

    private Boolean publicado=true;

    @OneToMany(
            mappedBy = "aula",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Arquivo> arquivos = new ArrayList<>();

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="modulo_id")
    private Modulo modulo;

}