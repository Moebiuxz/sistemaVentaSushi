
<%@page import="modelo.Personal"%>
<%@page import="bd.DAO"%>
<%
    Usuario us = (Usuario)request.getSession().getAttribute("usuario");
    if(us == null){
        response.sendRedirect("index.jsp");
    }
    
    String id = request.getParameter("r");
    
    DAO d = new DAO();
    Personal per = d.getPersonalPorId(id);
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar Personal</title>
        <link rel="stylesheet" href="css/style_InicioSesion.css" type="text/css"/>
        <link rel="stylesheet" href="css/bootstrap.min.css">
    </head>
    <body>
        <header>
            <%@include file="navBar.jsp" %>
        </header>
        <div class="container" >
            <div class="row" >
                <div class="col-md-6" >
                    <h2>Actualizar Personal</h2>
                    <form action="actualizarPersonal.do" method="POST">
                        <div class="form-group">
                            <label for="nombre">ID:</label>
                            <input name="txtId" type="text" class="form-control" value="<%=id%>" disabled>
                        </div>
                        <div class="form-group">
                            <label for="nombre">Nombre:</label>
                            <input name="txtNombre" type="text" class="form-control" value="<%out.println(per.nombre);%>">
                        </div>
                        <div class="form-group">
                            <label for="nombre">Apellido:</label>
                            <input name="txtApellido" type="text" class="form-control" value="<%out.println(per.apellidos);%>">
                        </div>
                        
                        <button type="submit" class="btn btn-default pull-right">Actualizar</button>
                    </form>
                        <a class="btn btn-danger btn-danger active" role="button" href="menuAdmin.jsp">Volver</a>
                </div>
            </div>
        </div>
    </body>
</html>
