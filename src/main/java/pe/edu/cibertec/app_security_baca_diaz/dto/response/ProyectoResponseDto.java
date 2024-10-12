package pe.edu.cibertec.app_security_baca_diaz.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProyectoResponseDto {
    private Integer id;
    private String titulo;
    private String descripcion;
    private String estado;
}
