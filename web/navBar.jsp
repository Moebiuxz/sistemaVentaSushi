<%@page import="otros.EnumPaginas"%>
<%@page import="otros.StaticPage"%>
<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario u = (Usuario) request.getSession().getAttribute("usuario");
    String menu = "";
    String listarPersonal = "";
    String listarUsuarios = "";
    String listarProductos = "";
    String crearProducto = "";
    String crearPromocion = "";
    String listaPromocion = "";
    String listaVentas = "";
    String reportes = "";
    String respaldo = "";
    String pass = "";

    if (StaticPage.PAGINA == EnumPaginas.MENU) {
        menu = "active";
    } else if (StaticPage.PAGINA == EnumPaginas.LISTAR_PERSONAL) {
        listarPersonal = "active";
    } else if (StaticPage.PAGINA == EnumPaginas.LISTAR_USUARIOS) {
        listarUsuarios = "active";
    } else if (StaticPage.PAGINA == EnumPaginas.LISTAR_PRODUCTOS) {
        listarProductos = "active";
    } else if (StaticPage.PAGINA == EnumPaginas.CREAR_PRODUCTO) {
        crearProducto = "active";
    } else if (StaticPage.PAGINA == EnumPaginas.CREAR_PROMOCION) {
        crearPromocion = "active";
    } else if (StaticPage.PAGINA == EnumPaginas.LISTA_PROMOCION) {
        listaPromocion = "active";
    } else if (StaticPage.PAGINA == EnumPaginas.LISTA_VENTAS) {
        listaVentas = "active";
    } else if (StaticPage.PAGINA == EnumPaginas.RESPALDO) {
        respaldo = "active";
    } else if (StaticPage.PAGINA == EnumPaginas.REPORTES) {
        reportes = "active";
    } else if (StaticPage.PAGINA == EnumPaginas.PASS) {
        pass = "active";
    }
%>
<!DOCTYPE html>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <!-- <a class="navbar-brand" href="menu.jsp">Inicio</a> -->
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <%
                    if (u != null && u.getTipoUsuario() == 1) {
                        //out.println("<li class='"+menu+"'><a href='menu.jsp'>Ingresar Producción<span class='sr-only'>(current)</span></a></li>");
                        out.println("<li class='" + listarPersonal + "'><a href='menuAdmin.jsp'>Listar Personal</a></li>");
                        //out.println("<li class='"+listarUsuarios+"'><a href='listarUsuarios.jsp'>Listar Usuarios</a></li>");   
                        out.println("<li class='" + crearProducto + "'><a href='registrarProducto.jsp'>Crear Producto</a></li>");
                        out.println("<li class='" + listarProductos + "'><a href='listarProducto.jsp'>Listar Productos</a></li>");
                        out.println("<li class='" + crearPromocion + "'><a href='crearPromocion.jsp'>Crear Promocion</a></li>");
                        out.println("<li class='" + listaPromocion + "'><a href='listaPromociones.jsp'>Listar Promociones</a></li>");
                        out.println("<li class='" + listaVentas + "'><a href='listaVentas.jsp'>Listar Ventas</a></li>");
                        out.println("<li class='" + reportes + "'><a href='reportes.jsp'>Reportes</a></li>");
                        out.println("<li class='" + respaldo + "'><a href='respaldo.jsp'>Respaldar</a></li>");
                        out.println("<li class='" + pass + "'><a href='cambiarPass.jsp'>Cambiar Contraseña</a></li>");
                    } else if (u != null && u.getTipoUsuario() == 2) {
                        out.println("<li class='" + pass + "'><a href='cambiarPass.jsp'>Cambiar Contraseña</a></li>");
                    }
                %>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="cerrarSesion.do">Cerrar Sesión</a></li>
            </ul>
        </div>
    </div>
</nav>
