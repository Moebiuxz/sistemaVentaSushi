<%-- 
    Document   : index
    Created on : 21-abr-2016, 6:01:52
    Author     : Álvaro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio de Sesión</title>
        <link rel="stylesheet" href="css/style_InicioSesion.css" type="text/css"/>
        <link rel="stylesheet" href="css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-6 col-md-4 col-md-offset-4">
                    <h1 class="text-center login-title">Inicie sesión antes de continuar</h1>
                    <div class="account-wall">
                        <img class="profile-img" src="images/icon_login.png"
                             alt="">
                        <form class="form-signin" action="iniciarSesion.do" method="POST">
                            <input type="text" name="txtUsuario" class="form-control" placeholder="Usuario:" required autofocus>
                            <input type="password" name="txtPass" class="form-control" placeholder="Clave:" required>
                            <button class="btn btn-lg btn-primary btn-block" type="submit">
                                Ingresar</button>
                            <label class="checkbox pull-left">
                                <input type="checkbox" value="recordar">
                                Recuerdame
                            </label>
                            <span class="clearfix"></span>
                        </form>
                    </div>
                    <!--  
                    <a href="vista_registroUsuario.html" class="text-center new-account">Crear cuenta </a>
                    -->  
                </div>
            </div>
            <%
                String m = request.getParameter("m");
                if (m != null) {
                    out.println("<div class='alert alert-success' role='alert'>Usuario o Contraseña Incorrecta</div>");
                }
            %>
        </div>
    </body>
</html>
