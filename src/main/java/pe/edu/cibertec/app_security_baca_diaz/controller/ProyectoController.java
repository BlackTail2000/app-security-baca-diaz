package pe.edu.cibertec.app_security_baca_diaz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.app_security_baca_diaz.dto.request.ProyectoRequestDto;
import pe.edu.cibertec.app_security_baca_diaz.dto.response.ProyectoResponseDto;
import pe.edu.cibertec.app_security_baca_diaz.service.IProyectoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/proyecto")
public class ProyectoController {
    private final IProyectoService proyectoService;

    @PostMapping
    @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<ProyectoResponseDto> crearProyecto(@RequestBody ProyectoRequestDto proyectoRequestDto) {
        return proyectoService.crearProyecto(proyectoRequestDto);
    }

    @PutMapping("/finalizar/{id}")
    @PreAuthorize("hasAnyRole('GESTOR', 'COORDINADOR')")
    public ResponseEntity<ProyectoResponseDto> finalizarProyecto(@PathVariable("id") Integer idProyecto) {
        return proyectoService.finalizarProyecto(idProyecto);
    }
}
