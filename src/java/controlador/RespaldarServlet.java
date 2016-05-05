package controlador;

import bd.DAO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Respaldo;

@WebServlet(name = "RespaldarServlet", urlPatterns = {"/respaldar.do"})
public class RespaldarServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        /* Obtener fecha local */
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Calendar cal = Calendar.getInstance();
        String fechaHora = dateFormat.format(cal.getTime());
        String fix[] = fechaHora.split(" ");
        /* Obtener fecha local */

        String executeCmd = "C:\\xampp\\mysql\\bin\\mysqldump -u root -B bd_sushi -r C:\\Users\\Álvaro\\Documents\\NetBeansProjects\\sistemaVentaSushi\\respaldo_" + fix[0].replace("/", "") + "_" + fix[1].replace(":", "") + ".sql";
        Process runtimeProcess;

        try {
            runtimeProcess = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", executeCmd});

            int processComplete = runtimeProcess.waitFor();

            System.out.println(processComplete);

            Respaldo r = new Respaldo(0, fix[0], fix[1]);
            DAO d = new DAO();
            d.crearRespaldo(r);
            response.sendRedirect("respaldo.jsp");

            if (processComplete == 0) {
                System.out.println("Backup Created Successfully !");
            } else {
                System.out.println("Couldn't Create the backup !");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
