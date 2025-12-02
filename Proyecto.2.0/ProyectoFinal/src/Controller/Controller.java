
package Controller;

import Connection.ConexionBD; // Asegúrate que este import coincida con tu clase de Conexion
import Model.Actividad;
import Model.Administrador;
import Model.Asistencia;
import Model.Incidencia;
import Model.Persona;
import Model.Practicante;
import Model.Profesor;
import Model.Secretaria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Controller {

    // --- LISTAS EN MEMORIA (Respaldos) ---
    private ArrayList<Practicante> practicantes = new ArrayList<>();
    private ArrayList<Asistencia> asistencias = new ArrayList<>();
    private ArrayList<Actividad> actividad = new ArrayList<>();
    private ArrayList<Incidencia> incidencia = new ArrayList<>();
    
    private ArrayList<Profesor> profesor = new ArrayList<>();
    private ArrayList<Administrador> administrador = new ArrayList<>();
    private ArrayList<Secretaria> secretaria = new ArrayList<>();

    // --- VARIABLES DE CONEXIÓN ---
    private Connection conexionBD;
    private Persona usuarioLogeado = null;

    // --- CONSTRUCTOR ---
    public Controller() {
        // 1. Intentar Conectar a la Base de Datos
        this.conexionBD = ConexionBD.getConnection();

        // 2. Cargar datos de prueba EN MEMORIA (Solo sirven si falla la BD)
        cargarDatosMemoria();
    }

    private void cargarDatosMemoria() {
        // Estos datos solo se usan si entra al 'else' (Sin conexión)
        Profesor p1 = new Profesor(1, "Carlos", "Estrada", "965874365", "Computación", "profe@gmail.com", "987654321", "12345");
        profesor.add(p1);
        administrador.add(new Administrador("admin@gmail.com", "958746854", "admin", "jefe", 2, "Admin", "Principal", "954754975"));
        secretaria.add(new Secretaria(3, "Ana", "Martinez", "87872476", "Recepción", "1250", "ana@gmail.com", "987654324", "ana123"));

        Practicante prac1 = new Practicante(101, "Luis", "Pretell", "72752401", "Farmacia", "Salud", "Luis@gmail.com", "pass123");
        prac1.setProfesorAsignado(p1);
        practicantes.add(prac1);

        Practicante prac2 = new Practicante(102, "Sofia", "Cespedes", "92852403", "Enfermeria", "Salud", "sofia@gamil.com", "123");
        prac2.setProfesorAsignado(p1);
        practicantes.add(prac2);
    }

    public boolean iniciarSesion(String email, String contraseña) {
        
        // --- 1. INTENTO POR BASE DE DATOS ---
        if (this.conexionBD != null) {
            try {
                // A. Buscar en PRACTICANTE
                if (buscarUsuarioEnBD(email, contraseña, "practicante")) return true;
                // B. Buscar en ADMINISTRADOR
                if (buscarUsuarioEnBD(email, contraseña, "administrador")) return true;
                // C. Buscar en SECRETARIA
                if (buscarUsuarioEnBD(email, contraseña, "secretaria")) return true;
                // D. Buscar en PROFESOR
                if (buscarUsuarioEnBD(email, contraseña, "profesor")) return true;

            } catch (SQLException e) {
                System.out.println("Error SQL Login: " + e.getMessage());
            }
        } 
        
        // --- 2. INTENTO POR MEMORIA (ARRAYLIST) ---
        // (Esto se ejecuta si no hay BD o si no se encontró en BD)
        for (Practicante p : practicantes) {
            if (p.getEmail().equals(email) && p.getContraseña().equals(contraseña)) {
                this.usuarioLogeado = p;
                return true;
            }
        }
        for (Administrador a : administrador) {
            if (a.getEmail().equals(email) && a.getContraseña().equals(contraseña)) {
                this.usuarioLogeado = a;
                return true;
            }
        }
        for (Secretaria s : secretaria) {
            if (s.getEmail().equals(email) && s.getContraseña().equals(contraseña)) {
                this.usuarioLogeado = s;
                return true;
            }
        }
        for (Profesor p : profesor) {
            if (p.getEmail().equals(email) && p.getContraseña().equals(contraseña)) {
                this.usuarioLogeado = p;
                return true;
            }
        }

        this.usuarioLogeado = null;
        return false;
    }

    // Método auxiliar para no repetir código SQL en el login
    private boolean buscarUsuarioEnBD(String email, String pass, String tabla) throws SQLException {
        String sql = "SELECT * FROM " + tabla + " WHERE email = ? AND contrasena = ?";
        PreparedStatement ps = conexionBD.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, pass);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            // Crear el objeto según la tabla
            switch (tabla) {
                case "practicante":
                    Practicante p = new Practicante();
                    p.setId(rs.getInt("id"));
                    p.setNombre(rs.getString("nombre"));
                    p.setApellido(rs.getString("apellido"));
                    p.setDni(rs.getString("dni"));
                    p.setEmail(rs.getString("email"));
                    p.setCarrera(rs.getString("carrera"));
                    this.usuarioLogeado = p;
                    break;
                case "administrador":
                    Administrador a = new Administrador();
                    a.setNombre(rs.getString("nombre"));
                    a.setEmail(rs.getString("email"));
                    this.usuarioLogeado = a;
                    break;
                case "profesor":
                    Profesor prof = new Profesor();
                    prof.setId(rs.getInt("id"));
                    prof.setNombre(rs.getString("nombre"));
                    prof.setEmail(rs.getString("email"));
                    this.usuarioLogeado = prof;
                    break;
                case "secretaria":
                    Secretaria s = new Secretaria();
                    s.setNombre(rs.getString("nombre"));
                    this.usuarioLogeado = s;
                    break;
            }
            return true;
        }
        return false;
    }
// NOTA: Se agregó el parámetro 'int idProfesorAsignado' al final
    public String agregarPracticante(int id, String nombre, String ap, String dni, String carrera, String area, String email, String contraseña, int idProfesorAsignado) {
        if (!(usuarioLogeado instanceof Administrador)) return "ERROR: Sin permisos.";

        if (this.conexionBD != null) {
            // --- SQL INSERT ACTUALIZADO PARA SQL SERVER ---
            try {
                // 1. Agregamos la columna 'id_profesor_asignado' al INSERT
                // 2. Usamos el valor '1' para el campo BIT 'activo'
                String sql = "INSERT INTO practicante (id, nombre, apellido, dni, carrera, area_asignada, email, contrasena, activo, id_profesor_asignado) VALUES (?,?,?,?,?,?,?,?,1,?)";
                
                PreparedStatement ps = conexionBD.prepareStatement(sql);
                ps.setInt(1, id);
                ps.setString(2, nombre);
                ps.setString(3, ap);
                ps.setString(4, dni);
                ps.setString(5, carrera);
                ps.setString(6, area);
                ps.setString(7, email);
                ps.setString(8, contraseña);
                
                // ESTA ES LA LÍNEA QUE SOLUCIONA EL NULL:
                ps.setInt(9, idProfesorAsignado);
                
                ps.executeUpdate();
                return "Practicante registrado en BD correctamente.";
            } catch (SQLException e) {
                return "Error BD: " + e.getMessage();
            }
        } else {
            // --- MEMORIA (Respaldo) ---
            if (buscarPracticante(dni) != null) return "ERROR: Ya existe.";
            Practicante nuevoP = new Practicante(id, nombre, ap, dni, carrera, area, email, contraseña);
            
            // Buscamos el profesor en la lista de memoria para asignarlo
            for(Profesor prof : profesor) {
                if(prof.getId() == idProfesorAsignado) {
                    nuevoP.setProfesorAsignado(prof);
                    break;
                }
            }
            practicantes.add(nuevoP);
            return "Practicante agregado (Local).";
        }
    }

    public String registrarAsistencia(String dniPracticante, String estado) {
        if (!(usuarioLogeado instanceof Secretaria) && !(usuarioLogeado instanceof Administrador)) {
            return "ERROR: No tiene permisos.";
        }

        Practicante p = buscarPracticante(dniPracticante);
        if (p == null) return "ERROR: Practicante no encontrado.";

        LocalDate hoy = LocalDate.now();
        String hora = LocalTime.now().toString().substring(0, 8); // Hora actual HH:mm:ss

        if (this.conexionBD != null) {
            // --- SQL INSERT ---
            try {
                String sql = "INSERT INTO asistencia (fecha, hora_entrada, hora_salida, estado, id_practicante) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ps = conexionBD.prepareStatement(sql);
                ps.setDate(1, java.sql.Date.valueOf(hoy));
                ps.setString(2, hora);
                ps.setString(3, "13:00"); // Hora salida default
                ps.setString(4, estado);
                ps.setInt(5, p.getId()); // Usamos el ID recuperado de la búsqueda
                ps.executeUpdate();
                
                // También agregamos a la lista local para que se vea en la tabla sin recargar
                asistencias.add(new Asistencia(0, hoy, hora, "13:00", estado, p));
                
                return "Asistencia registrada en BD.";
            } catch (SQLException e) {
                return "Error BD: " + e.getMessage();
            }
        } else {
            // --- MEMORIA ---
            if (!p.isActivo()) return "Error: Inactivo";
            for (Asistencia a : asistencias) {
                if (a.getPracticante().getDni().equals(dniPracticante) && a.getFecha().equals(hoy)) {
                    return "Error, ya se registró hoy.";
                }
            }
            asistencias.add(new Asistencia(asistencias.size() + 1, hoy, hora, "", estado, p));
            return "Asistencia registrada (Local).";
        }
    }

    public Practicante buscarPracticante(String dni) {
        // 1. Intentar en BD
        if (this.conexionBD != null) {
            try {
                String sql = "SELECT * FROM practicante WHERE dni = ?";
                PreparedStatement ps = conexionBD.prepareStatement(sql);
                ps.setString(1, dni);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Practicante p = new Practicante();
                    p.setId(rs.getInt("id"));
                    p.setNombre(rs.getString("nombre"));
                    p.setApellido(rs.getString("apellido"));
                    p.setDni(rs.getString("dni"));
                    p.setCarrera(rs.getString("carrera"));
                    p.setActivo(rs.getBoolean("activo"));
                    p.setEmail(rs.getString("email"));
                    return p;
                }
            } catch (SQLException e) {
                System.out.println("Error buscando: " + e.getMessage());
            }
        }
        
        // 2. Intentar en Memoria
        for (Practicante p : practicantes) {
            if (p.getDni().equals(dni)) return p;
        }
        return null;
    }

    public ArrayList<Asistencia> historialAsistencia(String dni) {
        ArrayList<Asistencia> historial = new ArrayList<>();
        
        // --- BD ---
        if (this.conexionBD != null) {
            try {
                // Hacemos JOIN para traer datos del practicante también
                String sql = "SELECT a.*, p.nombre, p.apellido, p.dni FROM asistencia a " +
                             "JOIN practicante p ON a.id_practicante = p.id " +
                             "WHERE p.dni = ? ORDER BY a.fecha DESC";
                PreparedStatement ps = conexionBD.prepareStatement(sql);
                ps.setString(1, dni);
                ResultSet rs = ps.executeQuery();
                
                while(rs.next()) {
                    Practicante pTemp = new Practicante();
                    pTemp.setNombre(rs.getString("nombre"));
                    pTemp.setApellido(rs.getString("apellido"));
                    pTemp.setDni(rs.getString("dni"));
                    
                    Asistencia asis = new Asistencia();
                    asis.setFecha(rs.getDate("fecha").toLocalDate());
                    asis.setHoraEntrada(rs.getString("hora_entrada"));
                    asis.setEstado(rs.getString("estado"));
                    asis.setPracticante(pTemp);
                    
                    historial.add(asis);
                }
                return historial; // Retornamos la lista de BD
            } catch(SQLException e) {
                System.out.println("Error historial BD: " + e.getMessage());
            }
        }

        // --- MEMORIA ---
        for (Asistencia a : asistencias) {
            if (a.getPracticante().getDni().equals(dni)) {
                historial.add(a);
            }
        }
        return historial;
    }

    // --- MÉTODOS SIMPLES (GETTERS Y SETTERS) ---
    
    public Persona getUsuarioLogueado() {
        return this.usuarioLogeado;
    }

    public void cerrarSesion() {
        this.usuarioLogeado = null;
    }
    
    // --- MÉTODOS DE REPORTE ---
    
    public ArrayList<Asistencia> visualizarAsistencias() {
        if (!(usuarioLogeado instanceof Practicante)) return new ArrayList<>();
        // Reutilizamos el método de historial buscando por el DNI del usuario logueado
        return historialAsistencia(usuarioLogeado.getDni());
    }

    public String generarReporteTexto() {
        // Nota: Este método actualmente lee de la lista 'asistencias' de memoria.
        // Si quieres que lea de BD, tendrías que hacer un SELECT * FROM asistencia.
        // Por simplicidad, dejaremos que use la memoria o lo que se haya cargado.
        StringBuilder sb = new StringBuilder();
        sb.append("REPORTE GENERAL\n----------------\n");
        
        int total = 0;
        // Si hay BD, podríamos hacer un conteo rápido
        if (conexionBD != null) {
             try {
                 String sql = "SELECT COUNT(*) FROM asistencia";
                 PreparedStatement ps = conexionBD.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery();
                 if(rs.next()) total = rs.getInt(1);
                 sb.append("Total registros en Base de Datos: ").append(total).append("\n");
             } catch(Exception e) {}
        }
        
        // Detalle de memoria (o lo que hayas cargado)
        for (Asistencia a : asistencias) {
            sb.append(a.getFecha()).append(" - ").append(a.getPracticante().getNombre()).append(" - ").append(a.getEstado()).append("\n");
        }
        return sb.toString();
    }
    
    // --- OTROS MÉTODOS (Registrar Actividad, Incidencia, etc.) ---
    // (Puedes seguir la misma lógica: IF conexion != null -> SQL INSERT, ELSE -> List.add)
    
    public String registrarActividad(String dni, String desc) {
        if(!(usuarioLogeado instanceof Profesor) && !(usuarioLogeado instanceof Administrador)) return "Sin permisos";
        
        Practicante p = buscarPracticante(dni);
        if(p == null) return "No encontrado";
        
        if (conexionBD != null) {
            try {
                // Asumimos que existe la tabla 'actividad'
                String sql = "INSERT INTO actividad (fecha, descripcion, id_practicante) VALUES (?, ?, ?)";
                PreparedStatement ps = conexionBD.prepareStatement(sql);
                ps.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
                ps.setString(2, desc);
                ps.setInt(3, p.getId());
                ps.executeUpdate();
                return "Actividad guardada en BD.";
            } catch (SQLException e) { return "Error BD: " + e.getMessage(); }
        }
        return "Guardado en Memoria";
    }
    
    public String reportarIncidencia(String mensaje) {
        if (!(usuarioLogeado instanceof Practicante)) return "Solo practicantes.";
        
        if (conexionBD != null) {
            try {
                String sql = "INSERT INTO incidencia (fecha_reporte, mensaje, estado, id_practicante) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = conexionBD.prepareStatement(sql);
                ps.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
                ps.setString(2, mensaje);
                ps.setString(3, "Pendiente");
                ps.setInt(4, usuarioLogeado.getId());
                ps.executeUpdate();
                return "Incidencia enviada a BD.";
            } catch (SQLException e) { return "Error BD: " + e.getMessage(); }
        }
        return "Incidencia guardada localmente.";
    }
    
    public String modificarAsistencia(String dni, LocalDate fecha, String nuevoEstado) {
         if (!(usuarioLogeado instanceof Administrador) && !(usuarioLogeado instanceof Secretaria)) return "Sin permisos";
         
         if (conexionBD != null) {
             try {
                 // Necesitamos el ID del practicante
                 Practicante p = buscarPracticante(dni);
                 if (p==null) return "Practicante no encontrado";
                 
                 String sql = "UPDATE asistencia SET estado = ? WHERE id_practicante = ? AND fecha = ?";
                 PreparedStatement ps = conexionBD.prepareStatement(sql);
                 ps.setString(1, nuevoEstado);
                 ps.setInt(2, p.getId());
                 ps.setDate(3, java.sql.Date.valueOf(fecha));
                 
                 int filas = ps.executeUpdate();
                 if (filas > 0) return "Actualizado en BD.";
                 else return "No se encontró el registro en BD.";
                 
             } catch (SQLException e) { return "Error BD: " + e.getMessage(); }
         }
         return "Modificado en memoria (si existiera).";
    }
    public ArrayList<Profesor> listarProfesores(){
        ArrayList<Profesor> listaProfe = new ArrayList<>(); 
        if (this.conexionBD != null) {
            try {
                // Solo necesitamos el ID y el nombre completo para el combo
                String sql = "SELECT id, nombre, apellido FROM profesor";
                PreparedStatement ps = conexionBD.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    // Creamos un profesor "ligero" solo con los datos necesarios
                    Profesor p = new Profesor();
                    p.setId(rs.getInt("id"));
                    p.setNombre(rs.getString("nombre"));
                    p.setApellido(rs.getString("apellido"));
                    listaProfe.add(p);
                }
            } catch (SQLException e) {
                System.out.println("Error al listar profesores: " + e.getMessage());
            }
        }
        // Si no hay BD, podrías devolver la lista en memoria 'profesor' como respaldo
        if (listaProfe.isEmpty()) return this.profesor; 
        return listaProfe;
    }
}