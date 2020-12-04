/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Futbolista;

import java.io.Serializable;

/**
 *
 * @author jaime
 */
public class Futbolista implements Serializable{
    
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int edad;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Futbolista() {
        this.dni = "";
        this.nombre = "";
        this.apellido1 = "";
        this.apellido2 = "";
        this.edad = 0;
    }

    public Futbolista(String dni, String nombre, String apellido1, String apellido2, int edad) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.edad = edad;
    }
    
}
