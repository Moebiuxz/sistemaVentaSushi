<%@page import="otros.EnumPaginas"%>
<%@page import="otros.StaticPage"%>
<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario us = (Usuario)request.getSession().getAttribute("usuario");
    if(us == null){
        response.sendRedirect("index.jsp");
    }
    
    StaticPage.PAGINA = EnumPaginas.CREAR_USUARIO; 
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Usuario</title>
        <link rel="stylesheet" href="css/style_InicioSesion.css" type="text/css"/>
        <link rel="stylesheet" href="css/bootstrap.min.css">
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
                    <h2>Registrar Usuario</h2>
                    <form action="registrarUsuario.do" method="POST">
                        <div class="form-group">
                            <label for="nombre">RUT:</label>
                            <input name="txtRut" type="text" class="form-control" id="rut" placeholder="RUT">
                        </div>
                        <div class="form-group">
                            <label for="nombre">Nombre:</label>
                            <input name="txtNombre" type="text" class="form-control" id="nombre" placeholder="Nombre">
                        </div>
                        <div class="form-group">
                            <label for="nombre">Contraseña:</label>
                            <input name="txtPassword" type="pass" class="form-control" id="pass" placeholder="Contraseña">
                        </div>
                        
                        <button type="submit" class="btn btn-default pull-right">Registrar</button>
                    </form>
                    <div style="margin-top: 60px; ">
                        <%
                            String m = request.getParameter("m");
                            if(m != null){
                                out.println("<div class='alert alert-success' role='alert'>El producto ha sido registrado.</div>");
                            }
                        %>
                    </div>
                    
                </div>
            </div>
        </div>
    </body>
</html>
