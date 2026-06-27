package com.gym.entrenadores.dto;
import lombok.*;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntrenadorRequestDto {

    @NotBlank(message = "El run no puede estar vacio")
    @Column(unique = true,length = 13,nullable = false)
    private String run; 

    @NotBlank(message = "El nombre no puede estar vacio")
    @Column(unique = true,length = 20,nullable = false)
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacio")
    @Column(unique = true,length = 20,nullable = false)
    private String apellido;

    @NotBlank(message = "La especialidad no puede estar vacia ")
    @Column(unique = true,length = 20,nullable = false)
    private String especialidad;

}
