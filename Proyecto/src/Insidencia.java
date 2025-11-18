
import java.time.LocalDate;


public class Insidencia {
    
    private int id;
    private LocalDate fechaReporte;
    private String mensaje;
    private Practiicante practicante;
    private String estado ;

    public Insidencia() {
    }

    public Insidencia(int id, LocalDate fechaReporte, String mensaje, Practiicante practicante) {
        this.id = id;
        this.fechaReporte = fechaReporte;
        this.mensaje = mensaje;
        this.practicante = practicante;
        this.estado = "pendiente";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(LocalDate fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Practiicante getPracticante() {
        return practicante;
    }

    public void setPracticante(Practiicante practicante) {
        this.practicante = practicante;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Insidencia{" + "id=" + id + ", fechaReporte=" + fechaReporte + ", mensaje=" + mensaje + ", practicante=" + practicante + ", estado=" + estado + '}';
    }
    
    
}
