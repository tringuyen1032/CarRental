/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.servlet;

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
import trinm.cart.CartObject;
import trinm.tblcar.TblCarDAO;

/**
 *
 * @author tring
 */
@WebServlet(name = "ItemRemoveServlet", urlPatterns = {"/ItemRemoveServlet"})
public class ItemRemoveServlet extends HttpServlet {

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
        BasicConfigurator.configure();

        try {
            PrintWriter out = response.getWriter();

            try {
                //1.    Cus goew to his/her cart place
                HttpSession session = request.getSession(false);
                if (session != null) {
                    //2. Cus takes his/her cart
                    CartObject cart = (CartObject) session.getAttribute("CUSTCART");
                    if (cart != null) {
                        //3. Cus get a items
                        Map<String, Integer> items = cart.getItems();
                        if (items != null) {
                            //4. Cus picks all removed items up
                            String[] itemsRemove = request.getParameterValues("chkItem");
                            List<Float> price = new ArrayList<>();
                            TblCarDAO dao = new TblCarDAO();
                            if (itemsRemove != null) {
                                for (String item : itemsRemove) {
                                    cart.removeItemFromCart(item);
                                }//end for item
                            }//end if removedItems is existed
                            for (Map.Entry<String, Integer> car : items.entrySet()) {
                                price.add(dao.searchPrice(car.getKey()));
                            }
                            session.setAttribute("CUSTCART", cart);
                            session.setAttribute("PRICE", price);
                            session.setAttribute("MESSAGE", null);

                        }//end if items is existed
                    }//end if cart is existed
                }//end if session is existed
            } finally {
                //call viewCart funtion again
                response.sendRedirect("viewCart");
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
