
public class Administrador extends Persona {
    private String email;
    private String telefono;
    private String contraseña;
    private String cargo;

    public Administrador() {
    }

    public Administrador(String email, String telefono, String contraseña, String cargo, int id, String nombre, String apellido, String dni) {
        super(id, nombre, apellido, dni);
        this.email = email;
        this.telefono = telefono;
        this.contraseña = contraseña;
        this.cargo = cargo;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Administrador{" + "email=" + email + ", telefono=" + telefono + ", contrase\u00f1a=" + contraseña + ", cargo=" + cargo + '}';
    }
    
}
