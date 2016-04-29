<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Restaurar</title>
    </head>
    <body>
        <%
            String e = request.getParameter("e");
        %>
        <h1>Realmente desea restaurar la BD?</h1>
        <h2><a href="restaurar.do?e=<%=e%>">SÍ</a>  /  <a href="respaldo.jsp">NO</a></h2>
    </body>
</html>
