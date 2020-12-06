/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Futbolista;


import Conexion.Conexion;
import Futbolista.Futbolista;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jaime
 */
public class FutbolistaController {
    ArrayList<Futbolista> futbolistas;
    Connection conex;
    Conexion conexion = new Conexion();

    public ArrayList<Futbolista> getFutbolistas() {
        return futbolistas;
    }

    public void setFutbolistas(ArrayList<Futbolista> futbolistas) {
        this.futbolistas = futbolistas;
    }


    public Futbolista getFutbolistas(int i) {
        return futbolistas.get(i);
    }
    public FutbolistaController() {
        this.futbolistas = new ArrayList<Futbolista>();
    }
    
    public void guardarFutbolistas(Futbolista fut){
            futbolistas.add(fut);
    }
    public void CrearTablaFut() throws SQLException{
        String NombreTabla ="Futbolista";
        conex = conexion.conectar();
        
        if(conex != null){
            try{
                Statement statement = conex.createStatement();
                String sql="CREATE TABLE if not exists "+NombreTabla+" "+
                        "(dni INT PRIMARY KEY NOT NULL,"+
                        " nombre string,"+
                        " apellido1 string,"+
                        " apellido2 string,"+
                        "edad int)";
            
                statement.execute(sql);
                statement.close();
            }catch(Exception e){
                System.out.println("Error al crear la tabla o que ya estaba creada");
                e.printStackTrace();
            }finally{
               conex.close();
            }
        }
    }
    public void guardarFutbolistasFichero(Futbolista fut) throws FileNotFoundException, IOException, SQLException{
        conex = conexion.conectar();
        
        System.out.println("Insertando datos en tabla...");
        String sql = "INSERT INTO Futbolista (dni,nombre,apellido1,apellido2,edad) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement pstmt = conex.prepareStatement(sql);
            //Cambiar para futbolista fut
            pstmt.setString(1, fut.getDni());
            pstmt.setString(2, fut.getNombre());
            pstmt.setString(3, fut.getApellido1());
            pstmt.setString(4, fut.getApellido2());
            pstmt.setInt(5, fut.getEdad());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void ModificarFutbolista(Futbolista fut) throws SQLException{
        conex = conexion.conectar();
        System.out.println("Modificando datos de la tabla...");
        String sql = "UPDATE Futbolista "+
            " SET dni=?,nombre=?,apellido1=?,apellido2=?,edad=?"+
            " WHERE dni=" +fut.getDni();

        try {
            PreparedStatement pstmt = conex.prepareStatement(sql);
            // set the corresponding param

            pstmt.setString(1, fut.getDni());
            pstmt.setString(2, fut.getNombre());
            pstmt.setString(3, fut.getApellido1());
            pstmt.setString(4, fut.getApellido2());
            pstmt.setInt(5, fut.getEdad());
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void LeerFichero() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
        conex = conexion.conectar();
        try{
            String consult = "SELECT * FROM Futbolista";
            Statement statement = conex.createStatement();
            ResultSet rs = statement.executeQuery(consult);
            
            while(rs.next()) {
                
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apellido1 = rs.getString("apellido1");
                String apellido2 = rs.getString("apellido2");
                int edad  = rs.getInt("apellido2");
                Futbolista fut = new Futbolista(dni, nombre, apellido1, apellido2, edad);
                guardarFutbolistas(fut);
            }
        }
        catch(SQLException e) {
            System.out.println("Fallo2!");
            e.printStackTrace();
        }
        finally{
            conex.close();
        }
    }
    public void EliminarFut (Futbolista fut) throws SQLException{
        conex = conexion.conectar();
        try{
            Statement statement = conex.createStatement();
            String sql =("DELETE FROM Futbolista"+
            " WHERE dni="+fut.getDni());
            statement.execute(sql);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }finally{
            conex.close();
        }
    }
    public String[][] datos_fut(){         
        String contenido[][] = new String[getFutbolistas().size()][5];
        for(int i = 0;i < this.getFutbolistas().size();i++){             
            contenido[i][0] = String.valueOf(this.getFutbolistas(i).getNombre());
            contenido[i][1] = String.valueOf(this.getFutbolistas(i).getApellido1()); 

        }
        return contenido;
    }
}
