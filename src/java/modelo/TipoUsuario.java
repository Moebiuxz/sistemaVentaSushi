package modelo;

public class TipoUsuario {

    private int id;
    private String nombre;
    private int estado; /*1.Activo, 2.Inactivo.*/


    public TipoUsuario() {
    }

    public TipoUsuario(int id, String nombre, int estado) {
        this.id = id;
        this.nombre = nombre;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}
