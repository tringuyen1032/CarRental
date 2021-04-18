/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import trinm.tbluser.TblUserCreateError;
import trinm.tbluser.TblUserDAO;
import javax.naming.NamingException;

/**
 *
 * @author tring
 */
@WebServlet(name = "AccountVerifyServlet", urlPatterns = {"/AccountVerifyServlet"})
public class AccountVerifyServlet extends HttpServlet {

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
            String url = "createNewAccountJSP";
            String code = request.getParameter("txtCode");
            TblUserCreateError errors = new TblUserCreateError();
            try {
                //1.    Check validate of 4 users
                HttpSession session = request.getSession();
                String codeVerify = (String) session.getAttribute("VERIFYCODE");
                String username = (String) session.getAttribute("CREATEACCOUNT");
                if (username != null) {
                    if (code.equals(codeVerify)) {
                        TblUserDAO dao = new TblUserDAO();
                        boolean result = dao.updateUser(username);
                        if (result) {
                            session.setAttribute("CREATEACCOUNT", null);
                            session.setAttribute("VERIFYCODE", null);
                            url = "loginPage";
                        }
                    } else {
                        url = "verifyFail";
                    }
                }
            } catch (NamingException ex) {
                BasicConfigurator.configure();
                LOGGER.error("AccountVerifyServlet_Naming: " + ex.getMessage());
            } catch (SQLException ex) {
                HttpSession session = request.getSession();
                String errMsg = ex.getMessage();
                BasicConfigurator.configure();
                LOGGER.error("CreateRecordServlet_SQL: " + errMsg);
                if (errMsg.contains("duplicate")) {
                    errors.setUsernameIsExisted("This username is Existed!!!");
                    session.setAttribute("CREATEER", errors);
                }
            } catch (NullPointerException ex) {
                BasicConfigurator.configure();
                LOGGER.error("AccountVerifyServlet_NullPointer: " + ex.getMessage());
            } finally {
                response.sendRedirect(url);
            }
        } catch (IOException ex) {
            BasicConfigurator.configure();
            LOGGER.error("AccountVerifyServlet_IO: " + ex.getMessage());
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
