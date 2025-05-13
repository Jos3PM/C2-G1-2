package pe.edu.upeu.sisventa.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sisventa.modelo.VentCarrito;

@Repository
public interface VentCarritoRepository extends JpaRepository<VentCarrito, Integer> {
}

