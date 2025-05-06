package pe.edu.upeu.calfx.servicio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.calfx.modelo.CalcTO;
import pe.edu.upeu.calfx.repositorio.CalcRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalcServicioImp implements CalcServicioI {

    @Autowired
    private CalcRepository calcRepository;

    @Override
    public void save(CalcTO calcTO) {
        calcRepository.save(calcTO);
    }

    @Override
    public List<CalcTO> findAll() {
        return calcRepository.findAll();
    }

    @Override
    public CalcTO findById(Long index) {return calcRepository.findById(index).orElse(new CalcTO());}

    @Override
    public void update(CalcTO calcTO, Long index) {calcRepository.save(calcTO);}

    @Override
    public void delete(CalcTO calcTO) {calcRepository.delete(calcTO);}

    @Override
    public void deleteById(Long index) {calcRepository.deleteById(index);}
}