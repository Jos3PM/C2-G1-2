public class Pokemon {

    public static double calcularDaño(String tipoAtacante, String tipoDefensor, int ataque, int defensa) {
        if (ataque < 1 || ataque > 100 || defensa < 1 || defensa > 100) {
            throw new IllegalArgumentException("Ataque y defensa 1 y 100.");
        }

        double efectividad = obtenerEfectividad(tipoAtacante.toLowerCase(), tipoDefensor.toLowerCase());
        double daño = 50 * ((double) ataque / defensa) * efectividad;

        return daño;
    }

    private static double obtenerEfectividad(String atacante, String defensor) {
        return switch (atacante) {
            case "agua" -> switch (defensor) {
                case "fuego" -> 2.0;
                case "planta" -> 0.5;
                default -> 1.0;
            };
            case "fuego" -> switch (defensor) {
                case "planta" -> 2.0;
                case "agua" -> 0.5;
                default -> 1.0;
            };
            case "planta" -> switch (defensor) {
                case "agua" -> 2.0;
                case "fuego" -> 0.5;
                default -> 1.0;
            };
            case "eléctrico" -> switch (defensor) {
                case "agua" -> 2.0;
                case "planta" -> 0.5;
                default -> 1.0;
            };
            default -> 1.0;
        };
    }

    public static void main(String[] args) {
        String atacante = "Eléctrico";
        String defensor = "Tierra";
        int ataque = 80;
        int defensa = 60;

        try {
            double daño = calcularDaño(atacante, defensor, ataque, defensa);
            System.out.printf("Daño causado por %s a %s: %.2f%n", atacante, defensor, daño);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
