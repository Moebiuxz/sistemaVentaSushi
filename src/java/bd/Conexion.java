package bd;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection; // Clase para poder conectar
import java.sql.Statement; // Clase para enviar consultas SQL
import java.sql.ResultSet; // Clase para obtener los datos o resultados de
                           // un select
import java.sql.SQLException; // Para manejar excepciones de SQL

public class Conexion {
    private Connection conexion; // con
    public Statement sentencia;  // sen
    public ResultSet resultado;  // rs
    
    public Conexion(String server, String bd, String user, String pass) throws SQLException{
        MysqlDataSource mysql = new MysqlDataSource();
        
        mysql.setServerName(server);
        mysql.setDatabaseName(bd);
        mysql.setUser(user);
        mysql.setPassword(pass);
        
        conexion = mysql.getConnection();
    }
    
    /**
    
    @param sql - Puede ser un Delete, Update o Insert 
     * @throws java.sql.SQLException 
    */
    public void ejecutar(String sql) throws SQLException{
        sentencia = conexion.createStatement();
        sentencia.executeUpdate(sql);
        sentencia.close();
    }
    
    /**
    
    @param select consulta select
    @return un opbjeto del Tipo ResultSet
    @throws SQLException 
    */
    public ResultSet ejecutarSelect(String select) throws SQLException{
        sentencia = conexion.createStatement();
        resultado = sentencia.executeQuery(select);
        return resultado;
    }
}