package br.com.c7flex.academia.auth.authorization.aspect;

import br.com.c7flex.academia.auth.authorization.annotation.ValidarAcesso;
import br.com.c7flex.academia.auth.authorization.AccessValidator;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class AccessValidationAspect {

    private final AccessValidator accessValidator;

    @Before("@annotation(validarAcesso)")
    public void validar(JoinPoint joinPoint,
                        ValidarAcesso validarAcesso) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        String[] parameterNames = signature.getParameterNames();
        Object[] parameterValues = joinPoint.getArgs();

        Long id = null;

        for (int i = 0; i < parameterNames.length; i++) {

            if (parameterNames[i].equals(validarAcesso.parametro())) {

                Object value = parameterValues[i];

                if (!(value instanceof Long parametroId)) {
                    throw new IllegalArgumentException(
                            "O parâmetro '" + validarAcesso.parametro() +
                                    "' deve ser do tipo Long."
                    );
                }

                id = parametroId;
                break;
            }
        }

        if (id == null) {
            throw new IllegalArgumentException(
                    "Parâmetro '" + validarAcesso.parametro() +
                            "' não encontrado."
            );
        }

        accessValidator.validar(
                validarAcesso.tipo(),
                id
        );
    }
}
