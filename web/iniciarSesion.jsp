<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
        <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
              rel="stylesheet" type="text/css">
        <link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
              rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="section">
            <div class="container text-center">
                <div class="row">
                    <div class="col-md-12">
                        <img src="images/login_icon.png" class="img-responsive img-thumbnail">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 text-left">
                        <br>
                        <form role="form" action="iniciarSesion.do" method="post" class="col-md-6" style="margin-left:280px;">
                            <div class="form-group">
                                <label class="control-label" for="inputUsuario">Usuario:</label>
                                <input name="txtRut" class="form-control" id="inputUsuario" placeholder="Ingrese usuario"
                                       type="text">
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="inputPass">Contraseña:</label>
                                <input name="txtPass" class="form-control" id="inputPass"
                                       placeholder="Contraseña" type="password">
                            </div>
                            <button type="submit" class="btn btn-block btn-danger btn-lg">Ingresar</button>
                            <%
                                String m = request.getParameter("m");
                                if (m != null) {
                                    out.println("<br>");
                                    out.println("<div class='alert alert-danger alert-dismissable'>");
                                        out.println("<strong>Error!</strong> El usuario o contraseña es incorrecto.");
                                    out.println("</div>");
                                }
                            %>                  
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
