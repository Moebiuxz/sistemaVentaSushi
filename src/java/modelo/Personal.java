package modelo;

public class Personal {

    public int id;
    public String rut;
    public String nombre;
    public String apellidos;
    public int tipo;
    public int usuario;
    public int estado; /*1.Activo, 2.Inactivo.*/


    public Personal() {
    }

    public Personal(int id, String rut, String nombre, String apellidos, int tipo, int usuario, int estado) {
        this.id = id;
        this.rut = rut;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.tipo = tipo;
        this.usuario = usuario;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}
