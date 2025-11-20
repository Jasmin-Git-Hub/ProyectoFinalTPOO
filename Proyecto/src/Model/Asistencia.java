
import java.time.LocalDate;
import java.util.Date;

public class Asistencia {
    private int id ;
    private LocalDate fecha;
    private String horaEntrada;
    private String horaSalida;
    private String estado;
    private Practiicante practicante;

    public Asistencia() {
    }

    public Asistencia(int id, LocalDate fecha, String horaEntrada, String horaSalida, String estado, Practiicante practicante) {
        this.id = id;
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.estado = estado;
        this.practicante = practicante;
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

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Practiicante getPracticante() {
        return practicante;
    }

    public void setPracticante(Practiicante practicante) {
        this.practicante = practicante;
    }

    @Override
    public String toString() {
        return "Asistencia{" + "id=" + id + ", fecha=" + fecha + ", horaEntrada=" + horaEntrada + ", horaSalida=" + horaSalida + ", estado=" + estado + ", practicante=" + practicante + '}';
    }
    
    
}
