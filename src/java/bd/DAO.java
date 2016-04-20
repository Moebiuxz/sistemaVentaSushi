package bd;

import java.sql.SQLException;
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
    
    public Usuario getUsuario(Usuario u) throws SQLException {
        Usuario user = null;
        sql = "SELECT * FROM usuario WHERE usuario_nombre = '" + u.getNombre()+ "' AND usuario_clave = '" + u.getPassword()+ "'";
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
    
    public TipoUsuario getTipoUsuario(int id) throws SQLException{
        TipoUsuario tipo = null;
        sql = "SELECT * FROM tipoUsuario WHERE tipoUsuario_id = '"+tipo.getId()+"'";
        C.resultado = C.ejecutarSelect(sql);
        if (C.resultado.next()) {
            tipo = new TipoUsuario(C.resultado.getInt(1), C.resultado.getString(2), C.resultado.getInt(3));
        }
        C.sentencia.close();
        return tipo;
    }
}
