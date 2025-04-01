package upeu.enums;

enum GENERO{masculino,femenino};
public class Persona {

    enum ESTADO_CIVIl{casado,divorciado,soltero,viudo}

    String nombre;
    int edad;
    GENERO sexo=GENERO.masculino;
    String estadoCivil=String.valueOf(ESTADO_CIVIl.soltero);

    public static void main(String[] args) {
        Persona P = new Persona();
        P.nombre = "BOB";
        P.edad = 19;
        P.estadoCivil = String.valueOf(ESTADO_CIVIl.divorciado);

        System.out.println("Nombre: " + P.nombre+"\nEdad: " + P.edad + "\nEstado Civil: " + P.estadoCivil + "\nSexo: " + P.sexo);

        for (ESTADO_CIVIl e: ESTADO_CIVIl.values()) {
            System.out.println(e);
        }
    }

}
