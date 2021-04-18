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
import trinm.tblcar.TblCarDAO;
import trinm.tblcart.TblCartDAO;
import trinm.tblcart.TblCartDTO;

/**
 *
 * @author tring
 */
@WebServlet(name = "HistoryViewServlet", urlPatterns = {"/HistoryViewServlet"})
public class HistoryViewServlet extends HttpServlet {

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
            String url = "historyPage";
            String valueSearch = request.getParameter("txtSeachValue");
            String date = request.getParameter("txtDatePick");

            try {
                HttpSession session = request.getSession();
                String userID = (String) session.getAttribute("USERNAME");
                List<TblCartDTO> cartList = new ArrayList<>();
                TblCartDAO dao = new TblCartDAO();

                if ((valueSearch == null || valueSearch.equals("")) && (date == null || date.equals(""))) {
                    dao.searchHistory(userID);
                    cartList = dao.getHistoryLoad();
                } else if ((valueSearch == null || valueSearch.equals(""))) {
                    dao.searchHistoryByDate(date, userID);
                    cartList = dao.getHistoryLoad();
                } else if (date == null || date.equals("")) {
                    TblCarDAO carDAO = new TblCarDAO();
                    carDAO.search(valueSearch);
                    if (carDAO.getCarID() != null) {
                        for (String carID : carDAO.getCarID()) {
                            dao.searchHistory(carID, userID);
                        }
                        cartList = dao.getHistoryLoad();
                    }
                } else {
                    TblCarDAO carDAO = new TblCarDAO();
                    carDAO.search(valueSearch);
                    if (carDAO.getCarID() != null) {
                        for (String carID : carDAO.getCarID()) {
                            dao.searchHistoryByDate(carID, date, userID);
                        }
                        cartList = dao.getHistoryLoad();
                    }
                }
                session.setAttribute("RESULT", cartList);
                session.setAttribute("SEARCHHISTORY", valueSearch);
                session.setAttribute("DATERENT", date);

            } catch (ParseException ex) {
                BasicConfigurator.configure();
                LOGGER.error("ViewHistoryServlet_ParseException: " + ex.getMessage());
            } finally {
                response.sendRedirect(url);
            }
        } catch (IOException ex) {
            BasicConfigurator.configure();
            LOGGER.error("ViewHistoryServlet_IO: " + ex.getMessage());
        } catch (SQLException ex) {
            BasicConfigurator.configure();
            LOGGER.error("ViewHistoryServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            BasicConfigurator.configure();
            LOGGER.error("ViewHistoryServlet_Naming: " + ex.getMessage());
        } catch (NullPointerException ex) {
            BasicConfigurator.configure();
            LOGGER.error("ViewHistoryServlet_NullPointer: " + ex.getMessage());
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
