package upeu.encapsulamiento;

public class loro implements Animal{
    @Override
    public void comer() {
        System.out.println("Comiendo");
    }
    @Override
    public void dormir() {
        System.out.println("zzz");
    }
    @Override
    public void emitirsonido() {
        System.out.println("piopio");
    }
    @Override
    public String peso() {
        return "10kg";
    }
}
