package Model;

import Controller.Controller;
import View.VistaUsuario;

public class Principal {

    public static void main(String[] args) {

        // Cargar el controlador
        Controller controladora = new Controller();

        // Abrir la vista principal donde se elige: Administrativo o Practicante
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaUsuario().setVisible(true);
            }
        });
    }
}

//public class Principal {
//        
    //public static void main(String[] args) {
//        Controller controladora = new Controller();
//        Supervisor s1=new Supervisor (1,"Mateo","Castillo","84293402","Salud");
//        Profesor p2=new Profesor(10, "Carlos","Peréz", "1234567", "Informatica", "Carlos@gmail.com", "976815321");
//        
//        
       
        
//        Practicante prac1=new Practicante(123, "Luis", "Pretell", "85342104", "Farmacia", "Salud", "Luis@gmail.com", "Maykol123");
//        Practicante prac2=new Practicante(456, "Benjamin", "Saldaña", "60039775","Enfermero", "Salud",  "Benjamin@gmail.com", "Benja123@.");
//        Practicante prac3=new Practicante(789, "Anderson", "Quispe", "74916640","Farmacia", "Salud",  "Anderson@gmail.com", "Anderxd123");
//        
//        controladora.agregarPracticante(123, "Luis", "Pretell", "85342104", "Farmacia", "Salud", "Luis@gmail.com", "Maykol123");
//        controladora.agregarPracticante(456, "Benjamin", "Saldaña", "60039775","Enfermero", "Salud",  "Benjamin@gmail.com", "Benja123@.");
//        controladora.agregarPracticante(789, "Anderson", "Quispe", "74916640","Farmacia", "Salud",  "Anderson@gmail.com", "Anderxd123");
//        System.out.println("Asistencia de los practicantes:");
//        System.out.println(controladora.registrarAsistencia(prac1, "Presente"));
//        System.out.println(controladora.registrarAsistencia(prac2, "Tarde"));
//        System.out.println("Prueba de duplicados");
//        System.out.println(controladora.registrarAsistencia(prac1, "Presente"));
//        System.out.println("Marcar ausencias");
//        controladora.marcarAusencia();
//        System.out.println("Se marcaron las ausencias");
//        ArrayList<Asistencia> asistenciasHoy=controladora.mostrarAsistenciaPorfecha(LocalDate.now());
//        for (Asistencia a : asistenciasHoy) {
//            System.out.println("Practicante:"+a.getPracticante().getNombre()+"Estado" +a.getEstado());
//            
//        }
//        System.out.println("Prueba de supervision");
//        controladora.registrarInformacion(prac1, p1, "El practicante es activo");
//        controladora.registrarInformacion(prac1, p1, "Requiere mejorar en reportes");
//        System.out.println("Historial de"+prac1.getNombre()+":");
//        ArrayList<Actividad>historialLuis=controladora.obtenerHistorial(prac1);
//        for (Actividad h1 : historialLuis) {
//            System.out.println(" - "+h1.getFecha()+":"+h1.getDescripcion()+"(Autor:"+h1.getMaestro().getNombre()+" )");
//            
//        }
//        System.out.println("Desactivar practicante");
//        System.out.println("Estado de Benjamin antes:"+controladora.buscarPracticante("60039775").isActivo());
//        controladora.desactivarPracticante("60039775");
//        System.out.println("Estado de Benjamin antes:"+controladora.buscarPracticante("60039775").isActivo());
//                
        
        
    //}
//}

