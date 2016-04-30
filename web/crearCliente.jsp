<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
Usuario us = (Usuario) request.getSession().getAttribute("usuario");
    if (us == null) {
        response.sendRedirect("index.jsp");
    }
    
    String fono = request.getParameter("r");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Cliente</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/estilos.css"/>
    </head>
    <body>
        <div class="container" >
            <h1>Menú vendedor: <%out.println(us.nombre);%></h1>
            <div class="row" >
                <div class="col-md-5" >
                    <form action="crearCliente.do" method="post">
                        <h4>Crear Cliente</h4><br/>
                        <input class="form-control" name="txtFono" value="<%out.println(fono);%>"  type="text" disabled/><br/>
                        <input class="form-control" name="txtNombre" placeholder="Nombre: " type="text" required="required"/><br/>
                        <input class="form-control" name="txtApellido" placeholder="Apellido: " type="text" required="required"/><br/>
                        <input class="btn btn-primary" name="btnCrearCliente" type="submit" value="Crear Cliente"><br/><br/>
                    </form>
                    <a class="btn btn-danger" role="button" href="menuUsuario.jsp">Volver</a>
                </div>
            </div>
        </div>

    </body>
</html>
