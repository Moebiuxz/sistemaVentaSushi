<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmar eliminar</title>
    </head>
    <body>
        <h1>¿Desea realmente eliminar este producto?</h1>
        <%
            out.println("<h2><a href='eliminarProducto.do?e="+request.getParameter("e") +"'>SI</a><a href='listarProducto.jsp'> // NO</a></h2>");
        %>
        
    </body>
</html>
