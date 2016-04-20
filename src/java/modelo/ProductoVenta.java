package modelo;

public class ProductoVenta {

    public int id;
    public int producto;
    public int venta;
    public int estado; /*1.Activo 2.Inactivo*/


    public ProductoVenta() {
    }

    public ProductoVenta(int id, int producto, int venta, int estado) {
        this.id = id;
        this.producto = producto;
        this.venta = venta;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public int getVenta() {
        return venta;
    }

    public void setVenta(int venta) {
        this.venta = venta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
