/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import trinm.tblcart.TblCartDAO;

/**
 *
 * @author tring
 */
@WebServlet(name = "OrderDeleteServlet", urlPatterns = {"/OrderDeleteServlet"})
public class OrderDeleteServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(this.getClass());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");

        try {
            PrintWriter out = response.getWriter();

            try {
                int orderID = Integer.parseInt(request.getParameter("orderID"));
                String carID = request.getParameter("carID");
                TblCartDAO dao = new TblCartDAO();
                dao.deleteCart(orderID, carID);
            } finally {
                //call viewCart funtion again
                response.sendRedirect("history");
                out.close();
            }
        } catch (IOException ex) {
            BasicConfigurator.configure();
            LOGGER.error("ItemRemoveServlet_IOException: " + ex.getMessage());
        } catch (SQLException ex) {
            BasicConfigurator.configure();
            LOGGER.error("ItemRemoveServlet_SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            BasicConfigurator.configure();
            LOGGER.error("ItemRemoveServlet_NamingException: " + ex.getMessage());
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
