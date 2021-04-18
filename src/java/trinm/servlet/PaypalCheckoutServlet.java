/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
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
import trinm.tblcart.TblCartDAO;
import trinm.tbldiscount.TblDiscountDAO;
import trinm.tblorder.TblOrderDAO;

/**
 *
 * @author tring
 */
@WebServlet(name = "PaypalCheckoutServlet", urlPatterns = {"/PaypalCheckoutServlet"})
public class PaypalCheckoutServlet extends HttpServlet {

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
            String urlRewriting = "loadcar";

            try {
                float total = 0;
                //1.    Cus goew to his/her cart place
                HttpSession session = request.getSession(false);
                boolean dateCheck = true;
                TblCarDAO carDAO = new TblCarDAO();
                List<String> nameList = new ArrayList<>();
                List<Integer> quantityList = new ArrayList<>();
                List<String> datePickList = new ArrayList<>();
                List<String> dateBackList = new ArrayList<>();
                List<Float> priceList = new ArrayList<>();
                if (session != null) {
                    String username = (String) session.getAttribute("USERNAME");
                    CartObject cart = (CartObject) session.getAttribute("CUSTCART");
                    int size = cart.getItems().size();
                    if (size != 0) {
                        for (int i = 1; i <= size; i++) {
                            String name = request.getParameter("nameOfFood-" + i);
                            int quantity = Integer.parseInt(request.getParameter("quantity=" + i));
                            String datePick = request.getParameter("datepicker-" + i);
                            String dateBack = request.getParameter("dateback-" + i);
                            total += Float.parseFloat(request.getParameter("priceValue=" + i));
                            if ((datePick == null || datePick.equals("")) || (dateBack == null || dateBack.equals(""))) {
                                urlRewriting = "viewCart";
                                dateCheck = false;
                                String msg = "Please input rental date and return date before check out!";
                                session.setAttribute("MESSAGE", msg);
                            } else {
                                boolean result = carDAO.checkStock(datePick, dateBack, name, quantity);
                                if (result) {
                                    datePickList.add(datePick);
                                    dateBackList.add(dateBack);
                                    nameList.add(name);
                                    quantityList.add(quantity);
                                    priceList.add(Float.parseFloat(request.getParameter("priceValue=" + i)));
                                } else {
                                    dateCheck = false;
                                    urlRewriting = "viewCart";
                                    String msg = name + " is out of stock";
                                    session.setAttribute("MESSAGE", msg);
                                }
                            }
                        }
                        if (dateCheck) {
                            TblOrderDAO dao = new TblOrderDAO();
                            String code = request.getParameter("txtDiscount");
                            TblDiscountDAO discountDAO = new TblDiscountDAO();
                            int discount = 0;
                            if(code != null && !code.equals("")) {
                                discount = discountDAO.searchDiscount(code);
                            }
                            if(discount != 0) {
                                total = total - (total*discount)/100;
                            }
                            dao.createTblOrder(username, total);
                            int lastID = dao.getLastID();
                            TblCartDAO cartDAO = new TblCartDAO();
                            for (int i = 0; i < size; i++) {
                                cartDAO.createTblCart(lastID, carDAO.getID(nameList.get(i)), quantityList.get(i), priceList.get(i), datePickList.get(i), dateBackList.get(i));
                            }
                            session.setAttribute("MESSAGE", null);
                            session.setAttribute("CUSTCART", null);
                            urlRewriting = "https://www.sandbox.paypal.com/cgi-bin/webscr?business=tringuyen11122000-facilitator@gmail.com"
                                    + "&cmd=_xclick&item_name=Rent Car Bill&amount=" + total + "&currency_code=USD&return=loadcar&cancel_return=loadcar";
                        }
                    }
                }
            } catch (SQLException ex) {
                BasicConfigurator.configure();
                LOGGER.error("CheckOutServlet_SQLException: " + ex.getMessage());
            } catch (NamingException ex) {
                BasicConfigurator.configure();
                LOGGER.error("CheckOutServlet_NamingException: " + ex.getMessage());
            } catch (ParseException ex) {
                BasicConfigurator.configure();
                LOGGER.error("CheckOutServlet_ParseException: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                BasicConfigurator.configure();
                LOGGER.error("CheckOutServlet_NumberFormatException: " + ex.getMessage());
            } catch (NullPointerException ex) {
                BasicConfigurator.configure();
                LOGGER.error("CheckOutServlet_NullPointerException: " + ex.getMessage());
            } finally {
                //call shopping funtion again
                response.sendRedirect(urlRewriting);
                out.close();
            }
        } catch (IOException ex) {
            LOGGER.error("CheckOut_IOException: " + ex.getMessage());
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
