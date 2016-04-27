<%@page import="modelo.TipoProducto"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Producto"%>
<%@page import="bd.DAO"%>
<%@page import="otros.StaticPage"%>
<%@page import="otros.EnumPaginas"%>
<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    Usuario us = (Usuario) request.getSession().getAttribute("usuario");
    if (us == null) {
        response.sendRedirect("index.jsp");
    }
    StaticPage.PAGINA = EnumPaginas.LISTAR_PRODUCTOS;
    DAO d = new DAO();
    List<Producto> productos = d.getProductos();
    
    if(request.getParameter("txtBuscar") != null){
        if(!request.getParameter("txtBuscar").equalsIgnoreCase("")){
            productos = d.getProductosLike(request.getParameter("txtBuscar"));
        }
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar Productos</title>
        <link rel="stylesheet" href="css/style_InicioSesion.css" type="text/css"/>
        <link rel="stylesheet" href="css/bootstrap.min.css">
    </head>
    <body>
        <header>
            <%@include file="elimReg.jsp" %>
            <%@include file="navBar.jsp" %>
        </header>
        <div class="container" >
            <div class="row" >
                <div class="col-md-6" >
                    <h2>Buscar Producto</h2>
                    <form action="listarProducto.jsp" method="POST">
                        <label for="producto">Buscar:</label>
                        <div class="form-group">
                            <input name="txtBuscar" type="text" class="form-control" id="nombre" placeholder="Buscar">
                        </div>

                        <button type="submit" class="btn btn-default pull-right">Buscar</button>
                    </form>
                    <br><br><br>
                    <h2>Lista de Productos</h2>
                    <table class="table table-bordered" style="margin-top: 30px;">
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Precio</th>
                            
                            <th>Eliminar</th>
                        </tr>
                        <%
                        for(Producto p : productos){
                            out.println("<tr>"); 
                                out.println("<td>"+p.getId()+"</td>");
                                out.println("<td>"+p.getNombre()+"</td>");
                                out.println("<td>"+p.getPrecio()+"</td>");
                                TipoProducto tp = d.getTipoProducto(p.getTipo());
                                //out.println("<td>"+tp.getNombre()+"</td>");
                                out.println("<td><a href='confirmarEliminar.jsp?e="+p.getId()+"'>Eliminar</a></td>");
                            out.println("</tr>");
                        }
                        %>
                    </table>
                </div>
            </div>
    </body>
</html>
