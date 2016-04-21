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
import modelo.Usuario;

@WebServlet(name = "RegistrarUsuarioServlet", urlPatterns = {"/registrarUsuario.do"})
public class RegistrarUsuarioServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String rut = request.getParameter("txtRut");
        String nombre = request.getParameter("txtNombre");
        String pass = request.getParameter("txtPassword");
        int estado = 1; //al estar recien creado, la cuenta pasa a estar activa
        int tipoUsuario = 2; //2 para cuentas recien creadas
        
        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setPassword(pass);
        u.setEstado(estado);
        u.setTipoUsuario(tipoUsuario);
        
        if(rut.equals("") || nombre.equals("") || pass.equals("")){
            
        }else{
            try {
                DAO d = new DAO();
                d.crearUsuario(u);
                response.sendRedirect("registrarUsuario.jsp?m=1");
            } catch (SQLException ex) {
                Logger.getLogger(RegistrarUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
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
