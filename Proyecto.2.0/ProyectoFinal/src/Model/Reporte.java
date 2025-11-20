
package Model;
public class Reporte {
    private int codigo;
    private int fechaGeneracion;
    private String tipoReporte;
    private String contenido;
    private int idPracticante;

    public Reporte() {
    }

    public Reporte(int codigo, int fechaGeneracion, String tipoReporte, String contenido, int idPracticante) {
        this.codigo = codigo;
        this.fechaGeneracion = fechaGeneracion;
        this.tipoReporte = tipoReporte;
        this.contenido = contenido;
        this.idPracticante = idPracticante;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(int fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getIdPracticante() {
        return idPracticante;
    }

    public void setIdPracticante(int idPracticante) {
        this.idPracticante = idPracticante;
    }

    @Override
    public String toString() {
        return "Reporte{" + "codigo=" + codigo + ", fechaGeneracion=" + fechaGeneracion + ", tipoReporte=" + tipoReporte + ", contenido=" + contenido + ", idPracticante=" + idPracticante + '}';
    }
    
    
}
