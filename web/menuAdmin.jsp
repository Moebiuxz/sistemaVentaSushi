<%@page import="modelo.TipoPersonal"%>
<%@page import="modelo.Personal"%>
<%@page import="java.util.List"%>

<%@page import="bd.DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario us = (Usuario)request.getSession().getAttribute("usuario");
    if(us == null){
        response.sendRedirect("index.jsp");
    }
    StaticPage.PAGINA = EnumPaginas.LISTAR_PERSONAL;
        DAO d = new DAO();
        List<Personal> personss;
        String nomBus = request.getParameter("txtBuscar");
        if (nomBus == null) {
            personss = d.getPersonales();
        } else {
            personss = d.getPersonalLike(nomBus);
        }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menú</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/estilos.css"/>
    </head>
    <body>
        <header>
            <%@include file="navBar.jsp"%>
        </header>
        <div class="container" >
            <div class="row" >
                <div class="col-md-6" >
                    <h2>Buscar Personal</h2>
                    <form action="menuAdmin.jsp" method="POST">
                        <label for="producto">Buscar:</label>
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
                            <th>Apellidos</th>
                            <th>Oficio</th>
                            <th>Actualizar</th>
                            <th>Eliminar</th>
                        </tr>
                        <%                            
                            for (Personal per : personss) {
                                DAO dd = new DAO();
                                TipoPersonal tp = dd.getTipoPersonal(per.getTipo());
                                out.println("<tr>");
                                out.println("<td>" + per.getRut()+ "</td>");
                                out.println("<td>" + per.getNombre() + "</td>");
                                out.println("<td>" + per.getApellidos()+ "</td>");
                                out.println("<td>" + tp.getNombre()+ "</td>");
                                out.println("<td><a class='imaGG' href='editarPersonal.jsp?r="+per.getId()+"'></a>Editar</td>");
                                out.println("<td></td>");
                                out.println("</tr>");
                            }
                        %>
                    </table>
                    
                    <h2>Crear de Personal</h2>
                    <a class="btn btn-default" role="button" href="crearPersonal.jsp">Crear Personal</a>
                    
                </div>
            </div>
        </body>
            </html>
