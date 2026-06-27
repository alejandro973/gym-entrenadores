package com.gym.entrenadores.dto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EntrenadorResponseDto {
    
    private Long id;
    private String run; 
    private String nombre;
    private String apellido;
    private String especialidad;


}
