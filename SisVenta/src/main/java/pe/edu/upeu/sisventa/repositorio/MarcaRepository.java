package pe.edu.upeu.sisventa.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sisventa.modelo.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
}
