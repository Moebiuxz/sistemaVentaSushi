<%@page import="modelo.Cliente"%>
<%@page import="bd.DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario us = (Usuario) request.getSession().getAttribute("usuario");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ventas</title>
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
                    <form method="post" action="completarVenta.jsp">
                        <div class="form-group">
                            <label for="id">ID Vendedor:</label>
                            <input name="txtId" type="text" class="form-control" id="id" value="<%out.println(us.getId());%>" readonly>
                        </div>
                        <div class="form-group">
                            <label for="nombre">Vendedor:</label>
                            <input name="txtNombre" type="text" class="form-control" id="nombre" value="<%out.println(us.getNombre());%>" readonly>
                        </div>
                        <div class="form-group">
                            <label for="cliente">Cliente:</label>
                            <select class="form-control" name="cboCliente" id="cliente">
                                <%
                                    DAO d = new DAO();
                                    for (Cliente c : d.getClientes()) {
                                %>
                                <option value="=<%c.getFono();%>">=<%c.getNombre();%></option>
                                <%
                                    }
                                %>
                            </select>
                            <!-- <input name="txtId" type="text" class="form-control" id="cliente" placeholder="Cliente"> -->
                        </div>
                        <button type="submit" class="btn btn-default pull-right">Crear</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>