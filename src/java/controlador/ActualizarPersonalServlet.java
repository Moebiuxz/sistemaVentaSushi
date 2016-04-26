package controlador;

import bd.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Personal;
import modelo.Usuario;

@WebServlet(name = "ActualizarPersonalServlet", urlPatterns = {"/actualizarPersonal.do"})
public class ActualizarPersonalServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            
            try {
            response.setContentType("text/html;charset=UTF-8");
            
            String id, nombre, apellido;
            
            id = request.getParameter("txtId");
            nombre = request.getParameter("txtNombre");
            apellido = request.getParameter("txtApellido");
            
            DAO d = new DAO();
            
            Personal p = d.getPersonalPorId(id);
            
            Personal perAc = new Personal(p.id, p.rut, nombre, apellido, p.tipo, p.usuario, p.estado);
            
            d.actualizarNombreUsuario(nombre, p.usuario);
            d.actualizarPersonal(perAc);
            
            response.sendRedirect("menuAdmin.jsp");
            
        } catch (SQLException ex) {
            Logger.getLogger(ActualizarPersonalServlet.class.getName()).log(Level.SEVERE, null, ex);
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
