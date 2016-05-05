<%@page import="java.util.List"%>
<%@page import="modelo.Respaldo"%>
<%@page import="bd.DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    
    DAO d = new DAO();
    List<Respaldo> respaldos = d.getRespaldos();

    String id = request.getParameter("d");
    Respaldo r = d.getRespaldo(id);

    String fecha = r.getFecha().replace("-", "");
    String hora = r.getHora().replace(":", "");
    String ruta = "/respaldo_" + fecha + "_" + hora+".sql";
    System.out.println(ruta);
    
    /*Funciona*/
    String[] executeCmd = new String[]{"C:\\xampp\\mysql\\bin\\mysql", "--user=root", "--password=", "bd_sushi", "-e", "source " + "C:\\Users\\Álvaro\\Documents\\NetBeansProjects\\sistemaVentaSushi\\"+ruta};
    Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
    int processComplete = runtimeProcess.waitFor();
    d.insertRespaldos(respaldos);
    response.sendRedirect("respaldo.jsp");
%>
