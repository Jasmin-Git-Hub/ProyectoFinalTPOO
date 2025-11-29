/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.time.LocalDate;


public class Incidencia {
    
    private int id;
    private LocalDate fechaReporte;
    private String mensaje;
    private Practicante practicante;
    private String estado ;

    public Incidencia() {
    }

    public Incidencia(int id, LocalDate fechaReporte, String mensaje, Practicante practicante) {
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

    public Practicante getPracticante() {
        return practicante;
    }

    public void setPracticante(Practicante practicante) {
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
