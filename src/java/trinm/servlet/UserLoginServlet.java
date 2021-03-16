/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.servlet;

import com.restfb.types.User;
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
import trinm.facebook.RestFacebook;
import trinm.tblcar.TblCarDAO;
import trinm.tbluser.SendEmail;
import trinm.tbluser.TblUserDAO;
import trinm.tbluser.TblUserHash;
import trinm.verify.VerifyRecaptcha;

/**
 *
 * @author tring
 */
@WebServlet(name = "UserLoginServlet", urlPatterns = {"/UserLoginServlet"})
public class UserLoginServlet extends HttpServlet {

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
            String code = request.getParameter("code");
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
            HttpSession session = request.getSession();
            try {
                TblUserDAO dao = new TblUserDAO();
                if (code == null || code.isEmpty()) {
                    if (verify) {
                        String username = request.getParameter("txtUser");
                        String password = request.getParameter("txtPass");
                        password = new TblUserHash(password).toHexString();
                        int result = 0;
                        result = dao.checkLogin(username, password);
                        if (result == 1) {
                            Cookie cookie = new Cookie(password, username);
                            cookie.setMaxAge(60 * 30);
                            response.addCookie(cookie);
                            session.setAttribute("USERNAME", username);
                            String fullname = dao.getFullname(username);
                            session.setAttribute("FULLNAME", fullname);
                            boolean role = dao.getRole(username);
                            session.setAttribute("ROLE", role);
                            url = "loadcar";
                            TblCarDAO carDAO = new TblCarDAO();
                            carDAO.loadCategory();
                            List<String> category = carDAO.getCategory();
                            category.add(0, "All");
                            session.setAttribute("CATEGORYLIST", category);
                        } else if (result == 0) {// end if check Login is ok
                            session.setAttribute("MSG", "Invalid mail or password. Please try again.");
                        } else {
                            SendEmail mailSend = new SendEmail();
                            String codeVerify = mailSend.sendEmail(username);
                            url = "verifyPage";
                            session.setAttribute("CREATEACCOUNT", username);
                            session.setAttribute("VERIFYCODE", codeVerify);
                        }
                        session.setAttribute("SEARCHBYSUBJECT", null);
                        session.setAttribute("CATEGORY", "All");
                        session.setAttribute("STATUS", "All");
                        session.setAttribute("SEARCHBYSTT", null);
                    } else {
                        session.setAttribute("MSG", "You missed the Captcha.");
                    }
                } else {
                    String accessToken = RestFacebook.getToken(code, "http://localhost:8084/CarRental/login");
                    User user = RestFacebook.getUserInfo(accessToken);
                    String username = user.getId();
                    String fullname = user.getName();
                    boolean result = false;
                    result = dao.checkLogin(username);
                    if (result) {
                        session.setAttribute("USERNAME", username);
                        session.setAttribute("FULLNAME", fullname);
                        boolean role = dao.getRole(username);
                        session.setAttribute("ROLE", role);
                        url = "loadcar";
                        TblCarDAO carDAO = new TblCarDAO();
                        carDAO.loadCategory();
                        List<String> category = carDAO.getCategory();
                        category.add(0, "All");
                        session.setAttribute("CATEGORYLIST", category);
                    } else {// end if check Login is ok
                        session.setAttribute("MSG", "Login facebook fail. Please try again.");
                    }
                }
            } finally {
                response.sendRedirect(url);
            }
        } catch (IOException ex) {
            BasicConfigurator.configure();
            LOGGER.error("LoginServlet_IO: " + ex.getMessage());
            ex.printStackTrace();
        } catch (SQLException ex) {
            BasicConfigurator.configure();
            LOGGER.error("LoginServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            BasicConfigurator.configure();
            LOGGER.error("LoginServlet_Naming: " + ex.getMessage());
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
