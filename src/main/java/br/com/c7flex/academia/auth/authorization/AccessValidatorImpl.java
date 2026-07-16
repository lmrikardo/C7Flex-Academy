package br.com.c7flex.academia.auth.authorization;

import br.com.c7flex.academia.arquivo.repository.ArquivoRepository;
import br.com.c7flex.academia.aula.repository.AulaRepository;
import br.com.c7flex.academia.common.exception.ApiException;
import br.com.c7flex.academia.common.exception.ErrorCode;
import br.com.c7flex.academia.matricula.service.MatriculaService;
import br.com.c7flex.academia.module.repository.ModuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessValidatorImpl
        implements AccessValidator {

    private final MatriculaService matriculaService;

    private final ModuloRepository moduloRepository;

    private final AulaRepository aulaRepository;

    private final ArquivoRepository arquivoRepository;

    @Override
    public void validar(TipoRecurso tipoRecurso, Long id) {

        Long cursoId;

        switch (tipoRecurso) {

            case CURSO -> {
                cursoId = id;
            }

            case MODULO -> {
                cursoId = moduloRepository.buscarCursoId(id);

                if (cursoId == null) {
                    throw new ApiException(ErrorCode.NOT_FOUND, "Módulo não localizado");
                }
            }

            case AULA -> {
                cursoId = aulaRepository.buscarCursoId(id);

                if (cursoId == null) {
                    throw new ApiException(ErrorCode.NOT_FOUND, "Aula não localizada");
                }
            }

            case ARQUIVO -> {
                cursoId = arquivoRepository.buscarCursoId(id);

                if (cursoId == null) {
                    throw new ApiException(ErrorCode.NOT_FOUND, "Arquivo não localizado");
                }
            }

            default -> {
                throw new ApiException(ErrorCode.NOT_FOUND, "Tipo de recurso inválido");
            }

        }

        matriculaService.validarAcesso(cursoId);

    }

}