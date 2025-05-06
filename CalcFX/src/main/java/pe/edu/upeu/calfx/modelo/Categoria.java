package pe.edu.upeu.calfx.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Categoria {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idCategoria;
    @Column(name = "Nombre")
    String nombre;
}
