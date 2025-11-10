
    public class Supervisor  extends Persona{

        private String area ;

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

        @Override
        public String toString() {
            return "Supervisor{" + "area=" + area + '}';
        }



    }
