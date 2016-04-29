package controlador;

import bd.DAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RestaurarServlet", urlPatterns = {"/restaurar.do"})
public class RestaurarServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        
        String executeCmd = "C:\\Program Files\\MySQL\\MySQLServer5.7\\bin\\mysql -u root -p1234 bd_sushi < C:\\Users\\Álvaro\\Desktop\\code\\respaldo_111.sql";
 
        Process runtimeProcess;
        try {
 
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
 
            if (processComplete == 0) {
                System.out.println("Base de datos restaurada.");
            } else {
                System.out.println("Ocurrio un problema.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
        
        
        
        
        
        /*Respaldo   
            try {
            String executeCmd = "C:\\Program Files\\MySQL\\MySQLServer5.7\\bin\\mysqldump -u " + "root" + " -p" + "1234" + " --add-drop-database -B " + "bd_sushi" + " -r " + "C:\\Users\\Álvaro\\Desktop\\code\\respaldo_111.sql";
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
 
            if (processComplete == 0) {
                System.out.println("Backup created successfully");
            } else {
                System.out.println("Could not create the backup");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        */
            
            
            
            
            
            
            /*
            Process run = Runtime.getRuntime().exec(
            "mysql.exe"
            + " --user=root" + " --password=1234"
            + " bd_sushi < C:\\Users\\Álvaro\\Desktop\\code\\respaldo_20160428_051656.sql");
            
            DAO d = new DAO();
            d.cambiarMaster();
            Runtime.getRuntime().exec("C:\\Program Files\\MySQL\\MySQLServer5.7\\bin\\mysql.exe -u root -p1234 bd_sushi < C:\\Users\\Álvaro\\Desktop\\code\\respaldo_20160428_051656.sql");
            response.sendRedirect("confirmarRestauracion.jsp");
            //String[] executeCmda = new String[]{"C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysql -u root -p1234 bd_sushi < C:\\Users\\Álvaro\\Desktop\\code\\respaldo_20160428_054932.sql"};
            //C:\Program Files\MySQL\MySQLServer5.7\bin
            //C:\Users\Álvaro\Desktop\code\respaldo_20160428_053840.sql
            
            String[] executeCmd = new String[]{"C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysql", "-u" + "root", "-p" + "1234", "bd_sushi < " + "C:\\Users\\Álvaro\\Desktop\\code\\respaldo_20160428_054932.sql"};
            Process runtimeProcess;
            
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
            System.out.println("Backup restored successfully");
            }
            } catch (InterruptedException ex) {
            Logger.getLogger(RestaurarServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            */

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
