package controlador;

import bd.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Personal;
import modelo.Usuario;

@WebServlet(name = "IniciarSesionServlet", urlPatterns = {"/iniciarSesion.do"})
public class IniciarSesionServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String rut = request.getParameter("txtRut");
        String pass = request.getParameter("txtPass");

        try {
            DAO d = new DAO();
            Personal pers = d.getPersonalSegunRutUsuario(rut);
            if (pers != null) {
                Usuario u = new Usuario(0, pers.nombre, pass, 0, 0);
                Usuario user = d.getUsuario(u);

                if (user != null) {
                    if (d.getTipoUsuario(user.getTipoUsuario()).getNombre().equalsIgnoreCase("ADMIN")) {
                        request.getSession().setAttribute("usuario", user);
                        response.sendRedirect("menuAdmin.jsp");
                    } else {
                        request.getSession().setAttribute("usuario", user);
                        response.sendRedirect("menuUsuario.jsp");
                    }
                } else {
                    response.sendRedirect("index.jsp?m=1");
                }
            } else {
                response.sendRedirect("index.jsp?m=1");
            }

        } catch (Exception ex) {
            Logger.getLogger(IniciarSesionServlet.class.getName()).log(Level.SEVERE, null, ex);
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
