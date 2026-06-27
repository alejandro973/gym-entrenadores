package com.gym.entrenadores.model;
import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Entrenador" )
public class Entrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true,length = 13,nullable = false)
    private String run; 

    @NotBlank
    @Column(length = 20,nullable = false)
    private String nombre;

    @NotBlank
    @Column(length = 20,nullable = false)
    private String apellido;

    @NotBlank
    @Column(length = 20,nullable = false)
    private String especialidad;

}
