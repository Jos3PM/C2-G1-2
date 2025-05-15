public class Codigo_Fibonacci {
    public static void main(String[] args) {
        long a = 0, b = 1;
        int n = 50;

        for (int i = 0; i < n; i++) {
            System.out.print(a + (i < n - 1 ? ", " : "\n"));
            long siguiente = a + b;
            a = b;
            b = siguiente;
        }
    }
}