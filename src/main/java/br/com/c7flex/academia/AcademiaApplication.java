package br.com.c7flex.academia;

import br.com.c7flex.academia.auth.config.GoogleProperties;
import br.com.c7flex.academia.common.config.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        GoogleProperties.class,
        JwtProperties.class
})
public class AcademiaApplication {

    public static void main(String[] args) {

        SpringApplication.run(AcademiaApplication.class,args);

    }
}