<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Restaurar</title>
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
        <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
              rel="stylesheet" type="text/css">
        <link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
              rel="stylesheet" type="text/css">
    </head>
    <body>
        <%
            String d = request.getParameter("d");
        %>
        <div class="section"></div>
        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="alert alert-dismissable alert-warning" style="text-align: center;">
                            <b>¿Está seguro &nbsp;que desea restaurar en este punto la base de datos?</b>
                        </div>
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12" style="text-align: center;">
                                    <a href="restaurarDB.jsp?d=<%=d%>"><i class="fa fa-3x fa-check fa-fw text-success"></i></a>
                                    <a href="respaldo.jsp"><i class="fa fa-3x fa-close fa-fw text-danger"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
