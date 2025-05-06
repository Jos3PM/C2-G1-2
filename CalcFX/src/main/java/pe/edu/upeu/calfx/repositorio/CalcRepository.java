package pe.edu.upeu.calfx.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.calfx.modelo.CalcTO;

@Repository
public interface CalcRepository extends JpaRepository<CalcTO, Long> {
}
