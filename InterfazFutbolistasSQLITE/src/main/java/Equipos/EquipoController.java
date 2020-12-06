/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Equipos;


import Conexion.Conexion;
import Equipos.Equipo;
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
public class EquipoController {
    ArrayList<Equipo> equipos;
    Connection conex;
    Conexion conexion = new Conexion();

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }

    public Equipo getEquipos(int i) {
        return equipos.get(i);
    }

    public EquipoController() {
        this.equipos = new ArrayList<Equipo>();
    }
    public void CrearTablaEqui() throws SQLException{
        String NombreTabla ="Equipo";
        conex = conexion.conectar();
        
        if(conex != null){
            try{
                Statement statement = conex.createStatement();
                String sql="CREATE TABLE if not exists "+NombreTabla+" "+
                        "(equipo_ID INT PRIMARY KEY NOT NULL,"+
                        " nombre_equipo string,"+
                        " nombre_campo string,"+
                        " fecha_fundacion string,"+
                        "numero_socios int,"+
                        "DniFut string)";
            
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
    public void guardarEquipos(Equipo equip){
            equipos.add(equip);
    }
        
    public void guardarEquiposFichero(Equipo equi) throws FileNotFoundException, IOException, SQLException{
        conex = conexion.conectar();
        
        System.out.println("Insertando datos en tabla...");
        String sql = "INSERT INTO Equipo (equipo_ID,nombre_equipo,nombre_campo,fecha_fundacion,numero_socios,DniFut) VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt = conex.prepareStatement(sql);
            //Cambiar para futbolista fut
            pstmt.setString(1, equi.getEquipo_ID());
            pstmt.setString(2, equi.getNombre_equipo());
            pstmt.setString(3, equi.getNombre_campo());
            pstmt.setString(4, equi.getFecha_fundacion());
            pstmt.setInt(5, equi.getNumero_socios());
            pstmt.setString(6, equi.getDniFut());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void ModificarEquipo(Equipo equi) throws SQLException{
        conex = conexion.conectar();
        System.out.println("Modificando datos de la tabla...");
        String sql = "UPDATE Equipo "+
            " SET equipo_ID=?,nombre_equipo=?,nombre_campo=?,fecha_fundacion=?,numero_socios=?,DniFut=?"+
            " WHERE equipo_ID=" +equi.getEquipo_ID();

        try {
            PreparedStatement pstmt = conex.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, equi.getEquipo_ID());
            pstmt.setString(2, equi.getNombre_equipo());
            pstmt.setString(3, equi.getNombre_campo());
            pstmt.setString(4, equi.getFecha_fundacion());
            pstmt.setInt(5, equi.getNumero_socios());
            pstmt.setString(6, equi.getDniFut());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void LeerFichero() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
        conex = conexion.conectar();
        try{
            String consult = "SELECT * FROM Equipo";
            Statement statement = conex.createStatement();
            ResultSet rs = statement.executeQuery(consult);
            
            while(rs.next()) {
                
                String equipo_id = rs.getString("equipo_ID");
                String nombre = rs.getString("nombre_equipo");
                String fundacion = rs.getString("fecha_fundacion");
                String campo = rs.getString("nombre_campo");
                int socios  = rs.getInt("numero_socios");
                String dnisoc  = rs.getString("DniFut");
                Equipo equi = new Equipo (equipo_id, nombre, fundacion, campo, socios, dnisoc);
                guardarEquiposFichero(equi);
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
    public void EliminarEqui (Equipo equi) throws SQLException{
        conex = conexion.conectar();
        try{
            Statement statement = conex.createStatement();
            String sql =("DELETE FROM Equipo"+
            " WHERE equipo_ID="+equi.getEquipo_ID());
            statement.execute(sql);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }finally{
            conex.close();
        }
    }
    public String[][] datos_equi(){         
        String contenido[][] = new String[getEquipos().size()][3];
        for(int i = 0;i < this.getEquipos().size();i++){             
            contenido[i][0] = String.valueOf(this.getEquipos(i).getNombre_equipo());
            contenido[i][1] = String.valueOf(this.getEquipos(i).getNombre_campo());
        }
        return contenido;
    }
}
