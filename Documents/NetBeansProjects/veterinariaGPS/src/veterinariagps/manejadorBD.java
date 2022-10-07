/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package veterinariagps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    //_---------------------------Login-----------------------------------------
    
    public void verificarUsuario(String nom,String pass)throws Exception{
        String query = "SELECT * FROM usuarios;";
        ResultSet rs = sentencia.executeQuery(query);
        String nombre="" , contraseña="";
        
        while (rs.next()) {
            nombre = rs.getString("NombreClave");
            contraseña = rs.getString("password"); 
            //System.out.println(contraseña+nombre);
            if (nombre.equals(nom)) {
                if (contraseña.equals(pass)) {
                    JOptionPane.showMessageDialog(null,"Ingresaste");
                    new MenuP().setVisible(true);
                    break;
                }else JOptionPane.showMessageDialog(null,"Contraseña Incorrecta");
            }
        }
        
    }
    //--------------------------------CRUD EMPLEADO-----------------------------
    /*public void crearEmpleado(int id, String nombre, int sueldo, String puesto){
        String qry = "INSERT INTO delincuente (ndelincuente,alias, sexo, lorigen, estarura, peso) VALUES ('"+id+"','"+alias+"','"+sexo+"','"+origen+"','"+altura+"','"+peso+"')";
        System.out.println(qry);
    }*/
    
    public int crearEmpleado(int id, String nombre, String puesto)throws Exception{ 
        String qry = "INSERT INTO empleado (idEmpleado,Empleadocol,Nombre ) VALUES ('"+id+"','"+puesto+"','"+nombre+"');";
        System.out.println(qry);
        return sentencia.executeUpdate(qry);
    }
    
    public ArrayList<String> mostrarEmpleados(){
        ArrayList<String> resultados = new ArrayList<>();
        try {
            String query = "SELECT * FROM empleado;";
            ResultSet rs = sentencia.executeQuery(query);
            
                while (rs.next()) {
                    //valores de empleados
                    int id = rs.getInt("idEmpleado");
                    String puesto = rs.getString("Empleadocol");
                    String nombre = rs.getString("Nombre");
                    resultados.add("id: "+id+"Puesto: "+puesto+"Nombre: "+nombre);
                    
                }
                //System.out.println(resultados);
        } catch (SQLException ex) {
            Logger.getLogger(manejadorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultados;
    }
    
    public void eliminarUsuario(int id){
        String qry = "DELETE FROM empleado WHERE idEmpleado = '"+id+"';";
        System.out.println(qry);
        try {
            sentencia.executeUpdate(qry);
        } catch (SQLException ex) {
            Logger.getLogger(manejadorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String [] consultarUsuario(String id){
        String [] registros = new String [3];
        try {
            String query = "SELECT * FROM empleado;";
            ResultSet rs = sentencia.executeQuery(query);
            
            while (rs.next()) {
                registros[0] = rs.getString("idEmpleado");
                if (id == registros[0]) {
                    registros[1] = rs.getString("Empleadocol");
                    registros[2] = rs.getString("Nombre");
                    break;
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(manejadorBD.class.getName()).log(Level.SEVERE, null, ex);
        }//Modificar nos falta
        /*for (int i = 0; i < registros.length; i++) {
            System.out.println(registros[i]);
        }*/
        return registros;
    }
    
    //---------------------------------Productos
    public int crearProducto(int id, String producto, String descripcion)throws Exception{ 
        String qry = "INSERT INTO producto (idProducto,Nombre,Descripcion ) VALUES ('"+id+"','"+producto+"','"+descripcion+"');";
        System.out.println(qry);
        return sentencia.executeUpdate(qry);
    }
    
    public ArrayList<String> mostrarProductos(){
        ArrayList<String> resultados = new ArrayList<>();
        try {
            String query = "SELECT * FROM producto;";
            ResultSet rs = sentencia.executeQuery(query);
            
                while (rs.next()) {
                    //valores de empleados
                    int id = rs.getInt("idProducto");
                    String Nombre = rs.getString("Nombre");
                    String Descripcion = rs.getString("Descripcion");
                    resultados.add("id: "+id+"Nombre: "+Nombre+"Descripcion: "+Descripcion);
                    
                }
                //System.out.println(resultados);
        } catch (SQLException ex) {
            Logger.getLogger(manejadorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultados;
    }
    
    public void eliminarProducto(int id){
        String qry = "DELETE FROM producto WHERE idProducto = '"+id+"';";
        System.out.println(qry);
        try {
            sentencia.executeUpdate(qry);
        } catch (SQLException ex) {
            Logger.getLogger(manejadorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
