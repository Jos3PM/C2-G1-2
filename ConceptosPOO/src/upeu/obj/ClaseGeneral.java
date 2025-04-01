package upeu.obj;

import upeu.modelo.Persona;

public class ClaseGeneral {
    public static void main(String[] args) {
        Persona persona;
        persona = new Persona();
        persona.setNombre("Jose");
        persona.setEdad(17);
        persona.saludo();
    }


}
