<%@page import="otros.EnumPaginas"%>
<%@page import="otros.StaticPage"%>
<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
Usuario u = (Usuario)request.getSession().getAttribute("usuario");
String cocinero = "";
String menu = "";
String crearUsuario = "";
String listarPersonal = "";
String listarUsuarios = "";
String listarProductos = "";
String crearProducto = "";



if(StaticPage.PAGINA == EnumPaginas.MENU){
    menu = "active";
}else if(StaticPage.PAGINA == EnumPaginas.CREAR_COCINERO){
    cocinero = "active";
}else if(StaticPage.PAGINA == EnumPaginas.CREAR_USUARIO){
    crearUsuario = "active";
}else if(StaticPage.PAGINA == EnumPaginas.LISTAR_PERSONAL){
    listarPersonal = "active";
}else if(StaticPage.PAGINA == EnumPaginas.LISTAR_USUARIOS){
    listarUsuarios = "active";
}else if(StaticPage.PAGINA == EnumPaginas.LISTAR_PRODUCTOS){
    listarUsuarios = "active";
}else if(StaticPage.PAGINA == EnumPaginas.CREAR_PRODUCTO){
    listarUsuarios = "active";
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
                if(u != null && u.getTipoUsuario() == 1){
                    //out.println("<li class='"+menu+"'><a href='menu.jsp'>Ingresar Producción<span class='sr-only'>(current)</span></a></li>");
                    out.println("<li class='"+listarPersonal+"'><a href='menuAdmin.jsp'>Listar Personal</a></li>");
                    out.println("<li class='"+cocinero+"'><a href=''>Crear Cocinero</a></li>");
                    out.println("<li class='"+crearUsuario+"'><a href='registrarUsuario.jsp'>Registrar Usuario</a></li>");   
                    out.println("<li class='"+listarUsuarios+"'><a href='listarUsuarios.jsp'>Listar Usuarios</a></li>");   
                    out.println("<li class='"+crearProducto+"'><a href='registrarProducto.jsp'>Crear Producto</a></li>");   
                    out.println("<li class='"+listarUsuarios+"'><a href='listarProducto.jsp'>Listar Productos</a></li>");   
                }else if(u != null && u.getTipoUsuario() == 2){
                    
                }
                %>
                
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="cerrarSesion.do">Cerrar Sesión</a></li>
            </ul>
        </div>
    </div>
</nav>
