package br.com.c7flex.academia.user.entity;

import br.com.c7flex.academia.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario extends BaseEntity {

    @Column(name = "google_id", length = 100)
    private String googleId;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(length = 500)
    private String foto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.ROLE_ALUNO;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(name = "ultimo_acesso")
    private LocalDateTime ultimoAcesso;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusUsuario status;

    @PrePersist
    public void prePersist() {

        super.prePersist();

        if (ativo == null)
            ativo = true;

        if (role == null)
            role = Role.ROLE_ALUNO;

    }

}