package bd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import modelo.Personal;
import modelo.Producto;
import modelo.ProductoPromocion;
import modelo.ProductoVenta;
import modelo.Promocion;
import modelo.PromocionVenta;
import modelo.Respaldo;
import modelo.TipoPersonal;
import modelo.TipoProducto;
import modelo.TipoUsuario;
import modelo.Usuario;
import modelo.Venta;

public class DAO {

    public final Conexion C;
    private String sql;
    private List<Producto> productos;
    private List<Promocion> promociones;
    private List<ProductoPromocion> productoPromociones;
    private List<Respaldo> respaldos;

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
                + "NULL,"
                + "'" + u.nombre + "',"
                + "AES_ENCRYPT('" + u.password + "',666),"
                + "'" + u.tipoUsuario + "',"
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
                + "usuario_clave = AES_ENCRYPT('" + u.getPassword() + "',666), "
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
    
    public String getPasswordDec(int id) throws SQLException{
        sql = "select AES_DECRYPT(usuario_clave,'666') from usuario where usuario_id = '"+id+"';";
        C.resultado = C.ejecutarSelect(sql);
        String pass = null;
        if(C.resultado.next()){
            pass = C.resultado.getString(1);
        }
        C.sentencia.close();
        return pass;
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

    public Personal getPersonalSegunRutUsuario(String rutUsu) throws SQLException {
        Personal per = null;
        sql = "SELECT * FROM personal WHERE personal_rut = '" + rutUsu + "'";
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

    public Personal getPersonalSegunIdUsuario(int idUsu) throws SQLException {
        Personal per = null;
        sql = "SELECT * FROM personal WHERE personal_usuario = '" + idUsu + "'";
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
                + "(personal_nombre like '%" + arg + "%' "
                + "OR "
                + "personal_rut like '%" + arg + "%' "
                + "OR "
                + "personal_apellidos like '%" + arg + "%') AND personal_estado = 1;";
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
        sql = "INSERT INTO cliente VALUES('" + cli.fono + "','" + cli.nombre + "','" + cli.apellido + "',1);";
        C.ejecutar(sql);
    }

    public Cliente getClientePorFono(String id) throws SQLException {
        Cliente cl = null;
        sql = "SELECT * FROM cliente WHERE cliente_fono = '" + id + "'";
        C.resultado = C.ejecutarSelect(sql);
        if (C.resultado.next()) {
            cl = new Cliente(
                    C.resultado.getString(1),
                    C.resultado.getString(2),
                    C.resultado.getString(3),
                    C.resultado.getInt(4)
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
            cl.setEstado(C.resultado.getInt(4));
            li.add(cl);
        }
        C.sentencia.close();
        return li;
    }

    public void actualizarCliente(Cliente cli) throws SQLException {
        sql = "UPDATE cliente SET "
                + "cliente_nombre = '" + cli.getNombre() + "', "
                + "cliente_apellido = '" + cli.getApellido() + "', "
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
            cl.setEstado(C.resultado.getInt(4));
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
    public List<Producto> getProductos() throws SQLException {
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

    public void eliminarProducto(String id) throws SQLException {
        sql = "UPDATE producto SET producto_estado = 0 WHERE producto_id = " + id + "";
        C.ejecutar(sql);
    }

    public List<Producto> getProductosLike(String filtro) throws SQLException {
        productos = new ArrayList<>();
        sql = "SELECT * FROM producto WHERE producto_estado = 1 and producto_nombre like '%" + filtro + "%'";
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

    public List<Promocion> getPromociones() throws SQLException {
        promociones = new ArrayList<>();
        sql = "SELECT * FROM promocion WHERE promocion_estado = 1";
        C.resultado = C.ejecutarSelect(sql);
        Promocion p;
        while (C.resultado.next()) {
            p = new Promocion();
            p.setId(C.resultado.getInt(1));
            p.setNombre(C.resultado.getString(2));
            p.setDescuento(C.resultado.getInt(3));
            p.setEstado(C.resultado.getInt(4));
            promociones.add(p);
        }
        C.sentencia.close();
        return promociones;
    }

    public Promocion getPromocionSegunId(String id) throws SQLException {
        sql = "select * from promocion where promocion_id = '" + id + "'";
        C.resultado = C.ejecutarSelect(sql);
        Promocion p = null;
        if (C.resultado.next()) {
            p = new Promocion();
            p.setId(C.resultado.getInt(1));
            p.setNombre(C.resultado.getString(2));
            p.setDescuento(C.resultado.getInt(3));
            p.setEstado(C.resultado.getInt(4));
        }
        C.sentencia.close();
        return p;
    }

    public void eliminarPromocion(String id) throws SQLException {
        sql = "UPDATE promocion SET promocion_estado = 0 WHERE promocion_id = " + id + "";
        C.ejecutar(sql);
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
                + "'" + p.getIdProducto() + "',"
                + "1"
                + ");";
        C.ejecutar(sql);
    }

    public List<ProductoPromocion> getProductoPromocion(int id) throws SQLException {
        productoPromociones = new ArrayList<>();
        sql = "SELECT * FROM productoPromocion WHERE productoPromocion_estado = 1 AND productoPromocion_idPromocion = '" + id + "'";
        C.resultado = C.ejecutarSelect(sql);
        ProductoPromocion pp;
        while (C.resultado.next()) {
            pp = new ProductoPromocion();
            pp.setId(C.resultado.getInt(1));
            pp.setIdPromocion(C.resultado.getInt(2));
            pp.setIdProducto(C.resultado.getInt(3));
            pp.setEstado(C.resultado.getInt(4));
            productoPromociones.add(pp);
        }
        C.sentencia.close();
        return productoPromociones;
    }

    /*
     Fin Métodos productoPromocion
     */
    //========================================================================
    /*
     Inicio Métodos venta
     */
    public void crearVenta(Venta v) throws SQLException {
        sql = "insert into venta values(null,'" + v.fecha + "','" + v.personal + "','" + v.cliente + "','" + v.total + "',1);";
        C.ejecutar(sql);
    }

    public Venta getVenta(int id) throws SQLException {
        sql = "select * from venta where venta_id = '" + id + "'";
        C.resultado = C.ejecutarSelect(sql);
        Venta v = null;
        if (C.resultado.next()) {
            v = new Venta();
            v.setId(C.resultado.getInt(1));
            v.setFecha(C.resultado.getDate(2));
            v.setPersonal(C.resultado.getInt(3));
            v.setCliente(C.resultado.getString(4));
            v.setEstado(C.resultado.getInt(5));
            v.setEstado(C.resultado.getInt(6));
        }
        C.sentencia.close();
        return v;
    }

    public List<Venta> getVentaLike(String arg) throws SQLException {
        sql = "select * from venta where "
                + "venta_personal like '%" + arg + "%' "
                + "or "
                + "venta_cliente like '%" + arg + "%';";
        C.resultado = C.ejecutarSelect(sql);
        Venta v;
        List<Venta> lv = new ArrayList<>();
        while (C.resultado.next()) {
            v = new Venta();
            v.setId(C.resultado.getInt(1));
            v.setFecha(C.resultado.getDate(2));
            v.setPersonal(C.resultado.getInt(3));
            v.setCliente(C.resultado.getString(4));
            v.setEstado(C.resultado.getInt(5));
            v.setEstado(C.resultado.getInt(6));
            lv.add(v);
        }
        C.sentencia.close();
        return lv;
    }

    public Venta getUltimaVenta() throws SQLException {
        Venta ven = null;
        sql = "SELECT * FROM venta";
        C.resultado = C.ejecutarSelect(sql);
        while (C.resultado.next()) {
            ven = new Venta(
                    C.resultado.getInt(1),
                    C.resultado.getDate(2),
                    C.resultado.getInt(3),
                    C.resultado.getString(4),
                    C.resultado.getInt(5),
                    C.resultado.getInt(6)
            );
        }
        C.sentencia.close();
        return ven;
    }

    public void desactivarVenta(int id) throws SQLException {
        sql = "update venta set "
                + "venta_estado = 0 "
                + "where "
                + "venta_id = '" + id + "';";
        C.ejecutar(sql);
    }

    public void activarVenta(int id) throws SQLException {
        sql = "update venta set "
                + "venta_estado = 1 "
                + "where "
                + "venta_id = '" + id + "';";
        C.ejecutar(sql);
    }

    public void eliminarVenta(int id) throws SQLException {
        sql = "delete from venta where venta_id = '" + id + "';";
        C.ejecutar(sql);
    }

    public List<Venta> getVentas() throws SQLException {
        sql = "select * from venta;";
        C.resultado = C.ejecutarSelect(sql);
        Venta v;
        List<Venta> lv = new ArrayList<>();
        while (C.resultado.next()) {
            v = new Venta();
            v.setId(C.resultado.getInt(1));
            v.setFecha(C.resultado.getDate(2));
            v.setPersonal(C.resultado.getInt(3));
            v.setCliente(C.resultado.getString(4));
            v.setTotal(C.resultado.getInt(5));
            v.setEstado(C.resultado.getInt(6));
            lv.add(v);
        }
        C.sentencia.close();
        return lv;
    }

    public List<Venta> getVentasPorFechaUnica(String fecha) throws SQLException {
        sql = "SELECT * FROM venta WHERE venta_fecha = '" + fecha + "';";
        C.resultado = C.ejecutarSelect(sql);
        Venta v;
        List<Venta> lv = new ArrayList<>();
        while (C.resultado.next()) {
            v = new Venta();
            v.setId(C.resultado.getInt(1));
            v.setFecha(C.resultado.getDate(2));
            v.setPersonal(C.resultado.getInt(3));
            v.setCliente(C.resultado.getString(4));
            v.setTotal(C.resultado.getInt(5));
            v.setEstado(C.resultado.getInt(6));
            lv.add(v);
        }
        C.sentencia.close();
        return lv;
    }

    public List<Venta> getVentasPorRangoDeFecha(String fechaDesde, String fechaHasta) throws SQLException {
        sql = "SELECT * FROM venta WHERE venta_fecha BETWEEN '" + fechaDesde + "' AND '" + fechaHasta + "'";
        C.resultado = C.ejecutarSelect(sql);
        Venta v;
        List<Venta> lv = new ArrayList<>();
        while (C.resultado.next()) {
            v = new Venta();
            v.setId(C.resultado.getInt(1));
            v.setFecha(C.resultado.getDate(2));
            v.setPersonal(C.resultado.getInt(3));
            v.setCliente(C.resultado.getString(4));
            v.setTotal(C.resultado.getInt(5));
            v.setEstado(C.resultado.getInt(6));
            lv.add(v);
        }
        C.sentencia.close();
        return lv;
    }

    /*
     Fin Métodos venta
     */
    //========================================================================
    /*
     Inicio Métodos productoVenta
     */
    public void crearProductoVenta(ProductoVenta pv) throws SQLException {
        sql = "insert into productoVenta values("
                + "null,"
                + "'" + pv.getProducto() + "',"
                + "'" + pv.getVenta() + "',"
                + "1"
                + ");";
        C.ejecutar(sql);
    }

    public ProductoVenta getProductoVenta(int id) throws SQLException {
        sql = "select * from productoVenta where venta_id = '" + id + "'";
        C.resultado = C.ejecutarSelect(sql);
        ProductoVenta pv = null;
        if (C.resultado.next()) {
            pv = new ProductoVenta();
            pv.setId(C.resultado.getInt(1));
            pv.setProducto(C.resultado.getInt(2));
            pv.setVenta(C.resultado.getInt(3));
            pv.setEstado(C.resultado.getInt(4));
        }
        C.sentencia.close();
        return pv;
    }

    public List<ProductoVenta> getProductoVentaLike(String arg) throws SQLException {
        sql = "select * from tipoUsuario where "
                + "productoVenta_producto like '%" + arg + "%' "
                + "or "
                + "productoVenta_venta like '%" + arg + "%';";
        C.resultado = C.ejecutarSelect(sql);
        ProductoVenta pv;
        List<ProductoVenta> lpv = new ArrayList<>();
        while (C.resultado.next()) {
            pv = new ProductoVenta();
            pv.setId(C.resultado.getInt(1));
            pv.setProducto(C.resultado.getInt(2));
            pv.setVenta(C.resultado.getInt(3));
            pv.setEstado(C.resultado.getInt(4));
            lpv.add(pv);
        }
        C.sentencia.close();
        return lpv;
    }

    public void desactivarProductoVenta(int id) throws SQLException {
        sql = "update productoVenta set "
                + "productoVenta_estado = 0 "
                + "where "
                + "productoVenta_id = '" + id + "';";
        C.ejecutar(sql);
    }

    public void activarProductoVenta(int id) throws SQLException {
        sql = "update productoVenta set "
                + "productoVenta_estado = 1 "
                + "where "
                + "productoVenta_id = '" + id + "';";
        C.ejecutar(sql);
    }

    public void eliminarProductoVenta(int id) throws SQLException {
        sql = "delete from productoVenta where productoVenta_id = '" + id + "';";
        C.ejecutar(sql);
    }

    public List<ProductoVenta> getProductosVenta() throws SQLException {
        sql = "select * from productoVenta;";
        C.resultado = C.ejecutarSelect(sql);
        ProductoVenta pv;
        List<ProductoVenta> lpv = new ArrayList<>();
        while (C.resultado.next()) {
            pv = new ProductoVenta();
            pv.setId(C.resultado.getInt(1));
            pv.setProducto(C.resultado.getInt(2));
            pv.setVenta(C.resultado.getInt(3));
            pv.setEstado(C.resultado.getInt(4));
            lpv.add(pv);
        }
        C.sentencia.close();
        return lpv;
    }

    /*
     Fin Métodos productoVenta
     */
    //========================================================================
    /*
     Inicio Métodos promocionVenta
     */
    public void crearPromocionVenta(PromocionVenta pv) throws SQLException {
        sql = "insert into promocionVenta values("
                + "null,"
                + "'" + pv.getPromocion() + "',"
                + "'" + pv.getVenta() + "',"
                + "1"
                + ");";
        C.ejecutar(sql);
    }

    /*
     Fin Métodos promocionVenta
     */
    //========================================================================
    /*
     Inicio Métodos productoVenta
     */
 /*
     Fin Métodos productoVenta
     */
 /*Prueba*/
    public void cambiarMaster() throws SQLException {
        sql = "USE mysql";
        C.ejecutar(sql);
    }

    public void generarBackUpPersonalizado(Respaldo r) throws SQLException {
        sql = "INSERT INTO autoBackup VALUES (NULL, '" + r.getFecha() + "', '" + r.getTipo() + "')";
        C.ejecutar(sql);
    }

    public Respaldo getBackUpAutomatico() throws SQLException {
        Respaldo r = null;
        sql = "SELECT * FROM autoBackup";
        C.resultado = C.ejecutarSelect(sql);
        if (C.resultado.next()) {
            r = new Respaldo();
            r.setId(C.resultado.getInt(1));
            r.setFecha(C.resultado.getString(2));
            r.setTipo(C.resultado.getString(3));
        }
        return r;
    }

    public void actualizarBackupAutomaticoDiario(String fechaFinal) throws SQLException {
        sql = "UPDATE autoBackup SET fecha = '" + fechaFinal + "'";
        C.ejecutar(sql);
    }

    /*
     Inicio Métodos respaldo
     */
    public void crearRespaldo(Respaldo r) throws SQLException {
        sql = "INSERT INTO respaldo VALUES(NULL, '" + r.getFecha() + "', '" + r.getHora() + "', 0)";
        C.ejecutar(sql);
    }

    public List<Respaldo> getRespaldos() throws SQLException {
        respaldos = new ArrayList<>();
        sql = "SELECT * FROM respaldo";
        C.resultado = C.ejecutarSelect(sql);

        while (C.resultado.next()) {
            Respaldo r = new Respaldo(C.resultado.getInt(1), C.resultado.getString(2), C.resultado.getString(3));
            respaldos.add(r);
        }
        C.sentencia.close();
        return respaldos;
    }

    public Respaldo getRespaldo(String id) throws SQLException {
        Respaldo r = null;
        sql = "SELECT * FROM respaldo WHERE respaldo_id = '" + id + "'";
        C.resultado = C.ejecutarSelect(sql);

        if (C.resultado.next()) {
            r = new Respaldo(C.resultado.getInt(1), C.resultado.getString(2), C.resultado.getString(3));
        }
        C.sentencia.close();
        return r;
    }

    public void insertRespaldos(List<Respaldo> res) throws SQLException {
        C.ejecutar("TRUNCATE TABLE respaldo");
        for (Respaldo r : res ) {
            C.ejecutar("INSERT INTO respaldo VALUES (NULL, '"+r.getFecha()+"', '"+r.getHora()+"', '"+r.getTipo()+"')");
        }
    }

    /*
     Fin Métodos respaldo
     */
}
