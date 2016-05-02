package controlador;

import bd.DAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Respaldo;

@WebServlet(name = "RestaurarServlet", urlPatterns = {"/restaurar.do"})
public class RestaurarServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            DAO d = new DAO();
            List<Respaldo> respaldos = d.getRespaldos();
            
            
            String id = request.getParameter("d");
            //Respaldo r = d.getRespaldo(id);
            
//            String fecha = r.getFecha().replace("-", "");
//            String hora = r.getHora().replace(":", "");
//            String ruta = "respaldo_"+fecha+"_"+hora;
//            System.out.println(ruta);
            //d.restaurarDB();
            //String[] executeCmd = new String[]{"C:\\wamp64\\bin\\mysql\\mysql5.7.9\\bin\\mysql", "--user=root", "--password=", "bd_sushi", "-e", "source " + "../respaldo/"+ruta+".sql"};
//            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            
            //d.restaurarRespaldos(respaldos);
        } catch (SQLException ex) {
            Logger.getLogger(RestaurarServlet.class.getName()).log(Level.SEVERE, null, ex);
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
