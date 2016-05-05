<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cambiar Contraseña</title>
        <link rel="stylesheet" href="css/style_InicioSesion.css" type="text/css"/>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <script src="js/validarPass.js" type="text/javascript"></script>
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
                    <form method="post" action="" name="cambiarPass">
                        <div class="form-group">
                            <label for="pass">Contraseña Actual: </label>
                            <input type="password" name="txtPass" class="form-control" id="pass" placeholder="Contraseña Actual"/>
                        </div>
                        <div class="form-group">
                            <label for="passNueva">Nueva Contraseña: </label>
                            <input type="text" name="txtCambioPass" class="form-control" id="passNueva" placeholder="Nueva Contraseña"/>
                        </div>
                        <div>
                            <label for="passNuevaRepetir">Repetir Contraseña: </label>
                            <input type="text" name="txtCambioPassRepetir" class="form-control" id="passNuevaRepetir" placeholder="Repetir Contraseña" onkeyup="cambioColor(this.value);"/>
                            <span id="mensaje"></span>
                        </div>
                        <br>
                        <input type="submit" class="btn btn-primary pull-right" value="Cambiar Contraseña"/>
                    </form>
                    <%
                    String mensaje = request.getParameter("m");
                    if(mensaje != null){
                        if(mensaje == "1"){
                            
                        }
                    }
                    %>
                </div>
            </div>
        </div>
    </body>
</html>
