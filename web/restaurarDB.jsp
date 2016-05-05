<%@page import="java.util.List"%>
<%@page import="modelo.Respaldo"%>
<%@page import="bd.DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    /*
    DAO d = new DAO();
    List<Respaldo> respaldos = d.getRespaldos();

    String id = request.getParameter("d");
    Respaldo r = d.getRespaldo(id);

    String fecha = r.getFecha().replace("-", "");
    String hora = r.getHora().replace(":", "");
    String ruta = "/respaldo_" + fecha + "_" + hora+".sql";
    System.out.println(ruta);
    d.restaurarDB();
    */
    String[] executeCmd = new String[]{"C:\\wamp64\\bin\\mysql\\mysql5.7.9\\bin\\mysql", "--user=root", "--password=", "bd_sushi", "-e", "source " + "/respaldo_20160430_140640.sql"};
    Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
    int processComplete = runtimeProcess.waitFor();
    //d.restaurarRespaldos(respaldos);
    
    //DAO d = new DAO();
    //String[] executeCmd = new String[]{"C:\\wamp64\\bin\\mysql\\mysql5.7.9\\bin\\mysql", "--user=root", "--password=", "bd_sushi","-e", "source "+ "/respaldo_20160430_015853.sql"};
    //Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
    /*NOTE: Used to create a cmd command*/
 /*NOTE: Do not create a single large string, this will cause buffer locking, use string array*/
    //String[] executeCmd = new String[]{"C:\\wamp64\\bin\\mysql\\mysql5.7.9\\bin\\mysql", "--user=root", "--password=", "bd_sushi","-e", "source "+ "/respaldo_20160430_015853.sql"};

    /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
    //Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
    //int processComplete = runtimeProcess.waitFor();

    /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/

%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="icon" type="image/png" href="images/icon.png" />
    </head>
    <body>
        <h1>prueba</h1>
    </body>
</html>
