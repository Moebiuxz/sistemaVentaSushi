<%@page import="modelo.Producto"%>
<%@page import="modelo.ProductoPromocion"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Promocion"%>
<%@page import="bd.DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario us = (Usuario) request.getSession().getAttribute("usuario");
    if (us == null) {
        response.sendRedirect("index.jsp");
    }
    StaticPage.PAGINA = EnumPaginas.LISTA_PROMOCION;

    DAO d = new DAO();
    List<Promocion> promociones = new ArrayList<>();
    List<ProductoPromocion> productoPromociones = new ArrayList<>();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista De Promociones</title>
        <link rel="stylesheet" href="css/style_InicioSesion.css" type="text/css"/>
        <link rel="stylesheet" href="css/bootstrap.min.css">
    </head>
    <body>
        <%@include file="navBar.jsp" %>
        <div class="container" >
            <div class="row" >
                <h2>Lista de Promociones</h2>
                <div class="col-md-10" >
                    <%                        promociones = d.getPromociones();
                        for (Promocion p : promociones) {
                            if (p.id == 1) {
                            } else {
                    %>
                    <div class="col-md-10" >

                        <h3><%out.println(p.nombre);%><a class="btn btn-danger" role="button" href="eliminarPromocion.do?r=<%out.println(p.id);%>">Eliminar promocion</a></h3>
                        <table class="table table-bordered" style="margin-top: 30px;">
                            <tr>
                                <th>Producto</th>
                                <th>Precio</th>
                                <th>Descuento <%out.println(p.descuento);%>%</th>
                            </tr>
                            <%
                                int precioTotal = 0, precioTotalCnDesc = 0;
                                productoPromociones = d.getProductoPromocion(p.id);
                                for (ProductoPromocion pp : productoPromociones) {
                                    Producto pro = d.getProducto(pp.idProducto);
                                    int totalConDescuento = pro.precio * p.descuento / 100;
                                    int totalDes = pro.precio - totalConDescuento;
                                    out.println("<tr>");
                                    out.println("<td>" + pro.nombre + "</td>");
                                    out.println("<td>$ " + pro.precio + "</td>");
                                    out.println("<td>$ " + totalDes + "</td>");
                                    out.println("</tr>");
                                    precioTotal = precioTotal + pro.precio;
                                    precioTotalCnDesc = precioTotalCnDesc + totalDes;
                                }
                                out.println("<tr>");
                                out.println("<th></th>");
                                out.println("<th>$ " + precioTotal + "</th>");
                                out.println("<th>Precio total $ " + precioTotalCnDesc + "</th>");
                                out.println("</tr>");
                            %>
                        </table>
                        <div class="clearfix visible-xs-block"></div>
                    </div>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
        </div>
    </body>
</html>
