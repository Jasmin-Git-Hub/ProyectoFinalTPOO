
public class Secretaria extends Persona{
    private String cargo ;
    private String sueldo;
    private String email;
    private String telefono;
    private String contraseña;

    public Secretaria() {
    }

    public Secretaria(int id, String nombre, String apellido, String dni,String cargo, String sueldo, String email, String telefono,String contraseña ) {
        super(id, nombre, apellido, dni);
        this.cargo = cargo;
        this.sueldo = sueldo;
        this.email = email;
        this.telefono = telefono;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
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
    public String getContraseña (){
            return contraseña;  
        }
    public void serContraseña(String contrseña ){
            this.contraseña=contraseña;
        }
        
    @Override
    public String toString() {
        return super.toString()+"Secretaria{" + "cargo=" + cargo + ", sueldo=" + sueldo + ", email=" + email + ", telefono=" + telefono + "cotraseña"+contraseña+'}';
    }
    
    
}


