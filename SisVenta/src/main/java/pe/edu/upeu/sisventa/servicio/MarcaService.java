package pe.edu.upeu.sisventa.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sisventa.modelo.Marca;
import pe.edu.upeu.sisventa.modelo.Producto;

import java.util.List;

@Service
public class MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;
    // Create
    public Producto guardarEntidad(Marca to) {
        return marcaRepository.save(marca);
    }
    // Report
    public List<Producto> listarEntidad() {
        return marcaRepository.findAll();
    }
    // Update
    public Producto actualizarEntidad(Marca to) {
        return marcaRepository.save(to);
    }
    // Delete
    public void eliminarRegEntidad(Long idProducto) {
        marcaRepository.deleteById(idProducto);
    }
    // Buscar por ID
    public Producto buscarProducto(Long idProducto) {
        return marcaRepository.findById(idProducto).orElse(null);
    }
