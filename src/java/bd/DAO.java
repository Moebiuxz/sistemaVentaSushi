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
    public void crearTipousuario(TipoUsuario tipo) throws SQLException {
        sql = "INSERT INTO tipoUsuario VALUES("
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

    public TipoUsuario getTipoUsuarioLike(String arg) throws SQLException {
        sql = "select * from tipoUsuario where "
                + "tipoUsuario_nombre like '%" + arg + "%' "
                + "or "
                + "tipoUsuario_id like '%" + arg + "%';";
        C.resultado = C.ejecutarSelect(sql);
        TipoUsuario tipo = null;
        if (C.resultado.next()) {
            tipo = new TipoUsuario();
            tipo.setId(C.resultado.getInt(1));
            tipo.setNombre(C.resultado.getString(2));
            tipo.setEstado(C.resultado.getInt(3));
        }
        C.sentencia.close();
        return tipo;
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
        sql = "SELECT * FROM tipoUsuario;";
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
        System.out.println(u.getNombre());
        return user;
    }
}
