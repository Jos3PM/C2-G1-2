package upeu.encapsulamiento;

public class Principal {
    public static void main(String[] args) {
        Animal a;
        a=new loro();
        a.comer(); a.emitirsonido(); a.dormir();
        System.out.println(a.peso());

    }
}
