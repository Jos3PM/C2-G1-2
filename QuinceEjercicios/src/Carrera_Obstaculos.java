public class Carrera_Obstaculos {

    public static boolean evaluarCarrera(String[] acciones, String pista) {
        if (acciones.length != pista.length()) {
            throw new IllegalArgumentException();
        }

        StringBuilder resultado = new StringBuilder();
        boolean carreraCorrecta = true;

        for (int i = 0; i < pista.length(); i++) {
            String accion = acciones[i];
            char obstaculo = pista.charAt(i);

            if (obstaculo == '_' && accion.equals("run")) {
                resultado.append("_");
            } else if (obstaculo == '|' && accion.equals("jump")) {
                resultado.append("|");
            } else if (obstaculo == '_' && accion.equals("jump")) {
                resultado.append("x");
                carreraCorrecta = false;
            } else if (obstaculo == '|' && accion.equals("run")) {
                resultado.append("/");
                carreraCorrecta = false;
            } else {
                throw new IllegalArgumentException("Acción inválida: " + accion);
            }
        }

        System.out.println("Pista original : " + pista);
        System.out.println("Acciones       : " + String.join(",", acciones));
        System.out.println("Pista evaluada : " + resultado);

        return carreraCorrecta;
    }

    public static void main(String[] args) {
        String[] acciones = {"jump", "jump", "run", "jump", "run"};
        String pista = "_|_|_";

        boolean resultado = evaluarCarrera(acciones, pista);
        System.out.println("Carrera superada:" + resultado);
    }
}
