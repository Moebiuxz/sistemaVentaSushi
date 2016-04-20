package modelo;

import java.sql.Date;

public class Cliente {

    public String fono;
    public String nombre;
    public String apellido;
    public Date nacimiento;
    public int estado; /*1.Activo, 2.Inactivo.*/


    public Cliente() {
    }

    public Cliente(String fono, String nombre, String apellido, Date nacimiento, int estado) {
        this.fono = fono;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacimiento = nacimiento;
        this.estado = estado;
    }

    public String getFono() {
        return fono;
    }

    public void setFono(String fono) {
        this.fono = fono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}
