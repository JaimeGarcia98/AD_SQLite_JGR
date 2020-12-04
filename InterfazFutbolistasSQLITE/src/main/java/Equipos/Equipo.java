/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Equipos;

import java.io.Serializable;

/**
 *
 * @author jaime
 */
public class Equipo implements Serializable{
    String equipo_ID;
    String nombre_equipo;
    String fecha_fundacion;
    String nombre_campo;
    int numero_socios;
    String DniFut;

    public String getDniFut() {
        return DniFut;
    }

    public void setDniFut(String DniFut) {
        this.DniFut = DniFut;
    }

    public String getEquipo_ID() {
        return equipo_ID;
    }

    public void setEquipo_ID(String equipo_ID) {
        this.equipo_ID = equipo_ID;
    }

    public String getNombre_equipo() {
        return nombre_equipo;
    }

    public void setNombre_equipo(String nombre_equipo) {
        this.nombre_equipo = nombre_equipo;
    }

    public String getFecha_fundacion() {
        return fecha_fundacion;
    }

    public void setFecha_fundacion(String fecha_fundacion) {
        this.fecha_fundacion = fecha_fundacion;
    }

    public String getNombre_campo() {
        return nombre_campo;
    }

    public void setNombre_campo(String nombre_campo) {
        this.nombre_campo = nombre_campo;
    }

    public int getNumero_socios() {
        return numero_socios;
    }

    public void setNumero_socios(int numero_socios) {
        this.numero_socios = numero_socios;
    }

    public Equipo() {
        this.equipo_ID = "";
        this.nombre_equipo = "";
        this.fecha_fundacion = "";
        this.nombre_campo = "";
        this.numero_socios = 0;
        this.DniFut = "";
    }

    public Equipo(String equipo_ID, String nombre_equipo, String fecha_fundacion, String nombre_campo, int numero_socios, String dni_fut) {
        this.equipo_ID = equipo_ID;
        this.nombre_equipo = nombre_equipo;
        this.fecha_fundacion = fecha_fundacion;
        this.nombre_campo = nombre_campo;
        this.numero_socios = numero_socios;
        this.DniFut = dni_fut;
    }
    

}
