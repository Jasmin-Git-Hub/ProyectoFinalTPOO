
import java.time.LocalDate;

public class Principal {

    public static void main(String[] args) {
        Controller controller = new Controller();
        Supervisor s1 = new Supervisor(1, "Mateo Castillo", "84293402", "Salud");
        Practiicante p1 = new Practiicante("B001", "Luis Pretell", "85342104", "Farmacia", "Salud", s1);
        Supervisor s2 = new Supervisor(2, "Laura Mercedez", "23814405", "Salud");
        Practiicante p2 = new Practiicante("B002", "Sofia Cespedes", "92852403", "Enfermería", "Salud", s2);
        controller.agregarPracticante(p1);
        controller.agregarPracticante(p2);
        controller.registrarAsistencia(new Asistencia("12345678", LocalDate.of(2025, 11, 10), "Presente"));
        controller.registrarAsistencia(new Asistencia("87654321", LocalDate.of(2025, 11, 10), "Tarde"));
        controller.registrarAsistencia(new Asistencia("12345678", LocalDate.of(2025, 11, 11), "Falta"));
        
        System.out.println("\n--- Buscar Practicante ---");
        Practiicante buscado = controller.buscarPracticante("85342104");
        if (buscado != null) {
            System.out.println("Encontrado: " + buscado.getNombre() + " " + buscado.getApellido() + " (" + buscado.getCarrera() + ")");
        }
    System.out.println("\n--- Asistencias del 10/11/2025 ---");
        List<Asistencia> lista = controlador.mostrarAsistenciasPorFecha(LocalDate.of(2025, 11, 10));
        for (Asistencia a : lista) {
            System.out.println("DNI: " + a.getDniPracticante() + " | Estado: " + a.getEstado());
        }
    }
System.out.println("\n--- Reporte General de Asistencia ---");
        controlador.obtenerReporteAsistencia();
    
}
