package com.gym.entrenadores;
import com.gym.entrenadores.dto.EntrenadorRequestDto;
import com.gym.entrenadores.dto.EntrenadorResponseDto;
import com.gym.entrenadores.model.Entrenador;
import com.gym.entrenadores.repository.EntrenadorRepository;
import com.gym.entrenadores.service.EntrenadorService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;


@SpringBootTest
public class EntrenadorServiceTest {

    @Autowired
    private EntrenadorService entrenadorService;

    @MockBean
    private EntrenadorRepository entrenadorRepository;

    @Test
    public void TestFindAll(){
        when(entrenadorRepository.findAll()).thenReturn(List.of(new Entrenador(1L, "Alejandro", "Perez", "26572299-0", "Karate")));

        List<EntrenadorResponseDto> Entrenadores = entrenadorService.buscarTodos();

        assertNotNull(Entrenadores);
        assertEquals(1, Entrenadores.size());

    }


  
    @Test
    public void testFindById() {
        Long id = 1L;
        Entrenador entrenador = new Entrenador(id, "26572299-0", "Alejandro", "Perez", "Karate");

       
        when(entrenadorRepository.findById(id)).thenReturn(Optional.of(entrenador));

        Optional<EntrenadorResponseDto> found = entrenadorService.buscarPorId(id);

        
        assertTrue(found.isPresent());
        assertEquals(1,found.get().getId());
    }

  
    @Test
    public void testDeleteById() {
        Long id = 1L;

        
        doNothing().when(entrenadorRepository).deleteById(id);

        
        entrenadorService.eliminarEntrenador(id);

        
        verify(entrenadorRepository, times(1)).deleteById(id);
    }
}
