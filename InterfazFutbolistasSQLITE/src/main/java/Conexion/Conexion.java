/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jaime
 */
public class Conexion {
    private Connection conex;
    private String url = "jdbc:sqlite:C://sqlite/db/PracticaSQlite.db";

    public Connection getConex() {
        return conex;
    }

    public void setConex(Connection conex) {
        this.conex = conex;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
    public Connection conectar () throws SQLException {
        try{
            conex = DriverManager.getConnection(url);
            System.out.println("Conectado a la base de datos");
        }
        catch(SQLException e){
            System.out.println("Fallo1!");
            e.printStackTrace();
        }
        return conex;
    }
    
}
