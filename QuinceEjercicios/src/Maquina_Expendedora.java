import java.util.*;

public class Maquina_Expendedora {
        static Map<Integer, Producto> productos = new HashMap<>() {{
            put(1, new Producto("Agua", 100));
            put(2, new Producto("Refresco", 150));
            put(3, new Producto("Chocolate", 200));
            put(4, new Producto("Chicles", 50));
        }};
        static final int[] MONEDAS_VALIDAS = {200, 100, 50, 10, 5};

        public static Resultado comprarProducto(int[] monedasIngresadas, int seleccionProducto) {
            int totalIngresado = 0;
            for (int moneda : monedasIngresadas) {
                if (!esMonedaValida(moneda)) {
                    return new Resultado("Moneda inválida detectada: " + moneda + " céntimos", monedasIngresadas);
                }
                totalIngresado += moneda;
            }

            Producto producto = productos.get(seleccionProducto);
            if (producto == null) {
                return new Resultado("Producto no existente", monedasIngresadas);
            }

            if (totalIngresado < producto.precio) {
                return new Resultado("Dinero insuficiente", monedasIngresadas);
            }

            int cambio = totalIngresado - producto.precio;
            int[] cambioMonedas = calcularCambio(cambio);

            return new Resultado(producto.nombre, cambioMonedas);
        }

        private static boolean esMonedaValida(int moneda) {
            for (int m : MONEDAS_VALIDAS) {
                if (m == moneda) return true;
            }
            return false;
        }

        private static int[] calcularCambio(int cambio) {
            List<Integer> monedasCambio = new ArrayList<>();
            for (int moneda : MONEDAS_VALIDAS) {
                while (cambio >= moneda) {
                    monedasCambio.add(moneda);
                    cambio -= moneda;
                }
            }
            return monedasCambio.stream().mapToInt(i -> i).toArray();
        }

        static class Producto {
            String nombre;
            int precio;

            Producto(String nombre, int precio) {
                this.nombre = nombre;
                this.precio = precio;
            }
        }

        static class Resultado {
            String mensaje;
            int[] cambio;

            Resultado(String mensaje, int[] cambio) {
                this.mensaje = mensaje;
                this.cambio = cambio;
            }

            void imprimir() {
                System.out.println("Resultado: " + mensaje);
                if (cambio.length > 0) {
                    System.out.println("Cambio devuelto: " + Arrays.toString(cambio));
                } else {
                    System.out.println("No hay cambio.");
                }
            }
        }

        public static void main(String[] args) {
            int[] monedas = {100, 50};
            int seleccion = 1;

            Resultado resultado = comprarProducto(monedas, seleccion);
            resultado.imprimir();
        }
    }
