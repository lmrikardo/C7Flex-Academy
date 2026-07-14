package br.com.c7flex.academia.auth.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "google")
public class GoogleProperties {

    private String clientId;

}