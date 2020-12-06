/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liga;

import Conexion.Conexion;
import Equipos.Equipo;
import Liga.Liga;
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
public class LigaController {
    ArrayList<Liga> ligas;
    Connection conex;
    Conexion conexion = new Conexion();

    public ArrayList<Liga> getLigas() {
        return ligas;
    }

    public void setLigas(ArrayList<Liga> ligas) {
        this.ligas = ligas;
    }
    
    public Liga getLigas(int i) {
        return ligas.get(i);
    }
    public LigaController() {
        this.ligas = new ArrayList<Liga>();
    }
    
    public void guardarLigas(Liga lig){
            ligas.add(lig);
    }
    
    public void guardarLigasFichero(Liga lig) throws FileNotFoundException, IOException, SQLException{
        conex = conexion.conectar();
        
        System.out.println("Insertando datos en tabla...");
        String sql = "INSERT INTO Liga (liga_ID,nombre_liga,numero_equipos,fecha_inicio,fecha_fin,Id_equipos) VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt = conex.prepareStatement(sql);
            //Cambiar para futbolista fut
            pstmt.setString(1, lig.getLiga_ID());
            pstmt.setString(2, lig.getNombre_liga());
            pstmt.setInt(3, lig.getNumero_equipos());
            pstmt.setString(4, lig.getFecha_inicio());
            pstmt.setString(5, lig.getFecha_fin());
            pstmt.setString(6, lig.getId_equipos());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void CrearTablaLiga() throws SQLException{
        String NombreTabla ="Liga";
        conex = conexion.conectar();
        
        if(conex != null){
            try{
                Statement statement = conex.createStatement();
                String sql="CREATE TABLE if not exists "+NombreTabla+" "+
                        "(liga_ID INT PRIMARY KEY NOT NULL,"+
                        " nombre_liga string,"+
                        " numero_equipos int,"+
                        " fecha_inicio string,"+
                        " fecha_fin string,"+
                        "Id_equipos string)";
            
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
    public void ModificarLiga(Liga lig) throws SQLException{
        conex = conexion.conectar();
        System.out.println("Modificando datos de la tabla...");
        String sql = "UPDATE Liga "+
            " SET liga_ID=?,nombre_liga=?,numero_equipos=?,fecha_inicio=?,fecha_fin=?,Id_equipos=?"+
            " WHERE liga_ID=" +lig.getLiga_ID();

        try {
            PreparedStatement pstmt = conex.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, lig.getLiga_ID());
            pstmt.setString(2, lig.getNombre_liga());
            pstmt.setInt(3, lig.getNumero_equipos());
            pstmt.setString(4, lig.getFecha_inicio());
            pstmt.setString(5, lig.getFecha_fin());
            pstmt.setString(6, lig.getId_equipos());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void LeerFichero() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
        conex = conexion.conectar();
        try{
            String consult = "SELECT * FROM Liga";
            Statement statement = conex.createStatement();
            ResultSet rs = statement.executeQuery(consult);
            
            while(rs.next()) {
                
                String liga_id = rs.getString("liga_ID");
                String nombre = rs.getString("nombre_liga");
                int numero_equi = rs.getInt("numero_equipos");
                String fecha_inicio = rs.getString("fecha_inicio");
                String fecha_fin  = rs.getString("fecha_fin");
                String id_equipos  = rs.getString("Id_equipos");
                Liga lig = new Liga (liga_id, nombre, numero_equi, fecha_inicio, fecha_fin, id_equipos);
                guardarLigasFichero(lig);
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
    public void EliminarLiga (Liga lig) throws SQLException{
        conex = conexion.conectar();
        try{
            Statement statement = conex.createStatement();
            String sql =("DELETE FROM Liga"+
            " WHERE liga_ID="+lig.getLiga_ID());
            statement.execute(sql);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }finally{
            conex.close();
        }
    }
    public String[][] datos_liga(){         
        String contenido[][] = new String[getLigas().size()][5];
        for(int i = 0;i < this.getLigas().size();i++){             
            contenido[i][0] = String.valueOf(this.getLigas(i).getNombre_liga());
            contenido[i][1] = String.valueOf(this.getLigas(i).getNumero_equipos()); 
        }
        return contenido;
    }
}
