package controlador;

import bd.DAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;

@WebServlet(name = "CambiarPassServlet", urlPatterns = {"/cambiarPass.do"})
public class CambiarPassServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String passActual = request.getParameter("txtPass");
        String passNueva = request.getParameter("txtCambioPass");
        String passRepetir = request.getParameter("txtCambioPassRepetir");
        
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        
        if(u.getPassword().equals(passActual)){
            if(passNueva.equals(passRepetir)){
                try {
                    DAO d = new DAO();
                    u.setNombre(u.getNombre());
                    u.setTipoUsuario(u.getTipoUsuario());
                    u.setEstado(u.getEstado());
                    u.setPassword(passNueva);
                    d.actualizarUsuario(u);
                    response.sendRedirect("cambiarPass.jsp?m=3");
                } catch (SQLException ex) {
                    Logger.getLogger(CambiarPassServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                response.sendRedirect("cambiarPass.jsp?m=2");
            }
        }else{
            response.sendRedirect("cambiarPass.jsp?m=1");
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
