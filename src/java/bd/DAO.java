package bd;

import com.sun.security.ntlm.Client;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import modelo.Personal;
import modelo.Producto;
import modelo.ProductoPromocion;
import modelo.Promocion;
import modelo.TipoPersonal;
import modelo.TipoProducto;
import modelo.TipoUsuario;
import modelo.Usuario;

public class DAO {

    public final Conexion C;
    private String sql;
    private List<Producto> productos;
    
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
                + "1"
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
                + "tipoUsuario_estado = " + tipo.getEstado() + " "
                + "where "
                + "tipoUsuario_id = '" + tipo.getId() + "';";
        C.ejecutar(sql);
    }

    public void desactivarTipoUsuario(int id) throws SQLException {
        sql = "update tipoUsuario set "
                + "tipoUsuario_estado = 0 "
                + "where "
                + "tipoUsuario_id = '" + id + "';";
        C.ejecutar(sql);
    }

    public void activarTipoUsuario(int id) throws SQLException {
        sql = "update tipoUsuario set "
                + "tipoUsuario_estado = 1 "
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
    
    //========================================================================
    
    /*
     Inicio Métodos Usuario
     */
    
    public void crearUsuario(Usuario u) throws SQLException {
        sql = "INSERT INTO usuario VALUES("
                + "null,"
                + "'" + u.getNombre() + "',"
                + "AES_ENCRYPT('"+u.getPassword()+"',666),"
                + "'" + u.getTipoUsuario() + "',"
                + "1"
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
    
    public Usuario getUltimoUsuario() throws SQLException {
        Usuario user = null;
        sql = "SELECT * FROM usuario";
        C.resultado = C.ejecutarSelect(sql);
        while (C.resultado.next()) {
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
                + "usuario_estado = " + u.getEstado() + " "
                + "where "
                + "usuario_id = '" + u.getId() + "';";
        C.ejecutar(sql);
    }
    
    public void actualizarNombreUsuario(String nombre, int id) throws SQLException {
        sql = "update usuario set "
                + "usuario_nombre = '" + nombre + "'"
                + "where "
                + "usuario_id = '" + id + "';";
        C.ejecutar(sql);
    }

    public void desactivarUsuario(int id) throws SQLException {
        sql = "update usuario set "
                + "usuario_estado = 0 "
                + "where "
                + "usuario_id = '" + id + "';";
        C.ejecutar(sql);
    }

    public void activarUsuario(int id) throws SQLException {
        sql = "update usuario set "
                + "usuario_estado = 1 "
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
    
    //========================================================================
    
    /*
     Inicio Métodos tipoPersonal
     */
    
    public void crearTipoPersonal(TipoPersonal tipo) throws SQLException {
        sql = "INSERT INTO tipoPersonal VALUES("
                + "NULL,"
                + "'" + tipo.getNombre() + "',"
                + "1"
                + ");";
        C.ejecutar(sql);
    }

    public TipoPersonal getTipoPersonal(int id) throws SQLException {
        TipoPersonal tipo = null;
        sql = "SELECT * FROM tipoPersonal WHERE tipoPersonal_id = '" + id + "'";
        C.resultado = C.ejecutarSelect(sql);
        if (C.resultado.next()) {
            tipo = new TipoPersonal(C.resultado.getInt(1), C.resultado.getString(2), C.resultado.getInt(3));
        }
        C.sentencia.close();
        return tipo;
    }

    public List<TipoPersonal> getTipoPersonalLike(String arg) throws SQLException {
        sql = "SELECT * FROM tipoPersonal WHERE "
                + "tipoPersonal_nombre like '%" + arg + "%' "
                + "OR "
                + "tipoPersonal_id like '%" + arg + "%';";
        C.resultado = C.ejecutarSelect(sql);
        TipoPersonal tipo;
        List<TipoPersonal> listaTipo = new ArrayList<>();
        while (C.resultado.next()) {
            tipo = new TipoPersonal();
            tipo.setId(C.resultado.getInt(1));
            tipo.setNombre(C.resultado.getString(2));
            tipo.setEstado(C.resultado.getInt(3));
            listaTipo.add(tipo);
        }
        C.sentencia.close();
        return listaTipo;
    }

    public void actualizarTipoPersonal(TipoPersonal tipo) throws SQLException {
        sql = "UPDATE tipoPersonal SET "
                + "tipoPersonal_nombre = '" + tipo.getNombre() + "', "
                + "tipoPersonal_estado = " + tipo.getEstado() + " "
                + "WHERE "
                + "tipoPersonal_id = '" + tipo.getId() + "';";
        C.ejecutar(sql);
    }

    public void desactivarTipoPersonal(int id) throws SQLException {
        sql = "UPDATE tipoPersonal SET "
                + "tipoPersonal_estado = 0 "
                + "WHERE "
                + "tipoPersonal_id = '" + id + "';";
        C.ejecutar(sql);
    }

    public void activarTipoPersonal(int id) throws SQLException {
        sql = "UPDATE tipoPersonal SET "
                + "tipoPersonal_estado = 1 "
                + "WHERE "
                + "tipoPersonal_id = '" + id + "';";
        C.ejecutar(sql);
    }

    public void eliminarTipoPersonal(int id) throws SQLException {
        sql = "DELETE FROM tipoPersonal WHERE tipoPersonal_id = '" + id + "';";
        C.ejecutar(sql);
    }

    public List<TipoPersonal> getTiposPersonal() throws SQLException {
        sql = "SELECT * FROM tipoPersonal;";
        C.resultado = C.ejecutarSelect(sql);
        TipoPersonal tipo;
        List<TipoPersonal> listaTipo = new ArrayList<>();
        while (C.resultado.next()) {
            tipo = new TipoPersonal();
            tipo.setId(C.resultado.getInt(1));
            tipo.setNombre(C.resultado.getString(2));
            tipo.setEstado(C.resultado.getInt(3));
            listaTipo.add(tipo);
        }
        C.sentencia.close();
        return listaTipo;
    }

    /*
     Fin Métodos tipoPersonal
     */
    
    //========================================================================
    
    /*
     Inicio Métodos personal
     */
    
    public void crearPersonal(Personal p) throws SQLException {
        sql = "INSERT INTO personal VALUES("
                + "NULL,'" + p.rut + "',"
                + "'" + p.nombre + "',"
                + "'" + p.apellidos + "',"
                + "'" + p.tipo + "',"
                + "'" + p.usuario + "',"
                + "1"
                + ");";
        C.ejecutar(sql);
    }

    public Personal getPersonalPorId(String id) throws SQLException {
        Personal per = null;
        sql = "SELECT * FROM personal WHERE personal_id = '" + id + "'";
        C.resultado = C.ejecutarSelect(sql);
        if (C.resultado.next()) {
            per = new Personal(
                    C.resultado.getInt(1),
                    C.resultado.getString(2),
                    C.resultado.getString(3),
                    C.resultado.getString(4),
                    C.resultado.getInt(5),
                    C.resultado.getInt(6),
                    C.resultado.getInt(7)
            );
        }
        C.sentencia.close();
        return per;
    }

    /*Para buscar, no para validar con pass*/
    public List<Personal> getPersonalLike(String arg) throws SQLException {
        sql = "SELECT * FROM personal WHERE "
                + "personal_nombre like '%" + arg + "%' "
                + "OR "
                + "personal_rut like '%" + arg + "%' "
                + "OR "
                + "personal_apellidos like '%" + arg + "%';";
        C.resultado = C.ejecutarSelect(sql);
        Personal p;
        List<Personal> li = new ArrayList<>();
        while (C.resultado.next()) {
            p = new Personal();
            p.setId(C.resultado.getInt(1));
            p.setRut(C.resultado.getString(2));
            p.setNombre(C.resultado.getString(3));
            p.setApellidos(C.resultado.getString(4));
            p.setTipo(C.resultado.getInt(5));
            p.setUsuario(C.resultado.getInt(6));
            p.setEstado(C.resultado.getInt(7));
            li.add(p);
        }
        C.sentencia.close();
        return li;
    }
    
    public void actualizarPersonalNombApell(Personal p) throws SQLException {
        sql = "UPDATE personal SET "
                + "personal_nombre = '" + p.getNombre() + "', "
                + "personal_apellidos = '" + p.getApellidos() + "' "
                + "WHERE "
                + "personal_id = '" + p.getId() + "';";
        C.ejecutar(sql);
    }

    public void actualizarPersonal(Personal p) throws SQLException {
        sql = "UPDATE personal SET "
                + "personal_rut = '" + p.getRut() + "', "
                + "personal_nombre = '" + p.getNombre() + "', "
                + "personal_apellidos = '" + p.getApellidos() + "', "
                + "personal_tipo = '" + p.getTipo() + "', "
                + "personal_usuario = '" + p.getUsuario() + "', "
                + "personal_estado = " + p.getEstado() + " "
                + "WHERE "
                + "personal_id = '" + p.getId() + "';";
        C.ejecutar(sql);
    }

    public void desactivarPersonal(int id) throws SQLException {
        sql = "UPDATE personal SET "
                + "personal_estado = 0 "
                + "where "
                + "personal_id = '" + id + "';";
        C.ejecutar(sql);
    }

    public void activarPersonal(int id) throws SQLException {
        sql = "UPDATE personal SET "
                + "personal_estado = 1 "
                + "where "
                + "personal_id = '" + id + "';";
        C.ejecutar(sql);
    }

    public void eliminarPersonal(int id) throws SQLException {
        sql = "DELETE FROM personal WHERE personal_id = '" + id + "';";
        C.ejecutar(sql);
    }

    public List<Personal> getPersonales() throws SQLException {
        sql = "SELECT * FROM personal WHERE personal_estado = 1;";
        C.resultado = C.ejecutarSelect(sql);
        Personal p;
        List<Personal> li = new ArrayList<>();
        while (C.resultado.next()) {
            p = new Personal();
            p.setId(C.resultado.getInt(1));
            p.setRut(C.resultado.getString(2));
            p.setNombre(C.resultado.getString(3));
            p.setApellidos(C.resultado.getString(4));
            p.setTipo(C.resultado.getInt(5));
            p.setUsuario(C.resultado.getInt(6));
            p.setEstado(C.resultado.getInt(7));
            li.add(p);
        }
        C.sentencia.close();
        return li;
    }

    /*
     Fin Métodos personal
     */
    
    //========================================================================
    
    /*
     Inicio Métodos cliente
     */
    
    public void crearCliente(Cliente cli) throws SQLException {
        sql = "INSERT INTO cliente VALUES("
                + "NULL,'" + cli.fono + "',"
                + "'" + cli.nombre + "',"
                + "'" + cli.apellido + "',"
                + "'" + cli.nacimiento + "',"
                + "1"
                + ");";
        C.ejecutar(sql);
    }

    public Cliente getClientePorFono(String id) throws SQLException {
        Cliente cl = null;
        sql = "SELECT * FROM cliente WHERE cliente_fono = '" + id + "')";
        C.resultado = C.ejecutarSelect(sql);
        if (C.resultado.next()) {
            cl = new Cliente(
                    C.resultado.getString(1),
                    C.resultado.getString(2),
                    C.resultado.getString(3),
                    C.resultado.getDate(4),
                    C.resultado.getInt(5)
            );
        }
        C.sentencia.close();
        return cl;
    }

    /*Para buscar, no para validar con pass*/
    public List<Cliente> getClienteLike(String arg) throws SQLException {
        sql = "SELECT * FROM cliente WHERE "
                + "cliente_fono like '%" + arg + "%' "
                + "OR "
                + "cliente_nombre like '%" + arg + "%' "
                + "OR "
                + "cliente_apellido like '%" + arg + "%';";
        C.resultado = C.ejecutarSelect(sql);
        Cliente cl;
        List<Cliente> li = new ArrayList<>();
        while (C.resultado.next()) {
            cl = new Cliente();
            cl.setFono(C.resultado.getString(1));
            cl.setNombre(C.resultado.getString(2));
            cl.setApellido(C.resultado.getString(3));
            cl.setNacimiento(C.resultado.getDate(4));
            cl.setEstado(C.resultado.getInt(5));
            li.add(cl);
        }
        C.sentencia.close();
        return li;
    }

    public void actualizarCliente(Cliente cli) throws SQLException {
        sql = "UPDATE cliente SET "
                + "cliente_nombre = '" + cli.getNombre() + "', "
                + "cliente_apellido = '" + cli.getApellido() + "', "
                + "cliente_nacimiento = '" + cli.getNacimiento() + "', "
                + "cliente_estado = " + cli.getEstado() + " "
                + "WHERE "
                + "cliente_fono = '" + cli.getFono() + "';";
        C.ejecutar(sql);
    }

    public void desactivarCliente(int fono) throws SQLException {
        sql = "UPDATE cliente SET "
                + "cliente_estado = 0 "
                + "where "
                + "cliente_fono = '" + fono + "';";
        C.ejecutar(sql);
    }

    public void activarCliente(int fono) throws SQLException {
        sql = "UPDATE cliente SET "
                + "cliente_estado = 1 "
                + "where "
                + "cliente_fono = '" + fono + "';";
        C.ejecutar(sql);
    }

    public void eliminarCliente(int fono) throws SQLException {
        sql = "DELETE FROM cliente WHERE cliente_fono = '" + fono + "';";
        C.ejecutar(sql);
    }

    public List<Cliente> getClietes() throws SQLException {
        sql = "SELECT * FROM cliente;";
        C.resultado = C.ejecutarSelect(sql);
        Cliente cl;
        List<Cliente> li = new ArrayList<>();
        while (C.resultado.next()) {
            cl = new Cliente();
            cl.setFono(C.resultado.getString(1));
            cl.setNombre(C.resultado.getString(2));
            cl.setApellido(C.resultado.getString(3));
            cl.setNacimiento(C.resultado.getDate(4));
            cl.setEstado(C.resultado.getInt(5));
            li.add(cl);
        }
        C.sentencia.close();
        return li;
    }

    /*
     Fin Métodos cliente
     */
    
    //========================================================================
    
    /*
     Inicio Métodos tipoProducto
     */
    
    public void crearTipoProducto(TipoProducto tipo) throws SQLException {
        sql = "INSERT INTO tipoProducto VALUES("
                + "NULL,"
                + "'" + tipo.getNombre() + "',"
                + "1"
                + ");";
        C.ejecutar(sql);
    }

    public TipoProducto getTipoProducto(int id) throws SQLException {
        TipoProducto tipo = null;
        sql = "SELECT * FROM tipoProducto WHERE tipoProducto_id = '" + id + "'";
        C.resultado = C.ejecutarSelect(sql);
        if (C.resultado.next()) {
            tipo = new TipoProducto(C.resultado.getInt(1), C.resultado.getString(2), C.resultado.getInt(3));
        }
        C.sentencia.close();
        return tipo;
    }

    public List<TipoProducto> getTipoProductoLike(String arg) throws SQLException {
        sql = "SELECT * FROM tipoProducto WHERE "
                + "tipoProducto_id like '%" + arg + "%' "
                + "OR "
                + "tipoProducto_nombre like '%" + arg + "%';";
        C.resultado = C.ejecutarSelect(sql);
        TipoProducto tipo;
        List<TipoProducto> listaTipo = new ArrayList<>();
        while (C.resultado.next()) {
            tipo = new TipoProducto();
            tipo.setId(C.resultado.getInt(1));
            tipo.setNombre(C.resultado.getString(2));
            tipo.setEstado(C.resultado.getInt(3));
            listaTipo.add(tipo);
        }
        C.sentencia.close();
        return listaTipo;
    }

    public void actualizarTipoProducto(TipoProducto tipo) throws SQLException {
        sql = "UPDATE tipoProducto SET "
                + "tipoProducto_nombre = '" + tipo.getNombre() + "', "
                + "tipoProducto_estado = " + tipo.getEstado() + " "
                + "WHERE "
                + "tipoProducto_id = '" + tipo.getId() + "';";
        C.ejecutar(sql);
    }

    public void desactivarTipoProducto(int id) throws SQLException {
        sql = "UPDATE tipoProducto SET "
                + "tipoProducto_estado = 0 "
                + "WHERE "
                + "tipoProducto_id = '" + id + "';";
        C.ejecutar(sql);
    }

    public void activarTipoProducto(int id) throws SQLException {
        sql = "UPDATE tipoProducto SET "
                + "tipoProducto_estado = 1 "
                + "WHERE "
                + "tipoProducto_id = '" + id + "';";
        C.ejecutar(sql);
    }

    public void eliminarTipoProducto(int id) throws SQLException {
        sql = "DELETE FROM tipoProducto WHERE tipoProducto_id = '" + id + "';";
        C.ejecutar(sql);
    }

    public List<TipoProducto> getTiposProductos() throws SQLException {
        sql = "SELECT * FROM tipoProducto WHERE tipoProducto_estado = 1";
        C.resultado = C.ejecutarSelect(sql);
        TipoProducto tipo;
        List<TipoProducto> listaTipo = new ArrayList<>();
        while (C.resultado.next()) {
            tipo = new TipoProducto();
            tipo.setId(C.resultado.getInt(1));
            tipo.setNombre(C.resultado.getString(2));
            tipo.setEstado(C.resultado.getInt(3));
            listaTipo.add(tipo);
        }
        C.sentencia.close();
        return listaTipo;
    }

    /*
     Fin Métodos tipoProducto
     */
    
    //========================================================================
    
    
    /*
     Inicio Métodos producto
     */
    public List<Producto> getProductos() throws SQLException{
        productos = new ArrayList<>();
        sql = "SELECT * FROM producto WHERE producto_estado = 1";
        C.resultado = C.ejecutarSelect(sql);
        Producto p;
        while (C.resultado.next()) {
            p = new Producto();
            p.setId(C.resultado.getInt(1));
            p.setNombre(C.resultado.getString(2));
            p.setPrecio(C.resultado.getInt(3));
            p.setEstado(C.resultado.getInt(4));
            productos.add(p);
        }
        C.sentencia.close();
        return productos;
    }
    
    public void crearProducto(Producto p) throws SQLException {
        sql = "INSERT INTO producto VALUES("
                + "NULL,"
                + "'" + p.getNombre() + "',"
                + "'" + p.getPrecio() + "',"
                + "'" + p.getTipo() + "',"
                + "1"
                + ");";
        C.ejecutar(sql);
    }
     
     public void eliminarProducto(String id) throws SQLException{
         sql = "UPDATE producto SET producto_estado = 0 WHERE producto_id = "+id+"";
         C.ejecutar(sql);
     }
     
     public List<Producto> getProductosLike(String filtro) throws SQLException{
        productos = new ArrayList<>();
        sql = "SELECT * FROM producto WHERE producto_estado = 1 and producto_nombre like '%"+filtro+"%'";
        C.resultado = C.ejecutarSelect(sql);
        Producto p;
        while (C.resultado.next()) {
            p = new Producto();
            p.setId(C.resultado.getInt(1));
            p.setNombre(C.resultado.getString(2));
            p.setPrecio(C.resultado.getInt(3));
            p.setEstado(C.resultado.getInt(4));
            productos.add(p);
        }
        C.sentencia.close();
        return productos;
    }
     
     public Producto getProducto(int id) throws SQLException {
        Producto pr = null;
        sql = "SELECT * FROM producto WHERE producto_id = '" + id + "'";
        C.resultado = C.ejecutarSelect(sql);
        if (C.resultado.next()) {
            pr = new Producto(C.resultado.getInt(1), C.resultado.getString(2), C.resultado.getInt(3), C.resultado.getInt(4), C.resultado.getInt(5));
        }
        C.sentencia.close();
        return pr;
    }
     
    /*
     Fin Métodos producto
     */
    
    //========================================================================
    
    /*
     Inicio Métodos promocion
     */
    
    public void crearPromocion(Promocion p) throws SQLException {
        sql = "INSERT INTO promocion VALUES("
                + "NULL,"
                + "'" + p.getNombre() + "',"
                + "'" + p.getDescuento() + "',"
                + "1"
                + ");";
        C.ejecutar(sql);
    }
    
    public Promocion getUltimaPromocion() throws SQLException {
        Promocion prom = null;
        sql = "SELECT * FROM promocion";
        C.resultado = C.ejecutarSelect(sql);
        while (C.resultado.next()) {
            prom = new Promocion(
                    C.resultado.getInt(1),
                    C.resultado.getString(2),
                    C.resultado.getInt(3),
                    C.resultado.getInt(4)
            );
        }
        C.sentencia.close();
        return prom;
    }
    
    
    /*
     Fin Métodos promocion
     */
    
    //========================================================================
    
    /*
     Inicio Métodos productoPromocion
     */
    
    public void crearProductoPromocion(ProductoPromocion p) throws SQLException {
        sql = "INSERT INTO productoPromocion VALUES("
                + "NULL,"
                + "'" + p.getIdPromocion() + "',"
                + "'" + p.getIdProducto()+ "',"
                + "1"
                + ");";
        C.ejecutar(sql);
    }
    
    /*
     Fin Métodos productoPromocion
     */
    
    //========================================================================
    
    /*
     Inicio Métodos venta
     */
    
    
    
    /*
     Fin Métodos venta
     */
    
    //========================================================================
    
    /*
     Inicio Métodos productoVenta
     */
    
    
    
    /*
     Fin Métodos productoVenta
     */
    
}
