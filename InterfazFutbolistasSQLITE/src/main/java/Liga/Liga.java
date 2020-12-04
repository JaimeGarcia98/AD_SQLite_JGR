/*
 * To change this license header, choose License Headers in Pcha_finroject Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liga;

import java.io.Serializable;

/**
 *
 * @author jaime
 */
public class Liga implements Serializable{
    String liga_ID;
    String nombre_liga;
    int numero_equipos;
    String fecha_inicio;
    String fecha_fin;
    String Id_equipos;

    public String getId_equipos() {
        return Id_equipos;
    }

    public void setId_equipos(String Id_equipos) {
        this.Id_equipos = Id_equipos;
    }

    public String getLiga_ID() {
        return liga_ID;
    }

    public void setLiga_ID(String liga_ID) {
        this.liga_ID = liga_ID;
    }

    public String getNombre_liga() {
        return nombre_liga;
    }

    public void setNombre_liga(String nombre_liga) {
        this.nombre_liga = nombre_liga;
    }

    public int getNumero_equipos() {
        return numero_equipos;
    }

    public void setNumero_equipos(int numero_equipos) {
        this.numero_equipos = numero_equipos;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Liga() {
        this.liga_ID = "";
        this.nombre_liga = "";
        this.numero_equipos = 0;
        this.fecha_inicio = "";
        this.fecha_fin = "";
        this.Id_equipos = "";
    }

    public Liga(String liga_ID, String nombre_liga, int numero_equipos, String fecha_inicio, String fecha_fin, String Id_equipos) {
        this.liga_ID = liga_ID;
        this.nombre_liga = nombre_liga;
        this.numero_equipos = numero_equipos;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.Id_equipos = Id_equipos;
    }
    
}
