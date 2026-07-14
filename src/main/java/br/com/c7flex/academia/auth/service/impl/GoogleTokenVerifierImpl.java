package br.com.c7flex.academia.auth.service.impl;

import br.com.c7flex.academia.auth.config.GoogleProperties;
import br.com.c7flex.academia.auth.dto.GoogleUser;
import br.com.c7flex.academia.auth.service.GoogleTokenVerifier;
import br.com.c7flex.academia.common.exception.ApiException;
import br.com.c7flex.academia.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleTokenVerifierImpl implements GoogleTokenVerifier {

    private final JwtDecoder googleJwtDecoder;

    private final GoogleProperties properties;

    @Override
    public GoogleUser verify(String idToken) {

        Jwt jwt = googleJwtDecoder.decode(idToken);

        validar(jwt);

        return new GoogleUser(

                jwt.getSubject(),

                jwt.getClaimAsString("name"),

                jwt.getClaimAsString("email"),

                jwt.getClaimAsString("picture")

        );

    }

    private void validar(Jwt jwt) {

        String audience = jwt.getAudience().getFirst();

        if (!properties.getClientId().equals(audience)) {

            throw new ApiException(ErrorCode.INTERNAL_ERROR, "Token do Google inválido.");

        }

        String issuer = jwt.getIssuer().toString();

        if (!issuer.equals("https://accounts.google.com")) {

            throw new ApiException(ErrorCode.INTERNAL_ERROR, "Issuer inválido.");

        }

    }

}