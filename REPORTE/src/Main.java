import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    Persona[] personas;
    Scanner sc = new Scanner(System.in);

    List<Persona> listaPersonas = new ArrayList<>();



    public void registrarAsistencia() {
        System.out.println("Ingrese la cantidad de estudiantes ");
        int cantidad = sc.nextInt();
        personas = new Persona[cantidad];
        for (int i = 0; i < personas.length; i++) {
            Persona personaX = new Persona();
            System.out.println("Ingrese el nombre del persona");
            personaX.nombre = sc.next();
            System.out.println("Ingrese el estado del persona (P=presente, F=falta)");
            personaX.estado = sc.next();
            personas[i] = personaX;
        }
        System.out.println("Reporte de asistencia:");
        for (int i = 0; i < personas.length; i++) {
            System.out.println(personas[i].nombre + " " + personas[i].estado);
        }
    }

    public void registrarAsisTeciaList(){
        String existeP = "si";
        while(existeP.equals("si")){
            System.out.println("Ingrese el Nombre del Persona ");
            String nombre = sc.nextLine();
            System.out.println("Ingrese el Estado del Persona ");
            String estado = sc.nextLine();
            Persona persona = new Persona();
            persona.setNombre(nombre);
            persona.setEstado(estado);
            listaPersonas.add(persona);
            System.out.println("Existe mas estudiantes matriculados (SI/NO)");
            existeP = sc.nextLine().toUpperCase();
        }

        for(Persona persona : listaPersonas){
            System.out.println(persona.getNombre() + " " + persona.getEstado());
        }

        System.out.println("Ingrese el nombre del Persona :");
        String nombre = sc.nextLine();
        for(Persona persona : listaPersonas){
            if(persona.getNombre().equals(nombre)){
                persona.setEstado("j");
            }
            listaPersonas.set(listaPersonas.indexOf(persona), persona);
        }

        for(Persona persona : listaPersonas){
            System.out.println(persona.getNombre() + " " + persona.getEstado());
        }
    }
    public void listarAsistenia(){
        System.out.println("listar Asistencia: ");
        for (Persona persona : listaPersonas) {
            System.out.println();
        };
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.registrarAsistencia();
    }


}