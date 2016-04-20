package modelo;

public class Promocion {

    public int id;
    public String nombre;
    public int descuento;
    public int estado; /*1.Activo, 2.Inactivo.*/


    public Promocion() {
    }

    public Promocion(int id, String nombre, int descuento, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.descuento = descuento;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}
