/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String SERVIDOR = "localhost"; //UCLAB800
    private static String BD = "BDGInstituto";
    
    private static final String URL = "jdbc:sqlserver://"
            +SERVIDOR+ ";databaseName="+BD+
            ";integratedSecurity=true" +
            ";encrypt=true" + ";trustServerCertificate=true";   
    
    private static Connection conexion = null;
    
    public static Connection getConnection(){
        try {
            if (conexion == null || conexion.isClosed()) {
            
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                
                conexion = DriverManager.getConnection(URL);
                System.out.println("Conexion exitosa a BD" + BD);
           }
        }
        catch (ClassNotFoundException e) {
                System.out.println("No se encontró el driver de SQL Server "+
                        e.getMessage());
                
            } catch(SQLException e){
                System.out.println("Error al conectar a la BD "+
                        e.getMessage());
            }
        return conexion;
        }
}
