<%@page import="bd.DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cambiar Contraseña</title>
        <link rel="stylesheet" href="css/style_InicioSesion.css" type="text/css"/>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <script src="js/validarPass.js" type="text/javascript"></script>
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
                        Cambiar Contraseña
                    </h2>
                    <form method="post" action="cambiarPass.do" name="cambiarPass" autocomplete="off" onpaste="return false">
                        <div class="form-group">
                            <label for="pass">Contraseña Actual: </label>
                            <input type="password" name="txtPass" class="form-control" id="pass" placeholder="Contraseña Actual" required/>
                        </div>
                        <div class="form-group">
                            <label for="passNueva">Nueva Contraseña: </label>
                            <input type="password" name="txtCambioPass" class="form-control" id="passNueva" placeholder="Nueva Contraseña" required/>
                        </div>
                        <div>
                            <label for="passNuevaRepetir">Repetir Contraseña: </label>
                            <input type="password" name="txtCambioPassRepetir" class="form-control" id="passNuevaRepetir" placeholder="Repetir Contraseña" onkeyup="cambioColor(this.value);" required/>
                            <span id="mensaje"></span>
                        </div>
                        <br>
                        <%                           
                        if (u.getTipoUsuario() == 1) {
                        %>
                        <a href="menuAdmin.jsp" class="btn btn-primary pull-left">
                            ←Volver
                        </a>
                        <%
                        } else {
                        %>
                        <a href="menuUsuario.jsp" class="btn btn-primary pull-left">
                            ←Volver
                        </a>
                        <%
                            }
                        %>
                        <input type="submit" class="btn btn-primary pull-right" value="Cambiar Contraseña"/>
                    </form>
                    <br>
                    <br>
                    <%
                        String m = request.getParameter("m");
                        if (m != null) {
                            if (m.equals("1")) {
                    %>
                    <div class='alert alert-danger alert-dismissable'>
                        <strong>Error!</strong> Contraseña Actual Erronea.
                    </div>
                    <%
                    } else if (m.equals("2")) {
                    %>
                    <div class='alert alert-danger alert-dismissable'>
                        <strong>Error!</strong> Contraseñas No Coincidentes.
                    </div>
                    <%
                    } else {
                    %>
                    <div class='alert alert-success alert-dismissable'>
                        <strong>Exito!</strong> Contraseña Cambiada Exitosamente.
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
