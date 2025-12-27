/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author RS
 */
public class Conexion {
    
//URL, USUARIO, PASSWORD
    //mysql://root:QrNRMFJXzmpILaRgtarrOYRHkHcsQoPk@maglev.proxy.rlwy.net:29832/railway
    private static final String URL ="jdbc:mysql://yamabiko.proxy.rlwy.net:34418/railway";
    private static final String USER ="root";
    private static final String PASSWORD ="EIWveJrAOjTHtcapchYzyhRbRQqafguZ";
    
    public static Connection getConnection(){
        Connection con =null;
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Se conecto a la Base de Datos");
        } catch (Exception ex) {
            System.out.println("Error de conexion: "+ex.getMessage());
        }
        
        return con;
    }
}
