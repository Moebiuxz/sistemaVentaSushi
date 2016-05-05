<%@page import="java.util.List"%>
<%@page import="modelo.Producto"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="modelo.Venta"%>
<%@page import="bd.DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Contenido</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/estilos.css"/>
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
                    <h2>
                        Crear Venta
                    </h2>
                    <%                        String id = request.getParameter("txtId");
                        String nombre = request.getParameter("txtNombre");
                        String cliente = request.getParameter("cboCliente");
                        DAO d = new DAO();
                        Venta v = new Venta();

                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        Calendar cal = Calendar.getInstance();
                        String fechaHora = dateFormat.format(cal.getTime());

                        v.setFecha(fechaHora);
                        v.setPersonal(Integer.parseInt(id));
                        v.setCliente(cliente);
                        v.setEstado(1);
                        d.crearVenta(v);
                    %>
                    <form method="post" action="completarVenta.jsp">
                        <div class="form-group">
                            <label for="id">ID Vendedor:</label>
                            <input name="txtId" type="text" class="form-control" id="id" value="<%out.println(id);%>" readonly>
                        </div>
                        <div class="form-group">
                            <label for="nombre">Vendedor:</label>
                            <input name="txtNombre" type="text" class="form-control" id="nombre" value="<%out.println(nombre);%>" readonly>
                        </div>
                        <div class="form-group">
                            <label for="cliente">Cliente:</label>
                            <input name="txtCliente" type="text" class="form-control" id="cliente" value="<%out.println(cliente);%>" readonly>
                        </div>
                        <label for="producto">Producto:</label>
                        <div class="form-group">
                            <select class="form-control" name="cboProducto" id="producto">
                                <%
                                    List<Producto> produ = new ArrayList<>();
                                    produ = d.getProductos();
                                    for (Producto p : produ) {
                                        out.println("<option value='" + p.getId() + "'>" + p.getNombre() + "</option>");
                                    }
                                %>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-default pull-right">Completar</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>