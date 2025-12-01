
package Controller;

import Model.Actividad;
import Model.Administrador;
import Model.Asistencia;
import Model.Incidencia;
import Model.Persona;
import Model.Practicante;
import Model.Profesor;
import Model.Secretaria;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;



public class Controller  {
    private ArrayList<Practicante> practicantes = new ArrayList(); 
    private ArrayList<Asistencia> asistencias=new ArrayList();
    private ArrayList<Actividad> actividad=new ArrayList();
    private ArrayList<Incidencia> incidencia=new ArrayList();
    
    private ArrayList<Profesor> profesor=new ArrayList();
    private ArrayList<Administrador> administrador=new ArrayList();
    private ArrayList<Secretaria> secretaria=new ArrayList();
    private ArrayList<Practicante> practicante=new ArrayList();
     
    private Persona usuarioLogeado =null;
    public Controller(){
        profesor.add(new Profesor(1, "Carlos", "Estrada", "965874365", "Computación", "profe@gmail.com", "987654321", "12345"));
        administrador.add( new Administrador("admin@gmail", "958746854","admin","jefe",2, "admin","principal","954754975"));
        secretaria.add(new Secretaria(3, "Ana", "Martinez", "87872476", "Recepción", "1250", "ana@gmail.com", "987654324", "ana123")); 
        
        Practicante p1 = new Practicante(101, "Luis", "Pretell", "72752401", "Farmacia", "Salud", "Luis@gmail.com", "pass123"); 
        p1.setProfesorAsignado(profesor.get(0));
        practicantes.add(p1);
        
        Practicante p2=new Practicante (102, "Sofia", "cespedes", "92852403","enfermeria", "Salud", "sofia@gamil.com", "123");
        p2.setProfesorAsignado(profesor.get(0));
        practicantes.add(p2);
    }
  
   public String agregarPracticante(int id, String nombre, String ap, String dni, String carrera, String area, String email, String contraseña){ 
       if (!(usuarioLogeado instanceof Administrador)) return "ERROR: Sin permisos.";
       if (buscarPracticante(dni) != null) return "ERROR: Ya existee un practicante con ese DNI.";
       Practicante p1 = new Practicante(id, nombre, ap, dni, carrera, area, email, contraseña);
       practicantes.add(p1); 
       return "Practicante agregado.";
   }
   
   public String registrarAsistencia(String dniPracticante, String estado){
       if(!(usuarioLogeado instanceof Secretaria)&&!(usuarioLogeado instanceof Administrador)){
           return "ERROR: No tiene permisos para esta acción"; 
       }
       Practicante p = buscarPracticante(dniPracticante); 
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
   
   public Practicante buscarPracticante(String dni){
       for (Practicante p : practicantes) {
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
           Practicante p = buscarPracticante(a.getPracticante().getDni()); 
           if(p!=null){
               System.out.println("Practicante:" + p.getNombre() + "- Fecha:" + a.getFecha() + 
                       "- Estado"+ a.getEstado());
           }
       }
   }   
   
   public boolean iniciarSesion(String email, String contraseña){
        for (Practicante prac1 : practicantes) {
            if (prac1.getEmail().equals(email) && prac1.getContraseña().equals(contraseña)) {
                this.usuarioLogeado = prac1; 
                return true;
            }
        }

    // 2. Buscar en Administradores
        for (Administrador adm1 : administrador) {
            if (adm1.getEmail().equals(email) && adm1.getContraseña().equals(contraseña)) {
                this.usuarioLogeado = adm1; 
                return true;
            }
        }

        for (Secretaria sec1 : secretaria) {
            if (sec1.getEmail().equals(email) && sec1.getContraseña().equals(contraseña)) {
                this.usuarioLogeado = sec1; 
                return true;
            }
        }

        for (Profesor prof1 : profesor) {
            if (prof1.getEmail().equals(email) && prof1.getContraseña().equals(contraseña)) {
                this.usuarioLogeado = prof1; 
                return true;
            }
        }

    
        this.usuarioLogeado = null; 
        return false;
   }
   
   public void cerrarSesion(){
       this.usuarioLogeado = null; 
   }
   
   public Persona getUsuarioLogueado(){
       return this.usuarioLogeado; 
   }
   
   public String registrarActividad(String dniPracticante, String descripcion){
       
       // 1. CORRECCIÓN DE PERMISOS:
       // Permitimos entrar si es Profesor O SI ES Administrador
       if(!(usuarioLogeado instanceof Profesor) && !(usuarioLogeado instanceof Administrador)) {
           return "ERROR: No tiene permisos para registrar actividades."; 
       }
       
       Practicante p = buscarPracticante(dniPracticante); 
       if(p == null) return "ERROR: Practicante no encontrado"; 
       
       int id = actividad.size() + 1; 
       
       // 2. CORRECCIÓN DE AUTOR (Para evitar errores de conversión):
       Profesor autor;
       
       if (usuarioLogeado instanceof Profesor) {
           // Si quien entró es profe, lo usamos tal cual
           autor = (Profesor) usuarioLogeado;
       } else {
           // Si quien entró es ADMINISTRADOR, creamos un "Profesor Genérico" 
           // para que la clase Actividad no falle (ya que pide un objeto Profesor obligatoriamente)
           autor = new Profesor(id, descripcion, descripcion, descripcion, descripcion, descripcion, descripcion,descripcion);

       // 3. Crear y guardar la actividad
       Actividad act = new Actividad(id, LocalDate.now(), descripcion, autor); 
       actividad.add(act); 
       p.agregarActividad(act);
       
       return "Actividad registrada correctamente.";}
        return null;
       
   }
      
   public String modificarActividad(int idAct, String nuevoTexto){
       if(!(usuarioLogeado instanceof Profesor)) return "ERROR: Sin permisos"; 
       for (Actividad act : actividad) {
           if(act.getId()==idAct){
               if(act.getMaestro().equals(usuarioLogeado)){
                   act.setDescripcion(nuevoTexto);
                   return "Observación modificada"; 
               }
               else{
                   return "ERROR: No es el autor de esta actividad"; 
               }
           }
       }
       return "ERROR: Actividad no encontrada"; 
   }
   
   public String desactivarPracticante(String dni){
       if (!(usuarioLogeado instanceof Administrador)) return "ERROR: Sin permisos.";
        
        Practicante p = buscarPracticante(dni);
        if (p == null) return "ERROR: Practicante no encontrado.";
        
        p.setActivo(false);
        return "Practicante desactivado. Su historial se mantiene.";
   }
   
   public String marcarAusencia(){
       LocalDate hoy = LocalDate.now();
       int ausentesMarcados = 0;
       for (Practicante p1 : practicantes) {
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
   
   public String modificarPracticante(String dni, String nuevoNombre, String nuevaCarrera){
      if (!(usuarioLogeado instanceof Administrador)) return "ERROR: Sin permisos.";
        
        Practicante p = buscarPracticante(dni);
        if (p == null) return "ERROR: Practicante no encontrado.";
        
        p.setNombre(nuevoNombre);
        p.setCarrera(nuevaCarrera);
        return "Practicante modificado.";
   }
   
   public ArrayList<Practicante> practicantesActivos(){
       ArrayList<Practicante> activo =new ArrayList<>(); 
       for (Practicante p : practicantes) {
           if(p.isActivo()) activo.add(p); 
       }
       return activo; 
   }
   
   public ArrayList<Practicante> practicantesAsignados(){
       ArrayList<Practicante> asignado = new ArrayList<>();
       if (!(usuarioLogeado instanceof Profesor)) return asignado; 
       Profesor profLogueado = (Profesor) usuarioLogeado; 
       for (Practicante p : practicantes) {
           if(p.getProfesorAsignado()!=null&&p.getProfesorAsignado().equals(profLogueado)){
               asignado.add(p); 
           }
       }
       return  asignado; 
   }
   
   public ArrayList<Asistencia> historialAsistencia(String dni){
       ArrayList<Asistencia> historial = new ArrayList<>(); 
       for (Asistencia a : asistencias) {
           if (a.getPracticante().getDni().equals(dni)){
               historial.add(a); 
           }
       }
       Collections.sort(historial, (Asistencia a1, Asistencia a2) -> a2.getFecha().compareTo(a1.getFecha())); 
       return  historial;
   }
   
   public ArrayList <Actividad> historialActividad(String dni, LocalDate inicio, LocalDate fin){
       Practicante p = buscarPracticante(dni); 
       if (p==null) return new ArrayList<Actividad>(); 
       ArrayList<Actividad> historialFiltrado = new ArrayList<>(); 
       for (Actividad act : p.getActividad()) {
           if((inicio==null||!act.getFecha().isBefore(inicio))&&(fin==null||!act.getFecha().isAfter(fin))){
               historialFiltrado.add(act); 
           }
       }
       return historialFiltrado; 
   }
   
   public ArrayList<Asistencia> visualizarAsistencias(){
       if(!(usuarioLogeado instanceof Practicante)) return new ArrayList<Asistencia>();
       return historialAsistencia(usuarioLogeado.getDni()); 
   }
   
   public ArrayList<Actividad> visualizarActividades(){
       if(!(usuarioLogeado instanceof Practicante)) return new ArrayList<Actividad>(); 
       return historialActividad(usuarioLogeado.getDni(), null, null); 
   }
   
   public String reportarIncidencia(String mensaje){
       if(!(usuarioLogeado instanceof Practicante)) return "ERROR: Solo parcticantes pueden reportar."; 
       int id = incidencia.size()+1; 
       Incidencia inc = new Incidencia(id, LocalDate.now(), mensaje, (Practicante) usuarioLogeado); 
       incidencia.add(inc); 
       return "Incidencia reportada. ID: "+ id; 
   }
   
    public String modificarAsistencia(String dni, LocalDate fecha, String nuevoEstado) {
        if (!(usuarioLogeado instanceof Administrador) && !(usuarioLogeado instanceof Secretaria)) {
            return "ERROR: No tiene permisos para modificar asistencias.";
        }

        for (Asistencia a : asistencias) {
            if (a.getPracticante().getDni().equals(dni) && a.getFecha().equals(fecha)) {

                a.setEstado(nuevoEstado);
                return "Asistencia modificada correctamente.";
            }
        }

        return "ERROR: No se encontró asistencia para ese DNI en esa fecha.";
    }
}

