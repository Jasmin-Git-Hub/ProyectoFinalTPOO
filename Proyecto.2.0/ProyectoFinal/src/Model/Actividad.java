
package Model;
import java.time.LocalDate;
public class Actividad {
    private int id;
    private LocalDate fecha;
    private String descripcion;
    private Profesor maestro;

    public Actividad() {
    }

    public Actividad(int id, LocalDate fecha, String descripcion, Profesor maestro) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.maestro = maestro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Profesor getMaestro() {
        return maestro;
    }

    public void setMaestro(Profesor maestro) {
        this.maestro = maestro;
    }

    @Override
    public String toString() {
        return "Actividad{" + "id=" + id + ", fecha=" + fecha + ", descripcion=" + descripcion + ", maestro=" + maestro + '}';
    }
    
}
