package pe.edu.cibertec.app_security_baca_diaz.service;

import org.springframework.http.ResponseEntity;
import pe.edu.cibertec.app_security_baca_diaz.dto.request.ProyectoRequestDto;
import pe.edu.cibertec.app_security_baca_diaz.dto.response.ProyectoResponseDto;

public interface IProyectoService {

    ResponseEntity<ProyectoResponseDto> crearProyecto(ProyectoRequestDto proyectoRequestDto);
}
