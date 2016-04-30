<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Producto"%>
<%@page import="bd.DAO"%>
<%@page import="otros.PromEstatica"%>
<%@page import="otros.StaticPage"%>
<%@page import="otros.EnumPaginas"%>
<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario us = (Usuario) request.getSession().getAttribute("usuario");
    if (us == null) {
        response.sendRedirect("index.jsp");
    }
    StaticPage.PAGINA = EnumPaginas.CREAR_PROMOCION;

    String nombre = request.getParameter("txtNombre");
    String descu = request.getParameter("txtDescuento");
    String idProduc = request.getParameter("cboProducto");
    
    if (nombre != null && descu != null) {
        PromEstatica.NOMBRE = nombre;
        PromEstatica.DESCUENTO = descu;
    }
    
    if(idProduc != null){
        DAO d = new DAO();
        Producto p = d.getProducto(Integer.parseInt(idProduc)); 
        Producto pro = new Producto(p.id, p.nombre, p.precio, p.tipo, p.estado);
        
        PromEstatica.LI.add(pro); 
        
    }

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Promocion</title>
        <link rel="stylesheet" href="css/style_InicioSesion.css" type="text/css"/>
        <link rel="stylesheet" href="css/bootstrap.min.css">
    </head>
    <body>
        <header>
            <%@include file="navBar.jsp" %>
        </header>
        <div class="container" >
            <div class="row" >
                <div class="col-md-6" >
                    <h2>Crear Promocion</h2>
                    <%if (PromEstatica.NOMBRE == null && PromEstatica.DESCUENTO == null) {
                    %>

                    <form action="crearPromocion.jsp" method="POST">
                        <div class="form-group">
                            <label for="nombre">Nombre de promocion:</label>
                            <input name="txtNombre" type="text" class="form-control" id="nombre" placeholder="Nombre: " required="required">
                        </div>
                        <div class="form-group">
                            <label for="nombre">Descuento: </label>
                            <input name="txtDescuento" type="number" class="form-control" id="nombre" placeholder="Descuento: " required="required">
                        </div>
                        <button type="submit" class="btn btn-default pull-right">Aceptar</button>
                    </form>   
                    <%
                    } else {%>
                    <h4>Nombre Promocion: <%out.println(PromEstatica.NOMBRE);%></h4>
                    <h4>Descuento: <%out.println(PromEstatica.DESCUENTO);%>%</h4>
                    <form action="crearPromocion.jsp" method="POST">
                        <label for="producto">Producto:</label>
                        <div class="form-group">
                            <select class="form-control" name="cboProducto">
                                <%
                                    DAO d = new DAO();
                                    List<Producto> produ = new ArrayList<>();
                                    produ = d.getProductos();
                                    for (Producto p : produ) {
                                        if(p.id != 1){
                                            out.println("<option value='" + p.getId() + "'>" + p.getNombre() + "</option>");
                                        }
                                    }
                                %>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-default pull-right">Agregar</button>
                    </form>

                    <%
                        if (PromEstatica.LI != null) {
                            int total = 0;
                            int des = 0;
                            int totalTotal = 0;
                    %>
                    <table class="table table-bordered" style="margin-top: 30px;">
                        <tr>
                            <th>Nombre</th>
                            <th>Precio</th>
                        </tr>
                        <%                                
                            for (Producto pro : PromEstatica.LI) {
                                out.println("<tr>");
                                out.println("<td>" + pro.getNombre() + "</td>");
                                out.println("<td>" + pro.getPrecio() + "</td>");
                                out.println("</tr>");
                                total = total + pro.precio;
                                }
                        des = total*Integer.parseInt(PromEstatica.DESCUENTO)/100; 
                        totalTotal = total-des;
                        %>
                    </table>
                    <table class="table table-bordered" style="margin-top: 30px;">
                        <tr>
                            <th>Total: </th>
                            <th>$<%out.println(total);%></th>
                        </tr>
                        <tr>
                            <th>Descuento: </th>
                            <th>$<%out.println(des);%></th>
                        </tr>
                        <tr>
                            <th>Total Con Descuento:</th>
                            <th>$<%out.println(totalTotal);%></th>
                        </tr>
                    </table>
                    <%
                            }
                        }
                    
                    if(PromEstatica.LI.size() != 0){%>
                       <a class="btn btn-default" role="button" href="registrarPromocion.do">Crear Promocion</a>
                    <%
                    }
                    %>
                    
                    <br/><br/><br/>
                </div>
            </div>
        </div>
    </body>
</html>
