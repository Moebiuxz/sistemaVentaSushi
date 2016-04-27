<%@page import="bd.DAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="otros.EnumPaginas"%>
<%@page import="otros.StaticPage"%>
<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario us = (Usuario) request.getSession().getAttribute("usuario");
    if (us == null) {
        response.sendRedirect("index.jsp");
    }
    StaticPage.PAGINA = EnumPaginas.LISTAR_USUARIOS;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar Usuarios</title>
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
                    <h2>Buscar Usuario</h2>
                    <form action="filtrarUsuario.do" method="POST">
                        <label for="usuario">Buscar:</label>
                        <div class="form-group">
                            <input name="txtBuscar" type="text" class="form-control" id="nombre" placeholder="Buscar">
                        </div>

                        <button type="submit" class="btn btn-default pull-right">Buscar</button>
                    </form>
                    <br><br><br>
                    <h2>Lista de Personal</h2>
                    <table class="table table-bordered" style="margin-top: 30px;">
                        <tr>
                            <th>RUT</th>
                            <th>Nombre</th>
                            <th>Password</th>
                            <th>Actualizar</th>
                            <th>Eliminar</th>
                        </tr>
                        <%                            
                            DAO d = new DAO();
                            for (Usuario user : d.getUsuarios()) {
                                if (user.getEstado() == 1) {
                        %>
                        <tr>
                            <td>
                                <%
                                    out.println("");
                                %>
                            </td>
                            <td>
                                <%
                                    out.println(user.getNombre());
                                %>
                            </td>
                            <td>
                                <%
                                    out.println(user.getPassword());
                                %>
                            </td>
                            <td>
                                <%
                                    out.println("<a href='actualizarUsuario.jsp?id="+user.getId()+"'>Actualizar</a>");
                                %>
                            </td>
                            <td>
                                <%
                                    out.println("<a href='eliminarUsuario.do?id="+user.getId()+"'>Eliminar</a>");
                                %>
                            </td>
                        </tr>
                        <%
                                }
                            }
                        %>                        
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
