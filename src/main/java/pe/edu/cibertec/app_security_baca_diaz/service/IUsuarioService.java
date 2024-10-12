package pe.edu.cibertec.app_security_baca_diaz.service;

import pe.edu.cibertec.app_security_baca_diaz.model.Usuario;

public interface IUsuarioService {
    Usuario obtenerPorUsername(String username);
}
