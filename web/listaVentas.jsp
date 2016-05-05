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
    Usuario us = (Usuario) request.getSession().getAttribute("usuario");
    if (us == null) {
        response.sendRedirect("index.jsp");
    }
    StaticPage.PAGINA = EnumPaginas.LISTA_VENTAS;
    DAO d = new DAO();
    List<Venta> ventas = new ArrayList<>();

    String fechaUnica = request.getParameter("txtBuscarFechaUnica");
    String fechaInicio = request.getParameter("txtfechaInicio");
    String fechaFin = request.getParameter("txtfechaFin");

    if (fechaUnica != null) {
        ventas = d.getVentasPorFechaUnica(fechaUnica);
    } else if (fechaInicio != null || fechaFin != null) {
        ventas = d.getVentasPorRangoDeFecha(fechaInicio, fechaFin);
    } else {
        ventas = d.getVentas();
    }


%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Ventas</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/estilos.css"/>
        <link rel="icon" type="image/png" href="images/icon.png" />
    </head>
    <body>
        <header>
            <%@include file="elimReg.jsp" %>
            <%@include file="navBar.jsp"%>
        </header>
        <div class="container" >
            <div class="row" >
                <div class="col-md-11" >
                    <div class="col-md-4">
                        <h2>Buscar Personal</h2>
                        <form action="listaVentas.jsp" method="POST">
                            <label for="producto">Buscar por fecha unica:</label>
                            <div class="form-group">
                                <input name="txtBuscarFechaUnica" type="date" class="form-control" id="nombre">
                            </div>
                            <a class="btn btn-group" role="button" href="listaVentas.jsp">Mostrar todo</a>
                            <button type="submit" class="btn btn-default pull-right">Buscar</button>
                        </form>
                        
                    </div>
                    <div class="col-md-1"></div>
                    <div class="col-md-4">
                        <form action="listaVentas.jsp" method="POST">
                            <h2></h2>
                            <label for="producto">Buscar por rango:</label>
                            <div class="form-group">
                                <h5>Desde: </h5>
                                <input name="txtfechaInicio" type="date" class="form-control" id="nombre">
                                <h5>Hasta: </h5>
                                <input name="txtfechaFin" type="date" class="form-control" id="nombre">
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
                            int totalTotal = 0;
                                for (Venta v : ventas) {
                                    Personal per = d.getPersonalPorId(Integer.toString(v.personal));
                                    Cliente cli = d.getClientePorFono(v.cliente);
                                    out.println("<tr>");
                                    out.println("<td>" + v.id + "</td>");
                                    out.println("<td>" + v.fecha + "</td>");
                                    out.println("<td>" + per.nombre + "</td>");
                                    out.println("<td> " + cli.nombre + " " + cli.apellido + "</td>");
                                    out.println("<td>$ " + v.total + "</td>");
                                    out.println("<td><a href='verVenta.jsp?r="+v.id+"'>Ver Venta</a></td>");
                                    out.println("</tr>");
                                    totalTotal = totalTotal + v.total;
                                }
                                out.println("<tr>");
                                out.println("<th colspan='4'>Total vendido</th>");
                                out.println("<th> $ " + totalTotal + "</th>");
                                out.println("<th></th>");
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
