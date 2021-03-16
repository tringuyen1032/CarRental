/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import trinm.tblresult.TblResultDAO;
import trinm.tblresult.TblResultDTO;
import trinm.tblsubject.TblSubjectDAO;

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
            int count = 1;
            if (request.getParameter("btnCount") != null) {
                count = Integer.parseInt(request.getParameter("btnCount"));
            }

            try {
                HttpSession session = request.getSession();
                String userID = (String) session.getAttribute("USERNAME");
                TblResultDAO dao = new TblResultDAO();
                TblSubjectDAO subjectDao = new TblSubjectDAO();
                List<String> subjects = null;

                if ((valueSearch == null || valueSearch.equals(""))) {
                    dao.loadResult(userID, count);
                } else{
                    subjectDao.getSubjectID(valueSearch);
                    subjects = subjectDao.getListSubjectID();
                    if (subjects != null) {
                        dao.loadResult(userID, count, subjects);
                    }
                }
                List<TblResultDTO> resultList = dao.getResultList();
                session.setAttribute("RESULT", resultList);
                session.setAttribute("RESULTPOS", count);
                session.setAttribute("SEARCHHISTORY", valueSearch);

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
