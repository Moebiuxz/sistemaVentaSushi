package modelo;

public class ProductoPromocion {

    public int id;
    public int idPromocion;
    public int idProducto;
    public int estado; /*1.Activo 2.Inactivo*/


    public ProductoPromocion() {
    }

    public ProductoPromocion(int id, int idPromocion, int idProducto, int estado) {
        this.id = id;
        this.idPromocion = idPromocion;
        this.idProducto = idProducto;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}
