package controlador;

import bd.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Respaldo;

@WebServlet(name = "RespaldoPersonalizadoServlet", urlPatterns = {"/respaldoPersonalizado.do"})
public class RespaldoPersonalizadoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String resp = request.getParameter("personalizar");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String fechaHora = dateFormat.format(cal.getTime());
        String[] fix = fechaHora.split(" ");

        if (resp.equalsIgnoreCase("diario")) {
            try {

                Respaldo r = new Respaldo();
                r.setFecha(fix[0]);
                r.setTipo("Diario");
                DAO d = new DAO();
                d.generarBackUpPersonalizado(r);

            } catch (SQLException ex) {
                Logger.getLogger(RespaldoPersonalizadoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (resp.equalsIgnoreCase("mensual")){
            try {
                Respaldo r = new Respaldo();
                r.setFecha(fix[0]);
                r.setTipo("Mensual");
                DAO d = new DAO();
                d.generarBackUpPersonalizado(r);
            } catch (SQLException ex) {
                Logger.getLogger(RespaldoPersonalizadoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
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
