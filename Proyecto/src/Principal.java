
import java.time.LocalDate;
import java.util.ArrayList;

public class Principal {

    public static void main(String[] args) {
        Controller controller = new Controller();
        Supervisor s1 = new Supervisor(1, "Mateo", "Castillo", "84293402", "Salud");
        Supervisor s2 = new Supervisor(2, "Laura", "Mercedez", "23814405", "Salud");
        Practiicante p1 = new Practiicante(123, "Luis", "Pretell", "85342104", "Farmacia", "Salud", s1);
        Practiicante p2 = new Practiicante(0121, "Sofia ", "Cespedes", "92852403", "Enfermería", "Salud", s2);
        controller.agregarPracticante(p1);
        controller.agregarPracticante(p2);
        controller.registrarAsistencia(new Asistencia(1, LocalDate.of(2025, 11, 10),"08:00", "13:00", "Presente", p1));
        controller.registrarAsistencia(new Asistencia(1, LocalDate.of(2025, 11, 10), "08:00", "13:30", "Tarde", p2));
        controller.registrarAsistencia(new Asistencia(1, LocalDate.of(2025, 11, 10),"00:00", "00:00", "Falto", p1));
        
        System.out.println("\n--- Buscar Practicante ---");
        Practiicante buscado = controller.buscarPracticante("85342104");
        if (buscado != null) {
            System.out.println("Encontrado:" + buscado.getNombre() + " " + buscado.getApellido() + " (" + buscado.getCarrera() + ") " );
        }
        System.out.println("\n--- Asistencias del 10/11/2025 ---");
        ArrayList<Asistencia> lista = controller.mostrarAsistenciaPorfecha(LocalDate.of(2025, 11, 10));
        for (Asistencia a : lista) {
            System.out.println("DNI: " + a.getPracticante().getDni() + " | Estado: " + a.getEstado());
        }
    
        System.out.println("\n--- Reporte General de Asistencia ---");
        controller.obtenerReporte();
    }
}
