package pe.edu.upeu.calfx.servicio;

import pe.edu.upeu.calfx.modelo.CalcTO;

import java.util.List;

public interface CalcServicioI {

    public List<CalcTO> finAll();
    public CalcTO findById(int index);

    public void save(CalcTO calcTO);

    public void delete(CalcTO calcTO);
    public void deleteById(int index);

    public void update(CalcTO calcTO, int index);

}
