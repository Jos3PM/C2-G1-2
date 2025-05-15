    import java.util.*;
    import java.time.*;

public class Calendario_Adviento {

    static final Map<Integer, String> regalos = new HashMap<>() {{
        put(1, "Curso de Git");
        put(2, "Ebook sobre Clean Code");
        put(3, "Plantilla HTML para portafolio");
        put(4, "Guía de buenas prácticas Java");
        put(5, "Acceso gratuito a una API");
        put(6, "Curso de Python");
        put(7, "Sticker pack digital");
        put(8, "Licencia gratis de VS Code plugin");
        put(9, "Tip diario sobre testing");
        put(10, "Descuento para una suscripción dev");
        put(11, "Guía de diseño UI/UX");
        put(12, "Bot de Telegram para recordatorios");
        put(13, "Manual de patrones de diseño");
        put(14, "Entrenamiento de entrevistas técnicas");
        put(15, "Tema oscuro premium");
        put(16, "Template para CV dev");
        put(17, "Ebook sobre Docker");
        put(18, "Retos de algoritmia");
        put(19, "Curso breve de TypeScript");
        put(20, "Fondo de pantalla navideño dev");
        put(21, "Extensión de productividad");
        put(22, "Mini-juego navideño en JavaScript");
        put(23, "Trivia de ciencia y tecnología");
        put(24, "Sorpresa final de Navidad Dev");
    }};

    public static String analizarFecha(Date fecha) {
        ZoneId zona = ZoneId.systemDefault();
        LocalDateTime fechaInput = fecha.toInstant().atZone(zona).toLocalDateTime();

        LocalDateTime inicio = LocalDateTime.of(2022, 12, 1, 0, 0, 0);
        LocalDateTime fin = LocalDateTime.of(2022, 12, 24, 23, 59, 59);

        if (fechaInput.isBefore(inicio)) {
            Duration dur = Duration.between(fechaInput, inicio);
            return "Aún no ha comenzado el calendario. Faltan: " + formatearDuracion(dur);
        }

        if (fechaInput.isAfter(fin)) {
            Duration dur = Duration.between(fin, fechaInput);
            return "El calendario terminó. Tiempo desde el fin: " + formatearDuracion(dur);
        }

        int dia = fechaInput.getDayOfMonth();
        String regalo = regalos.getOrDefault(dia, "Regalo sorpresa");

        LocalDateTime finDelDia = LocalDateTime.of(2022, 12, dia, 23, 59, 59);
        Duration restante = Duration.between(fechaInput, finDelDia);

        return String.format("Día %d del calendario: %s%n Tiempo restante para que finalice el día: %s", dia, regalo, formatearDuracion(restante));
    }

    private static String formatearDuracion(Duration dur) {
        long horas = dur.toHours();
        long minutos = dur.toMinutesPart();
        long segundos = dur.toSecondsPart();
        return String.format("%02dh %02dm %02ds", horas, minutos, segundos);
    }

    public static void main(String[] args) {

        Calendar cal = Calendar.getInstance();
        cal.set(2022, Calendar.DECEMBER, 6, 15, 30, 0);
        Date fecha = cal.getTime();

        System.out.println(analizarFecha(fecha));
    }
}
