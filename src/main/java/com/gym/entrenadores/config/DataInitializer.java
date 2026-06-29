package com.gym.entrenadores.config;

import com.gym.entrenadores.model.Entrenador;
import com.gym.entrenadores.repository.EntrenadorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final EntrenadorRepository repository;

    @Override
    public void run(String... args) {
      
        if (repository.count() > 0) {
            log.info(">>> La base de datos de Entrenadores ya tiene información. Saltando carga.");
            return;
        }

        log.info(">>> Iniciando carga de entrenadores de prueba corregidos para Feign...");

       
        repository.save(new Entrenador(null, "11111111-1", "Pedro", "Pascal", "Yoga"));
        repository.save(new Entrenador(null, "22222222-2", "Gina", "Carano", "Crossfit"));
        repository.save(new Entrenador(null, "33333333-3", "Arnold", "Schwarzenegger", "Fisicoculturismo"));
        repository.save(new Entrenador(null, "44444444-4", "Ronda", "Rousey", "Defensa Personal"));

        log.info(">>> ¡Carga de Entrenadores finalizada con éxito con los nuevos RUNs!");
    }
}