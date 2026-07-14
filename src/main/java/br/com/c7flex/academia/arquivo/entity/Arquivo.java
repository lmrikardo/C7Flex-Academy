package br.com.c7flex.academia.arquivo.entity;

import br.com.c7flex.academia.aula.entity.Aula;
import br.com.c7flex.academia.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "arquivo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Arquivo extends BaseEntity {

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(name = "nome_original", nullable = false, length = 255)
    private String nomeOriginal;

    @Column(nullable = false, length = 100)
    private String tipo;

    @Column(nullable = false, length = 20)
    private String extensao;

    @Column(nullable = false)
    private Long tamanho;

    @Column(nullable = false, length = 500)
    private String caminho;

    @Builder.Default
    @Column(nullable = false)
    private Integer ordem = 1;

    @Builder.Default
    @Column(nullable = false)
    private Boolean ativo = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aula_id", nullable = false)
    private Aula aula;

    @PrePersist
    public void prePersist() {
        super.prePersist();

        if (ativo == null) {
            ativo = true;
        }

        if (ordem == null) {
            ordem = 1;
        }
    }
}