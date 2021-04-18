/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.servlet;

import trinm.cart.CartObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import trinm.tblcar.TblCarDAO;

/**
 *
 * @author tring
 */
@WebServlet(name = "AddItemToCartServlet", urlPatterns = {"/AddItemToCartServlet"})
public class AddItemToCartServlet extends HttpServlet {

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
        PrintWriter out = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            try {
                //1.    Cus goes to cart place
                HttpSession session = request.getSession(true);
                //2.    Cus takes a cart
                CartObject cart = (CartObject) session.getAttribute("CUSTCART");
                if (cart == null) {
                    cart = new CartObject();
                }//end cart is not existed
                //3.    Cus picks an item up
                String item = request.getParameter("txtCarID");
                TblCarDAO dao = new TblCarDAO();
                String name = dao.getName(item);
                //4.    Cus drop item out
                cart.addItemToCart(name);
                session.setAttribute("CUSTCART", cart);
                Map<String, Integer> items = cart.getItems();
                List<Float> price = new ArrayList<>();
                if (items != null) {
                    for (Map.Entry<String, Integer> car : items.entrySet()) {
                        price.add(dao.searchPrice(car.getKey()));
                    }
                }//end if items is existed
                session.setAttribute("PRICE", price);
            } catch (SQLException ex) {
                BasicConfigurator.configure();
                LOGGER.error("AddItemToCartServlet_SQLException: " + ex.getMessage());
            } catch (NamingException ex) {
                BasicConfigurator.configure();
                LOGGER.error("AddItemToCartServlet_NamingException: " + ex.getMessage());
            } finally {
                //5.    Cus continously goes shopping
                response.sendRedirect("studentPage");
            }

        } catch (IOException ex) {
            BasicConfigurator.configure();
            LOGGER.error("AddItemToCartServlet_IOException: " + ex.getMessage());
        } finally {
            out.close();
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
