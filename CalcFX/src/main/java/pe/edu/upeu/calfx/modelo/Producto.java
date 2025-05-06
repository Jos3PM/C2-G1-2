package pe.edu.upeu.calfx.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "producto")
public class Producto {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idProducto;
    String nombre;
    @ManyToOne
            @JoinColumn(name = "id_categoria", nullable = false)
    Categoria categoria;
}
