
import java.util.ArrayList;


public class Practiicante extends Persona{
    
    private String carrera;
    private String areaAsignada;
    private Supervisor supervisor;
    private String email;
    private String contraseña;
    private boolean activo; 
    private ArrayList<Actividad> actividad; 

    public Practiicante() {
    }

    public Practiicante(int id, String nombre, String apellido, String dni, String carrera, String areaAsignada, Supervisor supervisor, String email, String contraseña, boolean activo) {
        super(id, nombre, apellido, dni);
        this.carrera = carrera;
        this.areaAsignada = areaAsignada;
        this.supervisor = supervisor;
        this.email = email;
        this.contraseña = contraseña;
        this.activo = true;
        this.actividad= new ArrayList<>(); 
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public ArrayList<Actividad> getRegistro() {
        return actividad;
    }

    public void setRegistro(ArrayList<Actividad> registro) {
        this.actividad = registro;
    }

    @Override
    public String toString() {
        return "Practiicante{" + "carrera=" + carrera + ", areaAsignada=" + areaAsignada + ", supervisor=" + supervisor + ", email=" + email + ", contraseña=" + contraseña + ", activo=" + activo + "actividad"+actividad.size()+'}';
    } 
    public void agregarActividad(Actividad act)
    {
       this.actividad.add(act);
    }
}
