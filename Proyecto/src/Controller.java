
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class Controller  {
    private ArrayList<Practiicante> practicantes = new ArrayList(); 
    private ArrayList<Asistencia> asistencias=new ArrayList();
    private ArrayList<Actividad> actividad=new ArrayList();
    private ArrayList<Profesor> profesor=new ArrayList();
    private ArrayList<Administrador> administrador=new ArrayList();
    private ArrayList<Secretaria> secretaria=new ArrayList();
    private ArrayList<Practiicante> practicante=new ArrayList();
    private ArrayList<Insidencia> incidencia=new ArrayList();
     
    private Persona usuarioLogeado =null;
    public Controller(){
        profesor.add(new Profesor(1, "carlos", " Estrado","965874365", "computación","prof@gmail.com","123"));
        administrador.add( new Administrador("admin@gami", "958746854","admin","jefe",2, "admin","principal","954754975"));
        secretaria.add(new Secretaria(3, "Ana", "Martinez", "87872476", "Recepción", "1250", "ana@gmaill.com", "987654324", "ana123")); 
        
        Practiicante p1 = new Practiicante(101, "Luis", "Pretell", "584796548", "Farmacia", "Salud", "Luis@gmail.com", "pass123"); 
        p1.setProfesorAsignado(profesor.get(0));
        practicantes.add(p1);
        
        Practiicante p2=new Practiicante (102, "Sofia", "cespedes", "92852403","enfermeria", "Salud", "sofia@gamil.com", "123");
        p2.setProfesorAsignado(profesor.get(0));
        practicantes.add(p2);
    }
    
    
   public String agregarPracticante(int id, String nombre, String ap, String dni, String carrera, String area, String email, String contraseña){ 
       if (!(usuarioLogeado instanceof Administrador)) return "ERROR: Sin permisos.";
       if (buscarPracticante(dni) != null) return "ERROR: Ya existee un practicante con ese DNI.";
       Practiicante p1 = new Practiicante(id, nombre, ap, dni, carrera, area, email, contraseña);
       practicantes.add(p1); 
       return "Practicante agregado.";
       
   }
   public String registrarAsistencia(String dniPracticante, String estado){
       if(!(usuarioLogeado instanceof Secretaria)&&!(usuarioLogeado instanceof Administrador)){
           return "ERROR: No tiene permisos para esta acción"; 
       }
       Practiicante p = buscarPracticante(dniPracticante); 
       if(p==null) return "ERROR: Practicante no encontrado"; 
       if (!p.isActivo()) return "Error: El practicante está inactivo"; 
       LocalDate hoy = LocalDate.now();
       for (Asistencia a : asistencias) {
           if (a.getPracticante().equals(p) && a.getFecha().equals(hoy)) {
               return "Error, ya se registró una asistencia parra el practicante el día de hoy";  
           }
       } 
       int idAsistencia = asistencias.size()+1;
       String hora = LocalTime.now().toString();
       Asistencia  nuevaAsistencia = new Asistencia(idAsistencia, hoy, hora, "", estado, p);
       asistencias.add(nuevaAsistencia);
       return "Asistencia registrada conrrectamente.";
       
       
   }
   public Practiicante buscarPracticante(String dni){
       for (Practiicante p : practicantes) {
           if (p.getDni().equals(dni)) {
               return p;
           }
       } return null;
   }
   public ArrayList<Asistencia>mostrarAsistenciaPorfecha(LocalDate fecha){
       ArrayList<Asistencia>listaFiltrada=new ArrayList<>();
       for (Asistencia a : asistencias) {
          if (a.getFecha().equals(fecha)){
              listaFiltrada.add(a);
          }
       }
       return listaFiltrada;
   }
   public void obtenerReporte(){
       System.out.println("Reporte generado:");
       for (Asistencia a : asistencias) {
           Practiicante p = buscarPracticante(a.getPracticante().getDni()); 
           if(p!=null){
               System.out.println("Practicante:" + p.getNombre() + "- Fecha:" + a.getFecha() + 
                       "- Estado"+ a.getEstado());
           }
       }
   }   
   
   public boolean iniciarSesion(String email, String contraseña){
       for (Practiicante prac1 : practicantes) {
           if (prac1.getEmail().equals(email) && prac1.getContraseña().equals(contraseña) ) {
               return true;
           }
       } 
       for (Administrador adm1 : administrador) {
           if (adm1.getEmail().equals(email) && adm1.getContraseña().equals(contraseña) ) {
               return true;
           }
       }
       for (Secretaria sec1 : secretaria) {
           if (sec1.getEmail().equals(email) && sec1.getContraseña().equals(contraseña) ) {
               return true;
           }
       }
       for (Profesor prof1 : profesor) {
           if (prof1.getEmail().equals(email) && prof1.getContraseña().equals(contraseña) ) {
               return true;
           }
       } 
       return false;
   }
   public void cerrarSesion(){
       this.usuarioLogeado = null; 
   }
   public Persona getUsuarioLogueado(){
       return this.usuarioLogeado; 
   }
   public void registrarInformacion(Practiicante p, Profesor encargado, String descripcion){
       int act=actividad.size()+1;
       LocalDate hoy =LocalDate.now();
       Actividad act1 = new Actividad(act, hoy, descripcion, encargado);
       actividad.add(act1);
       p.agregarActividad(act1);
       
   }
   
   public ArrayList<Actividad>obtenerHistorial(Practiicante p){
       return p.getRegistro();
       
   
   }
   
   public boolean desactivarPracticante(String dni){
       Practiicante p = buscarPracticante(dni);
       if (p != null) {
           p.setActivo(false);
           return true;
       } return false;
   }
   
   public String marcarAusencia(){
       LocalDate hoy = LocalDate.now();
       int ausentesMarcados = 0;
       for (Practiicante p1 : practicantes) {
           if (!p1.isActivo()) {
               continue;
           }
           boolean tieneAsistencia = false;
           for (Asistencia a : asistencias) {
               if (a.getPracticante().equals(p1) && a.getFecha().equals(hoy)){
                   tieneAsistencia = true;
                   break;
               }
           }
           if (!tieneAsistencia) {
               int idAsistencia = asistencias.size()+1;
               Asistencia ausente = new Asistencia(idAsistencia, hoy, "00:00", "00:00", "Ausente", p1);
               asistencias.add(ausente);
               ausentesMarcados++;
           }
       } return "se marcaron" + ausentesMarcados + "Ausencias.";
       
   
   }
   
}
