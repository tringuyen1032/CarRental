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
import trinm.tblcar.TblCarDTO;

/**
 *
 * @author tring
 */
@WebServlet(name = "CarSearchServlet", urlPatterns = {"/CarSearchServlet"})
public class CarSearchServlet extends HttpServlet {

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
            String url = "studentPage";
            String valueSearch = request.getParameter("txtSeachValue");
            String category = request.getParameter("category");
            String datePick = request.getParameter("txtDatePick");
            String dateBack = request.getParameter("txtDateBack");
            String amount = request.getParameter("txtAmount");
            int count = 1;
            if (request.getParameter("btnCount") != null) {
                count = Integer.parseInt(request.getParameter("btnCount"));
            }
            try {
                HttpSession session = request.getSession();
                List<TblCarDTO> carList = new ArrayList<>();
                int num = 0;
                if ((valueSearch == null || valueSearch.equals("")) && category.equals("All") && (datePick.equals("") || dateBack.equals("")) && amount.equals("")) {
                    TblCarDAO dao = new TblCarDAO();
                    dao.loadCar(count);
                    carList = dao.getCarLoad();
                    num = dao.getNum();
                } else if ((valueSearch == null || valueSearch.equals("")) && category.equals("All") && (datePick.equals("") || dateBack.equals(""))) {
                    TblCarDAO dao = new TblCarDAO();
                    dao.loadCar(count, Integer.parseInt(amount));
                    carList = dao.getCarLoad();
                    num = dao.getNum();
                } else if ((valueSearch == null || valueSearch.equals("")) && category.equals("All") && amount.equals("")) {
                    TblCarDAO dao = new TblCarDAO();
                    dao.checkStock(datePick, dateBack);
                    if (dao.getSearchValue() != null) {
                        for (String value : dao.getSearchValue()) {
                            dao.searchDate(value);
                        }
                    }
                    int pos = (count - 1) * 20 + 19;
                    if (dao.getCarLoad() != null) {
                        if (pos >= dao.getCarLoad().size()) {
                            pos = dao.getCarLoad().size() - 1;
                        }
                        for (int i = (count - 1) * 20; i <= pos; i++) {
                            carList.add(dao.getCarLoad().get(i));
                        }
                    }
                    num = dao.getNum();
                } else if ((valueSearch == null || valueSearch.equals("")) && category.equals("All")) {
                    TblCarDAO dao = new TblCarDAO();
                    dao.checkStock(datePick, dateBack, Integer.parseInt(amount));
                    if (dao.getSearchValue() != null) {
                        for (String value : dao.getSearchValue()) {
                            dao.searchDate(value);
                        }
                    }
                    int pos = (count - 1) * 20 + 19;
                    if (dao.getCarLoad() != null) {
                        if (pos >= dao.getCarLoad().size()) {
                            pos = dao.getCarLoad().size() - 1;
                        }
                        for (int i = (count - 1) * 20; i <= pos; i++) {
                            carList.add(dao.getCarLoad().get(i));
                        }
                    }
                    num = dao.getNum();
                } else if ((valueSearch == null || valueSearch.equals("")) && !category.equals("All") && (datePick.equals("") || dateBack.equals("")) && amount.equals("")) {
                    TblCarDAO dao = new TblCarDAO();
                    dao.loadCar(count, category);
                    carList = dao.getCarLoad();
                    num = dao.getNum();
                } else if ((valueSearch == null || valueSearch.equals("")) && !category.equals("All") && (datePick.equals("") || dateBack.equals(""))) {
                    TblCarDAO dao = new TblCarDAO();
                    dao.loadCar(count, category, Integer.parseInt(amount));
                    carList = dao.getCarLoad();
                    num = dao.getNum();
                } else if ((valueSearch == null || valueSearch.equals("")) && !category.equals("All") && amount.equals("")) {
                    TblCarDAO dao = new TblCarDAO();
                    dao.checkStock(datePick, dateBack);
                    if (dao.getSearchValue() != null) {
                        for (String value : dao.getSearchValue()) {
                            dao.searchDate(value, category);
                        }
                    }
                    int pos = (count - 1) * 20 + 19;
                    if (dao.getCarLoad() != null) {
                        if (pos >= dao.getCarLoad().size()) {
                            pos = dao.getCarLoad().size() - 1;
                        }
                        for (int i = (count - 1) * 20; i <= pos; i++) {
                            carList.add(dao.getCarLoad().get(i));
                        }
                    }
                    num = dao.getNum();
                } else if ((valueSearch == null || valueSearch.equals("")) && !category.equals("All")) {
                    TblCarDAO dao = new TblCarDAO();
                    dao.checkStock(datePick, dateBack, Integer.parseInt(amount));
                    if (dao.getSearchValue() != null) {
                        for (String value : dao.getSearchValue()) {
                            dao.searchDate(value, category);
                        }
                    }
                    int pos = (count - 1) * 20 + 19;
                    if (dao.getCarLoad() != null) {
                        if (pos >= dao.getCarLoad().size()) {
                            pos = dao.getCarLoad().size() - 1;
                        }
                        for (int i = (count - 1) * 20; i <= pos; i++) {
                            carList.add(dao.getCarLoad().get(i));
                        }
                    }
                    num = dao.getNum();
                } else if (!(valueSearch == null || valueSearch.equals("")) && category.equals("All") && (datePick.equals("") || dateBack.equals("")) && amount.equals("")) {
                    TblCarDAO dao = new TblCarDAO();
                    dao.search(count, valueSearch);
                    carList = dao.getCarLoad();
                    num = dao.getNum();
                } else if (!(valueSearch == null || valueSearch.equals("")) && category.equals("All") && (datePick.equals("") || dateBack.equals(""))) {
                    TblCarDAO dao = new TblCarDAO();
                    dao.search(count, valueSearch, Integer.parseInt(amount));
                    carList = dao.getCarLoad();
                    num = dao.getNum();
                } else if (!(valueSearch == null || valueSearch.equals("")) && category.equals("All") && amount.equals("")) {
                    TblCarDAO dao = new TblCarDAO();
                    dao.checkStock(datePick, dateBack, valueSearch);
                    if (dao.getSearchValue() != null) {
                        for (String value : dao.getSearchValue()) {
                            dao.searchDate(value);
                        }
                    }
                    int pos = (count - 1) * 20 + 19;
                    if (dao.getCarLoad() != null) {
                        if (pos >= dao.getCarLoad().size()) {
                            pos = dao.getCarLoad().size() - 1;
                        }
                        for (int i = (count - 1) * 20; i <= pos; i++) {
                            carList.add(dao.getCarLoad().get(i));
                        }
                    }
                    num = dao.getNum();
                } else if (!(valueSearch == null || valueSearch.equals("")) && category.equals("All")) {
                    TblCarDAO dao = new TblCarDAO();
                    dao.checkStock(datePick, dateBack, valueSearch, Integer.parseInt(amount));
                    if (dao.getSearchValue() != null) {
                        for (String value : dao.getSearchValue()) {
                            dao.searchDate(value);
                        }
                    }
                    int pos = (count - 1) * 20 + 19;
                    if (dao.getCarLoad() != null) {
                        if (pos >= dao.getCarLoad().size()) {
                            pos = dao.getCarLoad().size() - 1;
                        }
                        for (int i = (count - 1) * 20; i <= pos; i++) {
                            carList.add(dao.getCarLoad().get(i));
                        }
                    }
                    num = dao.getNum();
                } else if (!(valueSearch == null || valueSearch.equals("")) && !category.equals("All") && (datePick.equals("") || dateBack.equals("")) && amount.equals("")) {
                    TblCarDAO dao = new TblCarDAO();
                    dao.search(count, valueSearch, category);
                    carList = dao.getCarLoad();
                    num = dao.getNum();
                } else if (!(valueSearch == null || valueSearch.equals("")) && !category.equals("All") && (datePick.equals("") || dateBack.equals(""))) {
                    TblCarDAO dao = new TblCarDAO();
                    dao.search(count, valueSearch, category, Integer.parseInt(amount));
                    carList = dao.getCarLoad();
                    num = dao.getNum();
                } else if (!(valueSearch == null || valueSearch.equals("")) && !category.equals("All") && amount.equals("")) {
                    TblCarDAO dao = new TblCarDAO();
                    dao.checkStock(datePick, dateBack, valueSearch);
                    if (dao.getSearchValue() != null) {
                        for (String value : dao.getSearchValue()) {
                            dao.searchDate(value, category);
                        }
                    }
                    int pos = (count - 1) * 20 + 19;
                    if (dao.getCarLoad() != null) {
                        if (pos >= dao.getCarLoad().size()) {
                            pos = dao.getCarLoad().size() - 1;
                        }
                        for (int i = (count - 1) * 20; i <= pos; i++) {
                            carList.add(dao.getCarLoad().get(i));
                        }
                    }
                    num = dao.getNum();
                } else if (!(valueSearch == null || valueSearch.equals("")) && !category.equals("All")) {
                    TblCarDAO dao = new TblCarDAO();
                    dao.checkStock(datePick, dateBack, valueSearch, Integer.parseInt(amount));
                    if (dao.getSearchValue() != null) {
                        for (String value : dao.getSearchValue()) {
                            dao.searchDate(value, category);
                        }
                    }
                    int pos = (count - 1) * 20 + 19;
                    if (dao.getCarLoad() != null) {
                        if (pos >= dao.getCarLoad().size()) {
                            pos = dao.getCarLoad().size() - 1;
                        }
                        for (int i = (count - 1) * 20; i <= pos; i++) {
                            carList.add(dao.getCarLoad().get(i));
                        }
                    }
                    num = dao.getNum();
                }
                session.setAttribute("CARLIST", carList);
                session.setAttribute("NUM", num);
                session.setAttribute("AMOUNT", amount);
                session.setAttribute("DATEPICK", datePick);
                session.setAttribute("DATEBACK", dateBack);
                session.setAttribute("SEARCHLAST", valueSearch);
                session.setAttribute("CATEGORYLAST", category);
            } catch (ParseException ex) {
                BasicConfigurator.configure();
                LOGGER.error("CarSearchServlet_ParseException: " + ex.getMessage());
            } finally {
                response.sendRedirect(url);
            }
        } catch (IOException ex) {
            BasicConfigurator.configure();
            LOGGER.error("CarSearchServlet_IO: " + ex.getMessage());
        } catch (SQLException ex) {
            BasicConfigurator.configure();
            LOGGER.error("CarSearchServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            BasicConfigurator.configure();
            LOGGER.error("CarSearchServlet_Naming: " + ex.getMessage());
        } catch (NullPointerException ex) {
            BasicConfigurator.configure();
            LOGGER.error("CarSearchServlet_NullPointer: " + ex.getMessage());
        } catch (IndexOutOfBoundsException ex) {
            BasicConfigurator.configure();
            LOGGER.error("CarSearchServlet_IndexOutOfBoundsException: " + ex.getMessage());
        } finally {
            out.close();
        }
    }

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
