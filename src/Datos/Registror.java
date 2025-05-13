package Datos;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import Datos.conex;
import Datos.conex;
import UI.Login;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.awt.TextField;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author juanp
 */

public class Registror {
    public Registror (){    
} conex con = new conex();
    Connection cn = con.ConectarBd();
   public void registro(String nombres, String apellidos, String tipoDocumento, String NumeroDocumento, String Email, String cargo, String contraseña, JLabel OBLIGACION){
      Login ob = new Login();
       if (nombres.isEmpty() || apellidos.isEmpty() || NumeroDocumento.isEmpty() || Email.isEmpty() || contraseña.isEmpty()) {
            OBLIGACION.setText("Por favor, rellene todos los datos solicitados");
        } else {
            if (tipoDocumento.equalsIgnoreCase("Seleccionar")) {
                OBLIGACION.setText("Por favor, ingrese su tipo de documento");
            }
            if (cargo.equalsIgnoreCase("Seleccionar")) {
                OBLIGACION.setText("Por favor, ingrese su cargo");
            }
            if (NumeroDocumento.length() > 10 || NumeroDocumento.length() < 10) {
                OBLIGACION.setText("Por favor, ingrese su Documento correctamente");
            } else {
                try {
                    String consulta = "INSERT into usuarios(Nombres,Apellidos,Tipo_de_Documento,Email,No_de_Documento,Contraseña,cargo)values('" + nombres + "','" + apellidos + "','" + tipoDocumento + "','" + Email + "','" + NumeroDocumento + "','" + contraseña + "','" + cargo + "')";
                    PreparedStatement ps = (PreparedStatement) cn.prepareStatement(consulta);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "DATOS INGRESADOS CORRECTAMENTE");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "NO SE PUDO REGISTRAR DATOS" + e);
                }
            }
        }
      
   }
   
}

