package com.gym.entrenadores.service;
import com.gym.entrenadores.model.Entrenador;
import com.gym.entrenadores.repository.EntrenadorRepository;
import com.gym.entrenadores.dto.EntrenadorResponseDto;
import com.gym.entrenadores.dto.EntrenadorRequestDto;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;

    private EntrenadorResponseDto maptoDto(Entrenador e){
        return new EntrenadorResponseDto(
            e.getId(),
            e.getRun(),
            e.getNombre(),
            e.getApellido(),
            e.getEspecialidad()
        );
    }

    public Optional<EntrenadorResponseDto> buscarPorId(Long id){
        return entrenadorRepository.findById(id).map(this::maptoDto);

    }

    public Optional<EntrenadorResponseDto> buscarPorRun(String run){
        return entrenadorRepository.findByRun(run).map(this::maptoDto);

    }

    public List<EntrenadorResponseDto> buscarTodos(){
 
       return entrenadorRepository.findAll().stream().map(this::maptoDto).collect(Collectors.toList());
    }

    public EntrenadorResponseDto guardarEntrenador(EntrenadorRequestDto dto){
        Entrenador e = new Entrenador(null,dto.getRun(),dto.getNombre(),dto.getApellido(),dto.getEspecialidad());
        return maptoDto(entrenadorRepository.save(e));
    }

    public Optional<EntrenadorResponseDto> actualizarEntrenador(Long id,EntrenadorRequestDto dto){
        return entrenadorRepository.findById(id).map(e->{
            e.setRun(dto.getRun());
            e.setNombre(dto.getNombre());
            e.setApellido(dto.getApellido());
            e.setEspecialidad(dto.getEspecialidad());
            return maptoDto(entrenadorRepository.save(e));

        });
    }

    public void eliminarEntrenador(Long id){
        entrenadorRepository.deleteById(id);
    }



    


}
