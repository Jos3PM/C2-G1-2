package pe.edu.upeu.sisventa.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sisventa.modelo.Producto;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Aquí puedes agregar métodos personalizados si necesitas realizar consultas específicas

    @Query(value = "SELECT p.* FROM upeu_producto p WHERE p.nombre like :filter", nativeQuery = true)
    List<Producto> listAutoCompletProducto(@Param("filter") String filter);
    @Query(value = "SELECT p.* FROM upeu_producto p WHERE p.id_marca=:filter", nativeQuery = true)
            List<Producto> listProductoMarca(@Param("filter") Integer filter);
}