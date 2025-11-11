
public class Practiicante extends Persona{
    
    private String carrera;
    private String areaAsignada;
    private Supervisor supervisor;

    public Practiicante() {
    }

public Practiicante(int id, String nombre, String apellido, String dni,String carrera, String areaAsignada, Supervisor supervisor) {
        super(id, nombre, apellido, dni);
        this.carrera = carrera;
        this.areaAsignada = areaAsignada;
        this.supervisor = supervisor;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getAreaAsignada() {
        return areaAsignada;
    }

    public void setAreaAsignada(String areaAsignada) {
        this.areaAsignada = areaAsignada;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public String toString() {
        return "Practiicante{" + "carrera=" + carrera + ", areaAsignada=" + areaAsignada + ", supervisor=" + supervisor + '}';
    }
    
    
}
