package bd;

import java.sql.SQLException;

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
}
