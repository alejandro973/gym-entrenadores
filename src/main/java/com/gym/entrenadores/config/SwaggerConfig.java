package com.gym.entrenadores.config; // Ajustado al paquete de entrenadores

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Le indica a Spring que aplique este ajuste al arrancar el contexto de Entrenadores
public class SwaggerConfig {

    @Bean // Registra el objeto OpenAPI administrado por el framework
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gym API 2026 - Microservicio de Entrenadores") // Título modular
                        .version("1.0") // Versión del artefacto
                        .description("Documentación oficial de endpoints REST para la gestión integral del personal técnico, asignación de turnos y planes de entrenamiento "));
    }
}