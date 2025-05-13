package pe.edu.upeu.sisventa.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sisventa.modelo.Emisor;

@Repository
public interface EmisorRepository extends JpaRepository<Emisor, Integer> {
}
