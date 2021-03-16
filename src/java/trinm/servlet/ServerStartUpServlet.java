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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import trinm.tblsubject.TblSubjectDAO;
import trinm.tbluser.TblUserDAO;

/**
 *
 * @author tring
 */
@WebServlet(name = "ServerStartUpServlet", urlPatterns = {"/ServerStartUpServlet"})
public class ServerStartUpServlet extends HttpServlet {

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
            String url = "loginPage";
            try {
                //1.    Get cookies
                Cookie[] cookies = request.getCookies();
                HttpSession session = request.getSession();
                if (cookies != null) {
                    //2.    Get last cookies
                    Cookie lastCookie = cookies[cookies.length - 1];
                    String username = lastCookie.getValue();
                    String password = lastCookie.getName();
                    if (username.equals("JSESSIONID") && cookies.length > 1) {
                        lastCookie = cookies[cookies.length - 2];
                        username = lastCookie.getValue();
                        password = lastCookie.getName();
                    }
                    //3.    call check login
                    TblUserDAO dao = new TblUserDAO();
                    session.setAttribute("SEARCHLAST", "");

                    int result = dao.checkLogin(username, password);
                    if (result == 1) {
                        session.setAttribute("USERNAME", username);
                        String fullname = dao.getFullname(username);
                        session.setAttribute("FULLNAME", fullname);
                        boolean role = dao.getRole(username);
                        url = "studentPage";
                        session.setAttribute("ROLE", role);
                    }
                } //ed if cookies has been existed
                session.setAttribute("MSG", null);
                session.setAttribute("SEARCHBYSUBJECT", null);
                session.setAttribute("CATEGORY", "All");
                session.setAttribute("STATUS", "All");
                session.setAttribute("SEARCHBYSTT", null);
            } finally {
                response.sendRedirect(url);
            }
        } catch (IOException ex) {
            BasicConfigurator.configure();
            LOGGER.error("StartUpServlet_IO: " + ex.getMessage());
        } catch (SQLException ex) {
            BasicConfigurator.configure();
            LOGGER.error("StartUpServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            BasicConfigurator.configure();
            LOGGER.error("StartUpServlet_Naming: " + ex.getMessage());
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
