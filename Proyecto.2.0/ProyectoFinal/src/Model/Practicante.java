
package Model;

import java.util.ArrayList;


public class Practicante extends Persona{
    
    private String carrera;
    private String areaAsignada;
    private Supervisor supervisor;
    private String email;
    private String contraseña;
    private boolean activo; 
    private ArrayList<Actividad> actividad; 
    private Profesor profesorAsignado;

    public Practicante() {
    }

    public Practicante(int id, String nombre, String apellido, String dni, String carrera, String areaAsignada, String email, String contraseña) {
        super(id, nombre, apellido, dni);
        this.carrera = carrera;
        this.areaAsignada = areaAsignada;
        this.email = email;
        this.contraseña = contraseña;
        this.activo = true ;
        this.actividad = new ArrayList<>();
        
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

    public ArrayList<Actividad> getActividad() {
        return actividad;
    }

    public void setActividad(ArrayList<Actividad> actividad) {
        this.actividad = actividad;
    }

    public Profesor getProfesorAsignado() {
        return profesorAsignado;
    }

    public void setProfesorAsignado(Profesor profesorAsignado) {
        this.profesorAsignado = profesorAsignado;
    }
    public void agregarActividad(Actividad act1){
        this.actividad.add(act1); 
    }
     
}
