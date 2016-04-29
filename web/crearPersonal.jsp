<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Personal</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/estilos.css"/>
    </head>
    <body>
        <h2>Crear: </h2>
        <div class="container" >
            <div class="row" >
                <div class="col-md-6" >
                    <form action="crearPersonal.do" method="post">
                        <h4>Crear Personal</h4><br/>
                        <input class="form-control" name="txtRut" placeholder="Rut: " type="text" required="required"/><br/>
                        <input class="form-control" name="txtNombre" placeholder="Nombre: " type="text" required="required"/><br/>
                        <input class="form-control" name="txtApellido" placeholder="Apellido: " type="text" required="required"/><br/>
                        Estado: <select class="form-control" name="cboTipoProducto">
                            <option value=1>Cocinero</option>
                            <option value=2>Vendedor</option>
                            <option value=3>Repartidor</option>
                        </select><br/>
                        Tipo Usuario: <select class="form-control" name="cboTipoUsuario">
                            <option value=1>Admin</option>
                            <option value=2>Normal</option>
                        </select><br/>
                        <input class="btn btn-primary" name="btnCrearPersonal" type="submit" value="Crear Personal"><br/><br/>
                        <a class="btn btn-danger" role="button" href="menuAdmin.jsp">Volver</a>
                    </form>
                    <div style="margin-top: 60px; ">
                        <%
                            String m = request.getParameter("m");
                            if (m != null) {
                                out.println("<div class='alert alert-success' role='alert'>La clave del usuario creado es : "+m+"</div>");
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
