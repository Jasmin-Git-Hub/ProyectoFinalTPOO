
import java.time.LocalDate;
import java.util.ArrayList;


public class Controller  {
    private ArrayList<Practiicante> practicantes = new ArrayList(); 
    private ArrayList<Asistencia> asistencias=new ArrayList();
   public void agregarPracticante(Practiicante p){ 
       practicantes.add(p); 
   }
   public void registrarAsistencia(Asistencia a){
       asistencias.add(a);
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
}
