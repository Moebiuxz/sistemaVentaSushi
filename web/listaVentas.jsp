<%@page import="modelo.Cliente"%>
<%@page import="modelo.Personal"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Venta"%>
<%@page import="bd.DAO"%>
<%@page import="otros.StaticPage"%>
<%@page import="otros.EnumPaginas"%>
<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Usuario us = (Usuario)request.getSession().getAttribute("usuario");
    if(us == null){
        response.sendRedirect("index.jsp");
    }
    StaticPage.PAGINA = EnumPaginas.LISTA_VENTAS;
        DAO d = new DAO();
    List<Venta> ventas = new ArrayList<>();
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Ventas</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/estilos.css"/>
    </head>
    <body>
        <header>
            <%@include file="elimReg.jsp" %>
            <%@include file="navBar.jsp"%>
        </header>
        <div class="container" >
            <div class="row" >
                <div class="col-md-8" >
                    <div class="col-md-6">
                    <h2>Buscar Personal</h2>
                    <form action="menuAdmin.jsp" method="POST">
                        <label for="producto">Buscar:</label>
                        <div class="form-group">
                            <input name="txtBuscar" type="text" class="form-control" id="nombre" placeholder="Buscar">
                        </div>

                        <button type="submit" class="btn btn-default pull-right">Buscar</button>
                        
                    </form>
                    </div>
                    <div class="col-md-10" >
                        <table class="table table-bordered" style="margin-top: 30px;">
                            <tr>
                                <th>ID</th>
                                <th>Fecha</th>
                                <th>Personal</th>
                                <th>Cliente</th>
                                <th>Total</th>
                                <th>Ver</th>
                            </tr>
                            <%
                                ventas = d.getVentas();
                                for (Venta v : ventas) {
                                    Personal per = d.getPersonalPorId(Integer.toString(v.personal));
                                    Cliente cli = d.getClientePorFono(v.cliente);
                                        out.println("<tr>");
                                        out.println("<td>" + v.id + "</td>");
                                        out.println("<td>" + v.fecha + "</td>");
                                        out.println("<td>" + per.nombre + "</td>");
                                        out.println("<td> " + cli.nombre +" "+cli.apellido +"</td>");
                                        out.println("<td>$ " + v.total + "</td>"); 
                                        out.println("<td><a href=''>Ver Venta</a></td>");

                                        out.println("</tr>");
                                }
                                out.println("<tr>");
                                out.println("<th></th>");
                                out.println("<th>Precio total $</th>");
                                out.println("</tr>");
                            %>
                        </table>
                        <div class="clearfix visible-xs-block"></div>
                    </div>
                </div>
            </div>
        </div>
        
        
        
        
    </body>
</html>
