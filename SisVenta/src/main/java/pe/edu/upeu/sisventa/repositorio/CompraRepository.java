package pe.edu.upeu.sisventa.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sisventa.modelo.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {
}
