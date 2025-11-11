
import java.util.ArrayList;


public class Controller  {
    private ArrayList<Practiicante> practicantes = new ArrayList(); 
    private ArrayList<Asistencia> asistencia=new ArrayList();
   public void agregarPracticante(Practiicante p){ 
       practicantes.add(p); 
   }
   public void registrarAsistencia(Asistencia a){
       asistencia.add(a);
   }
   public Practiicante buscarPracticante(String dni){
       for (Practiicante p : practicantes) {
           if (p.getDni().equals(dni)) {
               return p;
           }
       } return null;
   }
   public void mostrarAsistenciaPorfecha(){
       
   }
   public void obtenerReporte(){
       
   }   
}
