package com.gym.entrenadores.repository;
import com.gym.entrenadores.model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EntrenadorRepository extends JpaRepository <Entrenador,Long> {
    Optional<Entrenador> findByRun(String run);

}
