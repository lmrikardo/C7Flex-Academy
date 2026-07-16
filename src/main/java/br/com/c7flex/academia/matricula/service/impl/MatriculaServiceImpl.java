package br.com.c7flex.academia.matricula.service.impl;

import br.com.c7flex.academia.common.exception.ApiException;
import br.com.c7flex.academia.common.exception.ErrorCode;
import br.com.c7flex.academia.common.security.CurrentUser;
import br.com.c7flex.academia.course.entity.Curso;
import br.com.c7flex.academia.course.repository.CursoRepository;
import br.com.c7flex.academia.matricula.entity.Matricula;
import br.com.c7flex.academia.matricula.entity.StatusMatricula;
import br.com.c7flex.academia.matricula.repository.MatriculaRepository;
import br.com.c7flex.academia.matricula.service.MatriculaService;
import br.com.c7flex.academia.user.entity.Usuario;
import br.com.c7flex.academia.user.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;
    private final CurrentUser currentUser;

    @Override
    public boolean possuiAcesso(Long cursoId) {

        Long usuarioId = currentUser.getId();

        return repository.existsByUsuarioIdAndCursoIdAndStatus(
                usuarioId,
                cursoId,
                StatusMatricula.ATIVA
        );
    }

    @Override
    public void validarAcesso(Long cursoId) {

        if (!possuiAcesso(cursoId)) {
            throw new ApiException(ErrorCode.VALIDATION_ERROR, "Você não possui acesso a este curso.");
        }

    }

    @Override
    public Matricula matricular(Long usuarioId, Long cursoId) {

        if (repository.existsByUsuarioIdAndCursoId(usuarioId, cursoId)) {
            throw new ApiException(ErrorCode.VALIDATION_ERROR, "Usuário já matriculado neste curso.");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ApiException(ErrorCode.VALIDATION_ERROR, "Usuário não encontrado."));

        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ApiException(ErrorCode.VALIDATION_ERROR, "Curso não encontrado."));

        Matricula matricula = new Matricula();
        matricula.setUsuario(usuario);
        matricula.setCurso(curso);
        matricula.setStatus(StatusMatricula.ATIVA);

        return repository.save(matricula);
    }

    @Override
    public void cancelar(Long matriculaId) {

        Matricula matricula = repository.findById(matriculaId)
                .orElseThrow(() -> new ApiException(ErrorCode.VALIDATION_ERROR, "Matrícula não encontrada."));

        matricula.setStatus(StatusMatricula.CANCELADA);

        repository.save(matricula);
    }

}