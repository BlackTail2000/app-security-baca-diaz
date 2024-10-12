package pe.edu.cibertec.app_security_baca_diaz.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Proyecto {
    @Id
    @Column(name = "id_proyecto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "titulo_proyecto")
    private String titulo;
    private String descripcion;
    private Boolean activo;
}
