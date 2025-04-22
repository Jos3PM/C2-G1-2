package pe.edu.upeu.calfx.servicio;

import pe.edu.upeu.calfx.modelo.CalcTO;

import java.util.ArrayList;
import java.util.List;

public class CalcServicioImp implements CalcServicioI{

    List<CalcTO> datos=new ArrayList<>();

    @Override
    public void save(CalcTO calcTO){
        datos.add(calcTO);
    }
    @Override
    public List<CalcTO> finAll() {
        return datos;
    }

    @Override
    public CalcTO findById(int index) {
        return datos.get(index);
    }

    @Override
    public void update(CalcTO calcTO, int index) {
        datos.set(index, calcTO);
    }

    @Override
    public void delete(CalcTO calcTO) {
        datos.remove(calcTO);
    }

    @Override
    public void deleteById(int index) {
        datos.remove(index);
    }

}

