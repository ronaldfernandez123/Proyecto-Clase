/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

/**
 *
 * @author elhac
 */
public class conex {
    Connection cn;
    
    public Connection ConectarBd(){
        try {
            Class.forName("com.mysql.jdbc.Connection");
            cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/proaula","root","");
            System.out.println("Conexion exitosa");
        } catch (Exception e) {
            System.out.println("Conexion fallida");
            
        }
    
       return cn;
    }
}
