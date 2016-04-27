package controlador;

import bd.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Producto;
import modelo.ProductoPromocion;
import modelo.Promocion;
import otros.PromEstatica;

@WebServlet(name = "RegistrarPromocionServlet", urlPatterns = {"/registrarPromocion.do"})
public class RegistrarPromocionServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            DAO d = new DAO();
            
            Promocion prom = new Promocion(1, PromEstatica.NOMBRE, Integer.parseInt(PromEstatica.DESCUENTO), 1);
            d.crearPromocion(prom);
            
            Promocion promo = d.getUltimaPromocion();
            
            for(Producto p : PromEstatica.LI){
                ProductoPromocion pp = new ProductoPromocion(1, promo.id, p.id, 1);
                d.crearProductoPromocion(pp);
            }
            
            PromEstatica.DESCUENTO = null;
            PromEstatica.LI = new ArrayList<>();
            PromEstatica.NOMBRE = null;
            
            
            response.sendRedirect("crearPromocion.jsp");
            
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarPromocionServlet.class.getName()).log(Level.SEVERE, null, ex);
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
