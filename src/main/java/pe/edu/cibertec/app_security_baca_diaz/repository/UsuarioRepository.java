package pe.edu.cibertec.app_security_baca_diaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.app_security_baca_diaz.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    Usuario findByUsername(String username);
}
