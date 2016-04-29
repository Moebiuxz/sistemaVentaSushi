package controlador;

import bd.DAO;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        
        
        int BUFFER = 10485760;
        //para guardar en memmoria
        StringBuffer temp = null;
        //para guardar el archivo SQL
        FileWriter fichero = null;
        PrintWriter pw = null;

        try {
            //sentencia para crear el BackUp
            Process run = Runtime.getRuntime().exec(
                    "C:\\Program Files\\MySQL\\MySQLServer5.7\\bin\\mysqldump --host=localhost"
                    + " --user=root" + " --password=1234"
                    + " --compact --complete-insert --extended-insert --skip-quote-names"
                    + " --skip-comments --skip-triggers " + "bd_sushi");

            //se guarda en memoria el backup
            InputStream in = run.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            temp = new StringBuffer();
            int count;
            char[] cbuf = new char[BUFFER];
            while ((count = br.read(cbuf, 0, BUFFER)) != -1) {
                temp.append(cbuf, 0, count);
            }
            br.close();
            in.close();
            /* se crea y escribe el archivo SQL */
            fichero = new FileWriter("C:\\Users\\Álvaro\\Desktop\\code\\respaldo_"+fix[0].replace("/", "")+"_"+fix[1].replace(":", "")+".sql");
            pw = new PrintWriter(fichero);
            pw.println(temp.toString());
            Respaldo r = new Respaldo(0, fix[0], fix[1]);
            DAO d = new DAO();
            d.crearRespaldo(r);
            response.sendRedirect("respaldo.jsp");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
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
