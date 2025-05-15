import java.util.*;

public class WarCarft {
    static final Map<String, Integer> valoresBien = Map.of(
            "pelosos", 1,
            "sureños_buenos", 2,
            "enanos", 3,
            "numenoreanos", 4,
            "elfos", 5
    );
    static final Map<String, Integer> valoresMal = Map.of(
            "sureños_malos", 2,
            "orcos", 2,
            "goblins", 2,
            "huargos", 3,
            "trolls", 5
    );

    public static String resultadoBatalla(Map<String, Integer> ejercitoBien, Map<String, Integer> ejercitoMal) {
        int poderBien = calcularPoder(ejercitoBien, valoresBien);
        int poderMal = calcularPoder(ejercitoMal, valoresMal);

        if (poderBien > poderMal) return "El Bien triunfa";
        else if (poderMal > poderBien) return "El Mal conquista";
        else return "Empate sangriento";
    }

    private static int calcularPoder(Map<String, Integer> ejercito, Map<String, Integer> valores) {
        int total = 0;
        for (Map.Entry<String, Integer> entrada : ejercito.entrySet()) {
            String raza = entrada.getKey().toLowerCase();
            int cantidad = entrada.getValue();
            int valor = valores.getOrDefault(raza, 0);
            total += valor * cantidad;
        }
        return total;
    }

    public static void main(String[] args) {
        Map<String, Integer> ejercitoBien = new HashMap<>();
        ejercitoBien.put("pelosos", 3);

        Map<String, Integer> ejercitoMal = new HashMap<>();
        ejercitoMal.put("orcos", 1);

        String resultado = resultadoBatalla(ejercitoBien, ejercitoMal);
        System.out.println("Resultado de la batalla: " + resultado);
    }
}
