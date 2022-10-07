/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package veterinariagps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author Wallace
 */
public class manejadorBD {
    private Connection conexion;
    private Statement sentencia;
    
    private String url="jdbc:mysql://localhost:3306/veterinaria?useUnicode=true&use"
            +"JDBCCompliantTimezoneShift=true&useLegacyDateTimeCode=false&"
            +"serverTimezone=UTC";
    
    public manejadorBD(){
        /*para crear en consola la BD
        se ingresa : mysql -u root -h localhost -p
        se ingresa password y listo,usar comandos de MySQL workbench
        */
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url,"root","");
            sentencia = conexion.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void cerrarConexion() throws Exception{
        if (sentencia != null) sentencia.close();
        if (conexion != null) conexion.close();   
    }
    
    public boolean verificarUsuario(String nom,String pass, Boolean verificar)throws Exception{
        String query = "SELECT * FROM empleado;";
        ResultSet rs = sentencia.executeQuery(query);
        String nombre="" , contraseña="";
        while (rs.next()) {
            //
            nombre = rs.getString("nombre");
            contraseña = rs.getString("contraseña");
            if (nombre.equals(nom)) {
                if (contraseña.equals(pass)) {
                    //System.out.println("Ingresaste!!!!");
                    JOptionPane.showMessageDialog(null,"Ingresaste MI PENDEJO");
                    verificar = false;
                    new Usuarios().setVisible(true);
                    break;
                }else JOptionPane.showMessageDialog(null,"Contraseña Incorrecta");
            }
        }
        return verificar;
    }
}
