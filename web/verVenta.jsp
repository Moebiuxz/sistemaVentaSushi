<%@page import="modelo.ProductoPromocion"%>
<%@page import="modelo.PromocionVenta"%>
<%@page import="modelo.ProductoVenta"%>
<%@page import="modelo.Personal"%>
<%@page import="modelo.Cliente"%>
<%@page import="bd.DAO"%>
<%@page import="modelo.Promocion"%>
<%@page import="modelo.Producto"%>
<%@page import="modelo.Venta"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Usuario us = (Usuario) request.getSession().getAttribute("usuario");
    if (us == null) {
        response.sendRedirect("index.jsp");
    }
    DAO d = new DAO();
    
    String idVenta = request.getParameter("r");
    
    List<ProductoVenta> productosV = new ArrayList<>();
    List<PromocionVenta> promocionesV = new ArrayList<>();
    List<ProductoPromocion> productoPromociones = new ArrayList<>();
    List<Promocion> promociones = new ArrayList<>();
    Venta v = null;
    if(idVenta != null){
        v = d.getVenta(Integer.parseInt(idVenta));
    }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ver Venta</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/estilos.css"/>
    </head>
    <body>
        <div class="container" >
            <div class="row" >
                <div class="col-md-11" >
                    <div class="col-md-6">
                        <%
                        Cliente cli = d.getClientePorFono(v.cliente);
                        Personal per = d.getPersonalPorId(Integer.toString(v.personal));
                        out.println("<h1>Venta N° : "+v.id+"</h1>");
                        out.println("<h1>Fecha : "+v.fecha+"</h1>");
                        out.println("<h4>Total : "+v.total+"</h4>");
                        out.println("<h2>Vendedor: "+per.nombre+"</h2>");
                        out.println("<h2>Cliente: "+cli.nombre+" "+cli.apellido+"</h2>");
                        out.println("<h4>N° de cliente: "+cli.fono+"</h4>");
                        out.println("<hr>");
                        out.println("<h2>Detalle :</h2>");
                        
                        productosV = d.getProductoPorIdList(v.id);
                        
                        out.println("<table class='table table-bordered' style='margin-top: 30px;'>");
                        out.println("<tr>");
                            out.println("<th>ID</th>");
                            out.println("<th>Nombre</th>");
                            out.println("<th>Precio</th>");
                        out.println("</tr>");
                        
                        for(ProductoVenta pv : productosV){
                                Producto prod = d.getProducto(pv.producto); 
                                if(prod.id != 1){
                                    out.println("<tr>");
                                    out.println("<td>" + prod.id + "</td>");
                                    out.println("<td>" + prod.nombre + "</td>");
                                    out.println("<td>" + prod.precio + "</td>");
                                    out.println("</tr>");
                                }
                        }         
                               
                        out.println("</table>");
                        
                        promocionesV = d.getPromocionVenPorIdList(v.id);
                        
                        for(PromocionVenta pvv : promocionesV){
                                promociones = d.getPromocionesSegId(pvv.promocion);
                                
                                for (Promocion p : promociones) { 
                                
                                out.println("<div class='col-md-11' >");
                                out.println("<table class='table table-bordered' style='margin-top: 30px;'>");
                                
                                    out.println("<tr>");
                                        out.println("<th>Producto</th>");
                                        out.println("<th>Precio</th>");
                                        out.println("<th>Descuento</th>");
                                    out.println("</tr>");
                                
                                out.println("</tr>");
                                
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
                                
                                
                                
                                
                                out.println("</table>");
                                out.println("</div>");
                                }
                        }
                        
                        %>
                        <h2>Total $<%out.println(v.total);%></h2>
                        <br>
                        <a class="btn btn-danger" role="button" href="listaVentas.jsp">Volver</a>
                        <br><br><br><br>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
