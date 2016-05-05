<%@page import="java.util.ArrayList"%>
<%@page import="modelo.ProductoPromocion"%>
<%@page import="modelo.Promocion"%>
<%@page import="modelo.TipoProducto"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Producto"%>
<%@page import="bd.DAO"%>
<%@page import="modelo.Cliente"%>
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
    Cliente cli;

    String fono = request.getParameter("txtFono");
    String opPP = request.getParameter("cboPP");
    String rr = request.getParameter("rr");
    String listaProductos = request.getParameter("lisP");
    String listaPromocion = request.getParameter("lisPP");
    String eliminarProducto = request.getParameter("elimP");
    String eliminatPromocion = request.getParameter("elimPP");

    if (eliminarProducto != null) {
        DAO d = new DAO();
        Producto p = d.getProducto(Integer.parseInt(eliminarProducto));
        for (Producto pp : PromEstatica.LIPRO) {
            if (pp.id == Integer.parseInt(eliminarProducto)) {
                PromEstatica.LIPRO.remove(pp);
                break;
            }
        }
    }

    if (eliminatPromocion != null) {
        DAO d = new DAO();
        Promocion p = d.getPromocionSegunId(eliminatPromocion);
        for (Promocion pp : PromEstatica.LIPP) {
            if (pp.id == Integer.parseInt(eliminatPromocion)) {
                PromEstatica.LIPP.remove(pp);
                break;
            }
        }
    }

    if (listaProductos != null) {
        DAO dd = new DAO();
        Producto p = dd.getProducto(Integer.parseInt(listaProductos));
        Producto pro = new Producto(p.id, p.nombre, p.precio, p.tipo, p.estado);
        PromEstatica.LIPRO.add(pro);
    }

    if (listaPromocion != null) {
        DAO dd = new DAO();
        Promocion p = dd.getPromocionSegunId(listaPromocion);
        Promocion pr = new Promocion(p.id, p.nombre, p.descuento, p.estado);
        PromEstatica.LIPP.add(pr);
    }

    if (rr != null) {
        PromEstatica.ESTA_FONO = false;
        PromEstatica.NOMBRE = null;
        PromEstatica.LIPRO = new ArrayList<>();
        PromEstatica.LIPP = new ArrayList<>();
        PromEstatica.FONO_CLIENTE = null;
    }
    DAO d = new DAO();

    if (fono != null) {
        cli = d.getClientePorFono(fono);
        if (cli == null) {
            response.sendRedirect("crearCliente.jsp?r=" + fono + "");
        } else {
            PromEstatica.ESTA_FONO = true;
            PromEstatica.FONO_CLIENTE = fono;
        }
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Usuario</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/estilos.css"/>
        <link rel="icon" type="image/png" href="images/icon.png" />
    </head>
    <body>
        <div class="container" >
            <h1>Menu vendedor: <%out.println(us.nombre);%></h1>
            <div class="row" >
                <div class="col-md-12" >
                    <div class="col-md-5" >
                        <%
                            int totalTotal = 0;
                            if (PromEstatica.ESTA_FONO == false) {
                        %>
                        <form action="menuUsuario.jsp" method="post">
                            <h4>Buscar Cliente</h4><br/>
                            <input class="form-control" name="txtFono" placeholder="Fono cliente: " type="text" required="required"/><br/>
                            <input class="btn btn-primary" name="btnCrearPersonal" type="submit" value="Buscar"><br/><br/>
                        </form>
                        <%
                            } else {
                                int totalProduc = 0, totalProm = 0, ttt = 0;
                                cli = d.getClientePorFono(PromEstatica.FONO_CLIENTE);
                                out.println("<hr>");
                                out.println("<h4>Cliente: " + cli.nombre + " " + cli.apellido + "<h4>");
                                if (PromEstatica.LIPRO.size() != 0) {

                                    out.println("<table class='table table-bordered' style='margin-top: 15px;'>");
                                    out.println("<td colspan='4'>Productos:</td>");
                                    out.println("<tr>");
                                    out.println("<th>Nombre</th>");
                                    out.println("<th>Precio</th>");
                                    out.println("<th>Eliminar</th>");
                                    out.println("</tr>");
                                    List<Producto> productos = PromEstatica.LIPRO;
                                    for (Producto p : productos) {
                                        out.println("<tr>");
                                        out.println("<td>" + p.getNombre() + "</td>");
                                        out.println("<td>$" + p.getPrecio() + "</td>");
                                        out.println("<td><a href='menuUsuario.jsp?elimP=" + p.getId() + "'>Eliminar</a></td>");
                                        out.println("</tr>");
                                        totalProduc = totalProduc + p.precio;
                                    }
                                    out.println("<tr>");
                                    out.println("<th>Total Productos: </th>");
                                    out.println("<th>$" + totalProduc + "</th>");
                                    out.println("</tr>");
                                    out.println("</table>");
                                }
                                if (PromEstatica.LIPP.size() != 0) {

                                    out.println("<table class='table table-bordered' style='margin-top: 15px;'>");
                                    out.println("<td colspan='3'>Promociones: </td>");
                                    out.println("<tr>");
                                    out.println("<th>Nombre</th>");
                                    out.println("<th>Precio</th>");
                                    out.println("<th>Eliminar</th>");
                                    out.println("</tr>");
                                    List<Promocion> promociones = PromEstatica.LIPP;
                                    int totProm = 0, desc = 0, tt=0;
                                    for (Promocion p : promociones) {
                                        int promoci = 0;
                                        desc = p.descuento;
                                        out.println("<tr>");
                                        out.println("<td>" + p.getNombre() + "</td>");
                                        List<ProductoPromocion> listPP = d.getProductoPromocion(p.id);
                                        for (ProductoPromocion pp : listPP) {
                                            Producto pro = d.getProducto(pp.idProducto);
                                            promoci = promoci + pro.precio;
                                        }
                                        totProm = promoci*desc/100;
                                        tt = promoci - totProm;
                                        out.println("<td>$" + tt + "</td>");
                                        out.println("<td><a href='menuUsuario.jsp?elimPP=" + p.getId() + "'>Eliminar</a></td>");
                                        out.println("</tr>");
                                        totalProm = totalProm + tt;
                                    }
                                    
                                    out.println("<tr>");
                                    out.println("<th>Total Promociones : </th>");
                                    out.println("<th>$" + totalProm + "</th>");
                                    out.println("</tr>");
                                    out.println("</table>");

                                }
                                out.println("<table class='table table-bordered' style='margin-top: 40px;'>");
                                out.println("<tr>");
                                totalTotal = totalProm + totalProduc;
                                out.println("<th>Total A Pagar: </th>");
                                out.println("<th>$ " + totalTotal + "</th>");
                                out.println("</tr>");
                                out.println("</table>");
                            }
                        %>
                        <a class="btn btn-danger" role="button" href="menuUsuario.jsp?rr=1">Limpiar</a>
                        <%
                            if (PromEstatica.LIPRO.size() != 0 || PromEstatica.LIPP.size() != 0) {%>
                        <a class="btn btn-primary" role="button" href="registrarVenta.do?tot=<%out.println(totalTotal);%>">Aceptar Venta</a>
                        <%}%>
                    </div>
                    <div class="col-md-1"></div>
                    <div class="col-md-5" >
                        <form action="menuUsuario.jsp" method="POST">
                            Buscar: <select class="form-control" name="cboPP">
                                <option value=1>Productos</option>
                                <option value=2>Promociones</option>
                            </select><br/>
                            <input class="btn btn-primary" name="btnBuscar" value="Buscar" type="submit">
                        </form>

                        <%
                            if (opPP != null && opPP.equals("1")) {
                                out.println("<table class='table table-bordered' style='margin-top: 30px;'>");
                                out.println("<tr>");
                                out.println("<th>ID</th>");
                                out.println("<th>Nombre</th>");
                                out.println("<th>Precio</th>");
                                if (PromEstatica.ESTA_FONO == true) {
                                    out.println("<th>Agregar</th>");
                                }
                                out.println("</tr>");
                                List<Producto> productos = d.getProductos();
                                for (Producto p : productos) {
                                    if (p.id == 1) {
                                    } else {
                                        out.println("<tr>");
                                        out.println("<td>" + p.getId() + "</td>");
                                        out.println("<td>" + p.getNombre() + "</td>");
                                        out.println("<td>" + p.getPrecio() + "</td>");
                                        if (PromEstatica.ESTA_FONO == true) {
                                            out.println("<td><a href='menuUsuario.jsp?lisP=" + p.getId() + "&cboPP=1'>Agregar</a></td>");
                                        }
                                        out.println("</tr>");
                                    }
                                }
                                out.println("</table>");
                            } else if (opPP != null && opPP.equals("2")) {

                                List<Promocion> promociones = d.getPromociones();
                                for (Promocion p : promociones) {
                                    if (p.id == 1) {
                                    } else {
                        %>
                        <h3><%out.println(p.nombre);%>
                            <%
                                if (PromEstatica.ESTA_FONO == true) {
                                    out.println("<a class='btn btn-default' role='button' href='menuUsuario.jsp?cboPP=2&lisPP=" + p.id + "'>Agregar promocion</a>");
                                }
                            %>
                        </h3>
                        <table class="table table-bordered" style="margin-top: 30px;">
                            <tr>
                                <th>Producto</th>
                                <th>Precio</th>
                                <th>Descuento <%out.println(p.descuento);%>%</th>
                            </tr>
                            <%
                                int precioTotal = 0, precioTotalCnDesc = 0;
                                List<ProductoPromocion> productoPromociones = d.getProductoPromocion(p.id);
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

                        <%
                                    }
                                }

                            }
                        %>

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
