
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
   public void agregarPracticante(Practiicante p){ 
       practicantes.add(p); 
   }
   public String registrarAsistencia(Practiicante p, String estado){
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
   
   public Persona iniciarSesion(String email, String contraseña){
       for (Practiicante prac1 : practicantes) {
           if (prac1.getEmail().equals(email) && prac1.getContraseña().equals(contraseña) ) {
               return prac1;
           }
       } 
       for (Administrador adm1 : administrador) {
           if (adm1.getEmail().equals(email) && adm1.getContraseña().equals(contraseña) ) {
               return adm1;
           }
       }
       for (Secretaria sec1 : secretaria) {
           if (sec1.getEmail().equals(email) && sec1.getContraseña().equals(contraseña) ) {
               return sec1;
           }
       }
       for (Profesor prof1 : profesor) {
           if (prof1.getEmail().equals(email) && prof1.getContraseña().equals(contraseña) ) {
               return prof1;
           }
       } 
       return null;
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
   
   public void marcarAusencia(){
       LocalDate hoy = LocalDate.now();
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
           }
       }
       
   
   }
   
}
