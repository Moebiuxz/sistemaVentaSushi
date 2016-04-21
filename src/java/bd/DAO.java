package bd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.TipoUsuario;
import modelo.Usuario;

public class DAO {

    public final Conexion C;
    private String sql;

    public DAO() throws SQLException {
        C = new Conexion(
                DatosConexion.SERVER,
                DatosConexion.BD,
                DatosConexion.USER,
                DatosConexion.PASS
        );
    }

    /*
     Inicio Métodos TipoUsuario
     */
    
    public void crearTipoUsuario(TipoUsuario tipo) throws SQLException {
        sql = "insert into tipoUsuario values("
                + "null,"
                + "'" + tipo.getNombre() + "',"
                + "'" + tipo.getEstado() + "'"
                + ");";
        C.ejecutar(sql);
    }

    public TipoUsuario getTipoUsuario(int id) throws SQLException {
        TipoUsuario tipo = null;
        sql = "SELECT * FROM tipoUsuario WHERE tipoUsuario_id = '" + id + "'";
        C.resultado = C.ejecutarSelect(sql);
        if (C.resultado.next()) {
            tipo = new TipoUsuario(C.resultado.getInt(1), C.resultado.getString(2), C.resultado.getInt(3));
        }
        C.sentencia.close();
        return tipo;
    }

    public List<TipoUsuario> getTipoUsuarioLike(String arg) throws SQLException {
        sql = "select * from tipoUsuario where "
                + "tipoUsuario_nombre like '%" + arg + "%' "
                + "or "
                + "tipoUsuario_id like '%" + arg + "%';";
        C.resultado = C.ejecutarSelect(sql);
        TipoUsuario tipo;
        List<TipoUsuario> listaTipo = new ArrayList<>();
        while (C.resultado.next()) {
            tipo = new TipoUsuario();
            tipo.setId(C.resultado.getInt(1));
            tipo.setNombre(C.resultado.getString(2));
            tipo.setEstado(C.resultado.getInt(3));
            listaTipo.add(tipo);
        }
        C.sentencia.close();
        return listaTipo;
    }

    public void actualizarTipoUsuario(TipoUsuario tipo) throws SQLException {
        sql = "update tipoUsuario set "
                + "tipoUsuario_nombre = '" + tipo.getNombre() + "', "
                + "tipoUsuario_estado = '" + tipo.getEstado() + "' "
                + "where "
                + "tipoUsuario_id = '" + tipo.getId() + "';";
        C.ejecutar(sql);
    }

    public void desactivarTipoUsuario(int id) throws SQLException {
        sql = "update tipoUsuario set "
                + "tipoUsuario_estado = '0' "
                + "where "
                + "tipoUsuario_id = '" + id + "';";
        C.ejecutar(sql);
    }

    public void activarTipoUsuario(int id) throws SQLException {
        sql = "update tipoUsuario set "
                + "tipoUsuario_estado = '1' "
                + "where "
                + "tipoUsuario_id = '" + id + "';";
        C.ejecutar(sql);
    }

    public void eliminarTipoUsuario(int id) throws SQLException {
        sql = "delete from tipoUsuario where tipoUsuario_id = '" + id + "';";
        C.ejecutar(sql);
    }

    public List<TipoUsuario> getTiposUsuario() throws SQLException {
        sql = "select * from tipoUsuario;";
        C.resultado = C.ejecutarSelect(sql);
        TipoUsuario tipo;
        List<TipoUsuario> listaTipo = new ArrayList<>();
        while (C.resultado.next()) {
            tipo = new TipoUsuario();
            tipo.setId(C.resultado.getInt(1));
            tipo.setNombre(C.resultado.getString(2));
            tipo.setEstado(C.resultado.getInt(3));
            listaTipo.add(tipo);
        }
        C.sentencia.close();
        return listaTipo;
    }

    /*
     Fin Métodos TipoUsuario
     */
    
    /*
     Inicio Métodos Usuario
     */
    
    public void crearUsuario(Usuario u) throws SQLException {
        sql = "insert into usuario values("
                + "null,"
                + "'" + u.getNombre() + "',"
                + "'" + u.getPassword() + "',"
                + "'" + u.getTipoUsuario() + "',"
                + "'" + u.getEstado() + "'"
                + ");";
        C.ejecutar(sql);
    }

    public Usuario getUsuario(Usuario u) throws SQLException {
        Usuario user = null;
        sql = "SELECT * FROM usuario WHERE usuario_nombre = '" + u.getNombre() + "' AND usuario_clave = AES_ENCRYPT('" + u.getPassword() + "',666)";
        C.resultado = C.ejecutarSelect(sql);
        if (C.resultado.next()) {
            user = new Usuario(
                    C.resultado.getInt(1),
                    C.resultado.getString(2),
                    C.resultado.getString(3),
                    C.resultado.getInt(4),
                    C.resultado.getInt(5)
            );
        }
        C.sentencia.close();
        return user;
    }

    /*Para buscar, no para validar con pass*/
    public List<Usuario> getUsuarioLike(String arg) throws SQLException {
        sql = "select * from usuario where "
                + "usuario_nombre like '%" + arg + "%' "
                + "or "
                + "usuario_tipo like '%" + arg + "%';";
        C.resultado = C.ejecutarSelect(sql);
        Usuario u;
        List<Usuario> lu = new ArrayList<>();
        while (C.resultado.next()) {
            u = new Usuario();
            u.setId(C.resultado.getInt(1));
            u.setNombre(C.resultado.getString(2));
            u.setPassword(C.resultado.getString(3));
            u.setTipoUsuario(C.resultado.getInt(4));
            u.setEstado(C.resultado.getInt(5));
            lu.add(u);
        }
        C.sentencia.close();
        return lu;
    }

    public void actualizarUsuario(Usuario u) throws SQLException {
        sql = "update usuario set "
                + "usuario_nombre = '" + u.getNombre() + "', "
                + "usuario_clave = '" + u.getPassword() + "', "
                + "usuario_tipo = '" + u.getTipoUsuario() + "', "
                + "usuario_estado = '" + u.getEstado() + "' "
                + "where "
                + "usuario_id = '" + u.getId() + "';";
        C.ejecutar(sql);
    }

    public void desactivarUsuario(int id) throws SQLException {
        sql = "update usuario set "
                + "usuario_estado = '0' "
                + "where "
                + "usuario_id = '" + id + "';";
        C.ejecutar(sql);
    }

    public void activarUsuario(int id) throws SQLException {
        sql = "update usuario set "
                + "usuario_estado = '1' "
                + "where "
                + "usuario_id = '" + id + "';";
        C.ejecutar(sql);
    }

    public void eliminarUsuario(int id) throws SQLException {
        sql = "delete from usuario where usuario_id = '" + id + "';";
        C.ejecutar(sql);
    }

    public List<Usuario> getUsuarios() throws SQLException {
        sql = "select * from usuario;";
        C.resultado = C.ejecutarSelect(sql);
        Usuario u;
        List<Usuario> lu = new ArrayList<>();
        while (C.resultado.next()) {
            u = new Usuario();
            u.setId(C.resultado.getInt(1));
            u.setNombre(C.resultado.getString(2));
            u.setPassword(C.resultado.getString(3));
            u.setTipoUsuario(C.resultado.getInt(4));
            u.setEstado(C.resultado.getInt(5));
            lu.add(u);
        }
        C.sentencia.close();
        return lu;
    }
    
    /*
     Fin Métodos Usuario
     */
}