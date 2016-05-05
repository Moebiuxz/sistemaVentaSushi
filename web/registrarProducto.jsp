<%@page import="modelo.TipoProducto"%>
<%@page import="bd.DAO"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Producto"%>
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
    StaticPage.PAGINA = EnumPaginas.CREAR_PRODUCTO;
    DAO d = new DAO();
    List<TipoProducto> tipos = d.getTiposProductos();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Producto</title>
        <link rel="stylesheet" href="css/style_InicioSesion.css" type="text/css"/>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="icon" type="image/png" href="images/icon.png" />
    </head>
    <body>
        <header>
            <%@include file="elimReg.jsp" %>
            <%@include file="navBar.jsp" %>
        </header>
        <div class="container" >
            <div class="row" >
                <div class="col-md-6" >
                    <h2>Registrar Producto</h2>
                    <form action="registrarProducto.do" method="POST">
                        <div class="form-group">
                            <label for="nombre">Nombre:</label>
                            <input name="txtNombre" type="text" class="form-control" id="nombre" placeholder="Nombre">
                        </div>
                        <div class="form-group">
                            <label for="nombre">Precio:</label>
                            <input name="txtPrecio" type="number" class="form-control" id="pass" placeholder="Precio">
                        </div>
                        <label for="producto">Producto:</label>
                        <div class="form-group">
                            <select class="form-control" name="cboProducto">
                                <%  for (TipoProducto tp : tipos) {
                                        out.println("<option value='" + tp.getId() + "'>" + tp.getNombre() + "</option>");
                                    }
                                %>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-default pull-right">Registrar</button>
                    </form>
                    <div style="margin-top: 60px; ">
                        <%
                            String m = request.getParameter("m");
                            if (m != null) {
                                out.println("<div class='alert alert-success' role='alert'>El producto ha sido registrado.</div>");
                            }
                        %>
                    </div>

                </div>
            </div>
        </div>
    </body>
</html>
