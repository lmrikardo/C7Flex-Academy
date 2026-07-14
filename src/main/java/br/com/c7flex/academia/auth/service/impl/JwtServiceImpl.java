package br.com.c7flex.academia.auth.service.impl;

import br.com.c7flex.academia.auth.service.JwtService;
import br.com.c7flex.academia.common.config.JwtProperties;
import br.com.c7flex.academia.common.constants.JwtConstants;
import br.com.c7flex.academia.user.entity.Usuario;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final JwtProperties properties;

    private SecretKey getKey() {

        return Keys.hmacShaKeyFor(
                properties.getSecret()
                        .getBytes(StandardCharsets.UTF_8)
        );

    }

    @Override
    public String gerarToken(Usuario usuario) {

        Date agora = new Date();

        Date expiracao = new Date(
                agora.getTime() + properties.getExpiration()
        );

        return Jwts.builder()

                .subject(usuario.getEmail())

                .claim(JwtConstants.USER_ID, usuario.getId())

                .claim(JwtConstants.ROLE, usuario.getRole().name())

                .issuedAt(agora)

                .expiration(expiracao)

                .signWith(getKey())

                .compact();

    }

    @Override
    public String getEmail(String token) {

        return getClaims(token)
                .getSubject();

    }

    @Override
    public boolean validar(String token) {

        try {

            getClaims(token);

            return true;

        } catch (JwtException e) {

            return false;

        }

    }

    @Override
    public boolean expirado(String token) {

        return getClaims(token)
                .getExpiration()
                .before(new Date());

    }

    private Claims getClaims(String token) {

        return Jwts.parser()

                .verifyWith(getKey())

                .build()

                .parseSignedClaims(token)

                .getPayload();

    }

}