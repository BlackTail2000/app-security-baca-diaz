package pe.edu.cibertec.app_security_baca_diaz.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioReponseDto {
    private Integer idusuario;
    private String username;
    private String email;
    private String token;
}
