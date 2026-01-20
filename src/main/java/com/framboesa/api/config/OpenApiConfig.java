package com.framboesa.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Movie Awards API")
                .description("""
                    API responsável por processar indicações e vencedores
                    de prêmios de cinema, calculando os intervalos mínimos
                    e máximos entre vitórias por produtor.
                """)
                .version("1.0.0")
                .contact(new Contact()
                    .name("André Luiz Zahn de Matos")
                    .email("zahndematos@email.com"))
                .license(new License()
                    .name("Uso acadêmico / desafio técnico")));
    }
}
