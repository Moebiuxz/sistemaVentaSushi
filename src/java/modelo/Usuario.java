package modelo;

public class Usuario {
    private int id;
    private String nombre;
    private String password;
    private int tipoUsuario;
    private int estado; /*1.Activo, 2.Inactivo.*/

    public Usuario() {
    }

    public Usuario(int id, String nombre, String password, int tipoUsuario, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
}
