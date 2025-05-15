import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Fecha {

    public static int diasEntreFechas(String fecha1, String fecha2) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalDate f1 = LocalDate.parse(fecha1, formato);
            LocalDate f2 = LocalDate.parse(fecha2, formato);
            long dias = ChronoUnit.DAYS.between(f1, f2);
            return (int) Math.abs(dias);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("'dd/mm/yyyy'", e);
        }
    }

    public static void main(String[] args) {
        String fecha1 = "15/05/2023";
        String fecha2 = "10/05/2025";

        try {
            int dias = diasEntreFechas(fecha1, fecha2);
            System.out.println("Diferencia en días: " + dias);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
