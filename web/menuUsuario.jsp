<%@page import="bd.DAO"%>
<%@page import="modelo.Cliente"%>
<%@page import="otros.PromEstatica"%>
<%@page import="otros.StaticPage"%>
<%@page import="otros.EnumPaginas"%>
<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario us = (Usuario) request.getSession().getAttribute("usuario");
    if (us == null) {
        response.sendRedirect("index.jsp");
    }
    Cliente cli;
    String fono = request.getParameter("txtFono");
    DAO d = new DAO();
    
    boolean estaFono = false;
    if(fono != null){
        cli = d.getClientePorFono(fono);
        if(cli == null){
            response.sendRedirect("crearCliente.jsp?r="+fono+"");
        }else{
            estaFono = true;
        }
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menú Usuario</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/estilos.css"/>
    </head>
    <body>
        <div class="container" >
            <h1>Menú vendedor: <%out.println(us.nombre);%></h1>
            <div class="row" >
                <div class="col-md-12" >
                    <div class="col-md-5" >
                        <%
                            if (estaFono == false) {
                        %>
                        <form action="menuUsuario.jsp" method="post">
                            <h4>Buscar Cliente</h4><br/>
                            <input class="form-control" name="txtFono" placeholder="Fono cliente: " type="text" required="required"/><br/>
                            <input class="btn btn-primary" name="btnCrearPersonal" type="submit" value="Buscar"><br/><br/>
                        </form>
                        <%
                            }else{
                            cli = d.getClientePorFono(fono);
                            out.println("<h4>Cliente: "+cli.nombre+" "+cli.apellido+"<h4>");
                        %>
                        
                        
                        
                        
                        <%
                            }
                        %>
                        <a class="btn btn-danger" role="button" href="menuUsuario.jsp">Volver</a>
                    </div>

                    <div class="col-md-6" >
                        <h1>Parte en la que se hace el pedido</h1>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
