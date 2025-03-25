package pe.edu.upeu.sysasisgui.servicio;

import org.springframework.stereotype.Service;
import pe.edu.upeu.sysasisgui.modelo.Asistencia;
import pe.edu.upeu.sysasisgui.modelo.Estudiante;

import java.util.ArrayList;
import java.util.List;

@Service
public class AsistenciaServicio {

    public List<Asistencia> asistencia = new ArrayList<>();

    public void save(Asistencia dato) {
        asistencia.add(dato);
    }

    public Asistencia getEntidad(int index) {
        return asistencia.get(index);
    }

    public void update(int index, Asistencia dato) {
        asistencia.set(index, dato);
    }

    public void delete(int index) {
        asistencia.remove(index);
    }

    public void deleteEntidad(Asistencia dato) {
        asistencia.remove(dato);
    }
}
