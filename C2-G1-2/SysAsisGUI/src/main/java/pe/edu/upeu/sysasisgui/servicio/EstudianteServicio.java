package pe.edu.upeu.sysasisgui.servicio;

import org.springframework.stereotype.Service;
import pe.edu.upeu.sysasisgui.modelo.Estudiante;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstudianteServicio {

    public List<Estudiante> estudiantes = new ArrayList<>();

    public void save(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public List<Estudiante> getEntidad() {
        return estudiantes;
    }

    public void update(int index, Estudiante estudiante) {
        estudiantes.set(index, estudiante);
    }

    public void delete(int index) {
        estudiantes.remove(index);
    }
    public void deleteEntidad(Estudiante index) {
        estudiantes.remove(index);
    }

    public void cargarDatos() {
        estudiantes.add(new Estudiante("juan"));
        estudiantes.add(new Estudiante("flores"));
        estudiantes.add(new Estudiante("damio"));
        estudiantes.add(new Estudiante("cain"));
        estudiantes.add(new Estudiante("cruz"));
        estudiantes.add(new Estudiante("remi"));
    }

}
