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
import trinm.tblcolor.TblColorDAO;

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
            int count = 1;
            if (request.getParameter("btnCount") != null) {
                count = Integer.parseInt(request.getParameter("btnCount"));
            }
            try {
                HttpSession session = request.getSession();
                TblCarDAO dao = new TblCarDAO();
                List<TblCarDTO> carList = new ArrayList<>();
                int num = 0;
                if (valueSearch == null || valueSearch.equals("")) {
                    dao.loadCar(count);
                    if (dao.getCarLoad() != null) {
                        for (TblCarDTO dto : dao.getCarLoad()) {
                            TblColorDAO colorDAO = new TblColorDAO();
                            colorDAO.getColor(dto.getId());
                            TblCarDTO car = new TblCarDTO(dto.getId(), dto.getName(), dto.getImage(), dto.getPrice(), dto.getQuantity(), dto.getCategory(), colorDAO.getColorLoad());
                            carList.add(car);
                        }
                    }
                } else {
                    dao.search(count, valueSearch);
                    if (dao.getCarLoad() != null) {
                        for (TblCarDTO dto : dao.getCarLoad()) {
                            TblColorDAO colorDAO = new TblColorDAO();
                            colorDAO.getColor(dto.getId());
                            TblCarDTO car = new TblCarDTO(dto.getId(), dto.getName(), dto.getImage(), dto.getPrice(), dto.getQuantity(), dto.getCategory(), colorDAO.getColorLoad());
                            carList.add(car);
                        }
                    }
                }
                num = dao.getNum();
                session.setAttribute("CARLIST", carList);
                session.setAttribute("NUM", num);
                session.setAttribute("SEARCHLAST", valueSearch);
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
