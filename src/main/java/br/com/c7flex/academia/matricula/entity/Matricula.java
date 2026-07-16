package br.com.c7flex.academia.matricula.entity;

import br.com.c7flex.academia.common.entity.BaseEntity;
import br.com.c7flex.academia.course.entity.Curso;
import br.com.c7flex.academia.user.entity.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "matricula",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_matricula_usuario_curso",
                        columnNames = {
                                "usuario_id",
                                "curso_id"
                        }
                )
        }
)
@Getter
@Setter
public class Matricula extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusMatricula status = StatusMatricula.ATIVA;

    @Column(name = "data_matricula", nullable = false)
    private LocalDateTime dataMatricula = LocalDateTime.now();

    @Column(name = "data_expiracao")
    private LocalDateTime dataExpiracao;

    public static Matricula criar(Usuario usuario, Curso curso) {
        Matricula matricula = new Matricula();
        matricula.setUsuario(usuario);
        matricula.setCurso(curso);
        matricula.setStatus(StatusMatricula.ATIVA);
        return matricula;
    }
}