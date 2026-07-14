package br.com.c7flex.academia.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * Chave secreta utilizada para assinar o JWT.
     */
    private String secret;

    /**
     * Tempo de expiração em milissegundos.
     */
    private Long expiration;

}