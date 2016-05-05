<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Respaldo"%>
<%@page import="java.util.List"%>
<%@page import="bd.DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario us = (Usuario) request.getSession().getAttribute("usuario");
    if (us == null) {
        response.sendRedirect("index.jsp");
    }
    StaticPage.PAGINA = EnumPaginas.RESPALDO;
    DAO d = new DAO();
    List<Respaldo> respaldos = d.getRespaldos();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Respaldo</title>
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
                    <h2>Respaldos Base de Datos</h2>
                    <table class="table table-bordered" style="margin-top: 30px;">
                        <tr>
                            <th>ID</th>
                            <th>Fecha</th>
                            <th>Hora</th>
                            <th>Restaurar</th>
                        </tr>
                        <%                            
                            for (Respaldo r : respaldos) {
                                out.println("<tr>");
                                out.println("<td>" + r.getId() + "</td>");
                                out.println("<td>" + r.getFecha() + "</td>");
                                out.println("<td>" + r.getHora() + "</td>");
                                out.println("<td><a href='confirmarRestauracion.jsp?d=" + r.getId() + "'>Restaurar</a></td>");
                                out.println("</tr>");
                            }
                        %>
                    </table>
                    <a href="respaldar.do" class="pull-right"><button class="btn btn-primary">Generar Respaldo</button></a>
                    <br><br><br>
                    <h2>Personalizar</h2>
                    <form action="respaldoPersonalizado.do">
                        <div class="radio">
                            <label>
                                <input type="radio" name="personalizar" value=diario checked="checked">
                                <strong>Diario</strong>
                            </label>
                        </div>
                        <div class="radio">
                            <label>
                                <input type="radio" name="personalizar" value="mensual"> 
                                <strong>Mensual</strong>
                            </label>
                        </div>
                        <div>    
                            <label>
                                <input type="radio" name="personalizar" value="especifico"> 
                                Especifico <input style="margin-top: 15px;" class="form-control" type="date" name="txtEspecifico">
                            </label>
                        </div>
                        <br>
                        <input type="submit" style="margin-left: -4px;" class="btn btn-success col-md-4" name="btnEnviar" value="Respaldar">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
