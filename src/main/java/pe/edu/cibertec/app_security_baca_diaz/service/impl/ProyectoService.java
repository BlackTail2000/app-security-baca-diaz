package pe.edu.cibertec.app_security_baca_diaz.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.app_security_baca_diaz.dto.request.ProyectoRequestDto;
import pe.edu.cibertec.app_security_baca_diaz.dto.response.ProyectoResponseDto;
import pe.edu.cibertec.app_security_baca_diaz.model.Proyecto;
import pe.edu.cibertec.app_security_baca_diaz.repository.ProyectoRepository;
import pe.edu.cibertec.app_security_baca_diaz.service.IProyectoService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProyectoService implements IProyectoService {
    private final ProyectoRepository proyectoRepository;

    @Override
    public ResponseEntity<ProyectoResponseDto> crearProyecto(ProyectoRequestDto proyectoRequestDto) {
        Proyecto proyecto = new Proyecto();
        proyecto.setTitulo(proyectoRequestDto.getTitulo());
        proyecto.setDescripcion(proyectoRequestDto.getDescripcion());
        proyecto.setActivo(true);
        proyecto = proyectoRepository.save(proyecto);

        return new ResponseEntity<>(ProyectoResponseDto.builder()
                .id(proyecto.getId())
                .descripcion(proyecto.getDescripcion())
                .titulo(proyecto.getTitulo())
                .estado("Activo")
                .build(),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProyectoResponseDto> finalizarProyecto(Integer idProyecto) {
        Proyecto proyecto = null;
        Optional<Proyecto> optional = proyectoRepository.findById(idProyecto);
        if(optional.isPresent())
            proyecto = optional.get();

        proyecto.setActivo(false);
        proyecto = proyectoRepository.save(proyecto);
        return new ResponseEntity<>(ProyectoResponseDto.builder()
                .id(proyecto.getId())
                .descripcion(proyecto.getDescripcion())
                .titulo(proyecto.getTitulo())
                .estado("Finalizado")
                .build(),
                HttpStatus.OK);
    }
}
