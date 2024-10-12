package pe.edu.cibertec.app_security_baca_diaz.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.app_security_baca_diaz.model.Usuario;
import pe.edu.cibertec.app_security_baca_diaz.repository.UsuarioRepository;
import pe.edu.cibertec.app_security_baca_diaz.service.IUsuarioService;

@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public Usuario obtenerPorUsername(String username){
        return usuarioRepository.findByUsername(username);
    }
}
