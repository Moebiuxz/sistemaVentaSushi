package modelo;

import java.sql.Date;

public class Venta {

    public int id;
    public Date fecha;
    public int personal;
    public String cliente;
    public int total;
    public int estado; /*1.Activo 2.Inactivo*/


    public Venta() {
    }

    public Venta(int id, Date fecha, int personal, String cliente, int total, int estado) {
        this.id = id;
        this.fecha = fecha;
        this.personal = personal;
        this.cliente = cliente;
        this.total = total;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getPersonal() {
        return personal;
    }

    public void setPersonal(int personal) {
        this.personal = personal;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    
    
}
