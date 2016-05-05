<%@page import="modelo.Usuario"%>
<%@page import="bd.DAO"%>
<%@page import="otros.StaticPage"%>
<%@page import="otros.EnumPaginas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Usuario us = (Usuario) request.getSession().getAttribute("usuario");
    if (us == null) {
        response.sendRedirect("index.jsp");
    }
    StaticPage.PAGINA = EnumPaginas.REPORTES;
    DAO d = new DAO();
    
    String rep = request.getParameter("cboTipoReporte");
    
    if(rep != null){
        if(rep.equals("1")){
            
        }else if(rep.equals("2")){
            
        }else if(rep.equals("3")){
            
        }else if(rep.equals("4")){
            
        }
    }
    
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reportes</title>
        <link rel="stylesheet" href="css/style_InicioSesion.css" type="text/css"/>
        <link rel="stylesheet" href="css/bootstrap.min.css">
    </head>
    <body>
        <header>
            <%@include file="elimReg.jsp" %>
            <%@include file="navBar.jsp" %>
        </header>
        <div class="container" >
            <h2>Crear reportes: </h2>
            <div class="row" >
                <div class="col-md-6" >
                    <form action="reportes.jsp" method="post">
                        Reporte de: <select class="form-control" name="cboTipoReporte">
                            <option value=1>Trabajadores</option>
                            <option value=2>Ventas</option>
                            <option value=3>Clientes</option>
                            <option value=4>Promociones</option>
                        </select><br/>
                        <input class="btn btn-primary" name="btnCrearReporte" type="submit" value="Crear Reporte"><br/><br/>
                        <a class="btn btn-danger" role="button" href="menuAdmin.jsp">Volver</a>
                        
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>