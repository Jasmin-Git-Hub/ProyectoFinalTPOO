
    public class Supervisor  extends Persona{

        private String area ;
        private String contraseña;

        public Supervisor() {
        }

        public Supervisor( int id, String nombre, String apellido, String dni,String area) {
            super(id, nombre, apellido, dni);
            this.area = area;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }
        public String getContraseña (){
            return contraseña;  
        }
        public void setContraseña(String contraseña ){
            this.contraseña=contraseña;
        }
        

        @Override
        public String toString() {
            return super.toString()+ "Supervisor{" + "area=" + area +"contraseña"+contraseña+ '}';
        }
        // se hicieron cambios 



    }
