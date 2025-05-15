public class Tres_Raya {

    public static String analizarTablero(String[][] tablero) {
        if (tablero.length != 3 || tablero[0].length != 3) {
            return "Nulo";
        }
        int conteoX = 0;
        int conteoO = 0;

        boolean ganaX = false;
        boolean ganaO = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String celda = tablero[i][j];
                if ("X".equals(celda)) conteoX++;
                else if ("O".equals(celda)) conteoO++;
                else if (!"".equals(celda)) return "Nulo";
            }
        }

        if (conteoX < conteoO || conteoX - conteoO > 1) {
            return "Nulo";
        }

        for (int i = 0; i < 3; i++) {
            if (tablero[i][0].equals(tablero[i][1]) && tablero[i][1].equals(tablero[i][2])) {
                if ("X".equals(tablero[i][0])) ganaX = true;
                if ("O".equals(tablero[i][0])) ganaO = true;
            }

            if (tablero[0][i].equals(tablero[1][i]) && tablero[1][i].equals(tablero[2][i])) {
                if ("X".equals(tablero[0][i])) ganaX = true;
                if ("O".equals(tablero[0][i])) ganaO = true;
            }
        }

        if (tablero[0][0].equals(tablero[1][1]) && tablero[1][1].equals(tablero[2][2])) {
            if ("X".equals(tablero[0][0])) ganaX = true;
            if ("O".equals(tablero[0][0])) ganaO = true;
        }

        if (tablero[0][2].equals(tablero[1][1]) && tablero[1][1].equals(tablero[2][0])) {
            if ("X".equals(tablero[0][2])) ganaX = true;
            if ("O".equals(tablero[0][2])) ganaO = true;
        }
        if (ganaX && ganaO) return "Nulo";
        if (ganaX) return "X";
        if (ganaO) return "O";

        if (conteoX + conteoO < 9) return "Empate";
        return "Empate";
    }

    public static void main(String[] args) {
        String[][] tablero = {
                {"O", "O", "X"},
                {"X", "O", "O"},
                {"X", "X", "O"}
        };
        // no funciona

        String resultado = analizarTablero(tablero);
        System.out.println("Resultado: " + resultado);
    }
}
