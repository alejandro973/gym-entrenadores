package com.gym.entrenadores.controller;

import com.gym.entrenadores.dto.EntrenadorRequestDto;
import com.gym.entrenadores.dto.EntrenadorResponseDto;
import com.gym.entrenadores.service.EntrenadorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/entrenadores")
@AllArgsConstructor
@Tag(name = "Entrenadores", description = "Endpoints REST para la administración del personal técnico, asignación de turnos y especialidades del gimnasio")
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    // 1. Obtener Entrenador por ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener Entrenador por ID", description = "Permite recuperar la ficha completa de un entrenador específico utilizando su clave primaria numérica.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa. Se retorna el entrenador encontrado."),
        @ApiResponse(responseCode = "404", description = "No se encontró ningún entrenador asociado al ID suministrado.")
    })
    public ResponseEntity<EntrenadorResponseDto> obtenerPorId(
        @Parameter(description = "Identificador numérico único del entrenador", example = "1", required = true)
        @PathVariable Long id
    ) {
        return entrenadorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 2. Obtener Entrenador por RUN
    @GetMapping("/buscar/{run}")
    @Operation(summary = "Obtener entrenador por RUN", description = "Permite consultar los datos de un entrenador mediante su documento de identidad nacional (RUN).")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Entrenador localizado con éxito."),
        @ApiResponse(responseCode = "404", description = "No existe ningún registro de entrenador con el RUN ingresado.")
    })
    public ResponseEntity<EntrenadorResponseDto> obtenerPorRun(
        @Parameter(description = "RUN del entrenador (con guion y dígito verificador)", example = "12345678-9", required = true)
        @PathVariable String run
    ) {
        return entrenadorService.buscarPorRun(run)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Obtener todos los entrenadores
    @GetMapping
    @Operation(summary = "Listar todos los entrenadores", description = "Retorna una lista completa con todo el personal técnico contratado en la cadena de gimnasios.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta procesada correctamente. Devuelve el listado de registros."),
        @ApiResponse(responseCode = "500", description = "Error interno de la plataforma al procesar la solicitud.")
    })
    public ResponseEntity<List<EntrenadorResponseDto>> obtenderTodos() {
        return ResponseEntity.ok(entrenadorService.buscarTodos());
    }

    // 4. Crear un nuevo entrenador
    @PostMapping
    @Operation(summary = "Registrar un nuevo entrenador", description = "Permite dar de alta un entrenador en el sistema. Valida las reglas del negocio del DTO de entrada (RUN único, formato, campos obligatorios).")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Entrenador creado e incorporado al sistema correctamente."),
        @ApiResponse(responseCode = "400", description = "Solicitud incorrecta (Bad Request). Los datos del JSON no cumplen con las validaciones del DTO.")
    })
    public ResponseEntity<EntrenadorResponseDto> crearEntrenador(@Valid @RequestBody EntrenadorRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(entrenadorService.guardarEntrenador(dto));
    }

    // 5. Actualizar un entrenador por ID
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar datos de un entrenador", description = "Permite modificar los atributos de un entrenador existente localizándolo mediante su ID y enviando el cuerpo actualizado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Datos del entrenador actualizados con éxito."),
        @ApiResponse(responseCode = "400", description = "Error de validación en la estructura del cuerpo del DTO."),
        @ApiResponse(responseCode = "404", description = "No se pudo actualizar. El ID no corresponde a ningún entrenador registrado.")
    })
    public ResponseEntity<EntrenadorResponseDto> actualizarEntrenador(
        @Parameter(description = "ID del entrenador a modificar", example = "1", required = true)
        @PathVariable Long id,
        @Valid @RequestBody EntrenadorRequestDto dto
    ) {
        return entrenadorService.actualizarEntrenador(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 6. Eliminar un entrenador por ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Remover un entrenador del sistema", description = "Elimina de forma permanente o lógica el registro de un entrenador del gimnasio basándose en su clave primaria.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Operación exitosa. Entrenador eliminado de la base de datos (No Content)."),
        @ApiResponse(responseCode = "404", description = "Falló la eliminación. El ID ingresado no existe en los registros.")
    })
    public ResponseEntity<Void> eliminarEntrenador(
        @Parameter(description = "ID del entrenador que se desea eliminar", example = "2", required = true)
        @PathVariable Long id
    ) {
        entrenadorService.eliminarEntrenador(id);
        return ResponseEntity.noContent().build();
    }
}
