package modelo;

public class PromocionVenta {
    public int id;
    public int promocion;
    public int venta;
    public int estado;

    public PromocionVenta() {
    }

    public PromocionVenta(int id, int promocion, int venta, int estado) {
        this.id = id;
        this.promocion = promocion;
        this.venta = venta;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPromocion() {
        return promocion;
    }

    public void setPromocion(int promocion) {
        this.promocion = promocion;
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
