package controlador;

import bd.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Personal;
import modelo.Producto;
import modelo.ProductoVenta;
import modelo.Promocion;
import modelo.PromocionVenta;
import modelo.Usuario;
import modelo.Venta;
import otros.PromEstatica;

@WebServlet(name = "RegistrarVentaServlet", urlPatterns = {"/registrarVenta.do"})
public class RegistrarVentaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            Usuario us = (Usuario) request.getSession().getAttribute("usuario");
            String total = request.getParameter("tot");
            
            Date fecha = Date.valueOf(LocalDate.now());
            DAO d = new DAO();
            
            Personal peer = d.getPersonalSegunIdUsuario(us.id);
            
            Venta v = new Venta(1, fecha, peer.id, PromEstatica.FONO_CLIENTE, Integer.parseInt(total), 1);
            d.crearVenta(v);
            Venta ven = d.getUltimaVenta();
            
            if(PromEstatica.LIPRO.size() != 0){
                for(Producto pv : PromEstatica.LIPRO){
                    ProductoVenta p = new ProductoVenta(1, pv.id, ven.id, 1);
                    d.crearProductoVenta(p);
                }
            }else{
                ProductoVenta pv = new ProductoVenta(1, 1, ven.id, 1);
                d.crearProductoVenta(pv);
            }
            
            if(PromEstatica.LIPP.size() != 0){
                for(Promocion pv : PromEstatica.LIPP){
                    PromocionVenta p = new PromocionVenta(1, pv.id, ven.id, 1);
                    d.crearPromocionVenta(p);
                }
            }else{
                PromocionVenta pv = new PromocionVenta(1, 1, ven.id, 1);
                d.crearPromocionVenta(pv);
            }
            
            PromEstatica.FONO_CLIENTE = null;
            PromEstatica.ESTA_FONO = false;
            PromEstatica.LIPRO = new ArrayList<>();
            PromEstatica.LIPP = new ArrayList<>();
            
            response.sendRedirect("menuUsuario.jsp");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarVentaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
