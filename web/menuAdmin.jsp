<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menú</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/estilos.css"/>
    </head>
    <body>
        <header>
            <nav class="navbar navbar-inverse navbar-static-top" role="navigation">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navegacion-fm">
                            <span class="sr-only">Desplegar / Ocultar Menú</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a href="#" class="navbar-brand">Tienda Sushi</a>
                    </div>
                    <!-- Inicia menú -->

                    <div class="collapse navbar-collapse" id="navegacion-fm">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="index.jsp">Inicio</a></li>

                        </ul>
                    </div>
                </div>
            </nav>
        </header>

        <!-- El menu al costado -->
        <aside class="col-md-3 hidden-xs hidden-sm">
            <h4>Especialidades</h4>
            <div class="list-group">
                <a href="#" class='list-group-item'>Crear cocinero</a>
                <a href="#" class='list-group-item'>Crear usuario</a>
                <a href="#" class='list-group-item'>Listar personal</a>
            </div>
        </aside>
    </body>
</html>
