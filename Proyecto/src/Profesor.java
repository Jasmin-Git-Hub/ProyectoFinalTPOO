public class Profesor extends Persona{ 
    private String areaAsignada;
    private String email;
    private String telefono;
    private String contraseña;

    public Profesor() {
    }

    public Profesor(int id, String nombre, String apellido, String dni, String areaAsignada, String email, String telefono, String contraseña) {
        super(id, nombre, apellido, dni);
        this.areaAsignada = areaAsignada;
        this.email = email;
        this.telefono = telefono;
        this.contraseña = contraseña;
    }

    public String getAreaAsignada() {
        return areaAsignada;
    }

    public void setAreaAsignada(String areaAsignada) {
        this.areaAsignada = areaAsignada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Profesor{" + "areaAsignada=" + areaAsignada + ", email=" + email + ", telefono=" + telefono + ", contrase\u00f1a=" + contraseña + '}';
    }
    
    
}
