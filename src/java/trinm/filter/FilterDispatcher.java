/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import trinm.map.MapObject;

/**
 *
 * @author tring
 */
@WebFilter(filterName = "FilterDispatcher", urlPatterns = {"/*"})
public class FilterDispatcher implements Filter {

    private static final boolean debug = true;
    private final Logger LOGGER = Logger.getLogger(this.getClass());

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public FilterDispatcher() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        String url = null;
        HttpSession session = req.getSession();

        MapObject map = (MapObject) req.getSession().getServletContext().getAttribute("MAP");

        try {
            int lastIndex = uri.lastIndexOf("/");
            String resource = uri.substring(lastIndex + 1);
            if (session.getAttribute("USERNAME") == null
                    && !resource.equals("login")
                    && !resource.equals("createAccount")
                    && !resource.equals("createNewAccountJSP")
                    && !resource.equals("createRecord")
                    && !resource.equals("startUp")
                    && !resource.equals("verifyPage")
                    && !resource.equals("verifyRecord")
                    && !resource.equals("verifyFail")
                    && !resource.equals("")) {
                resource = "loginPage";
            } else if (session.getAttribute("USERNAME") != null
                    && (resource.equals("login")
                    || resource.equals("loginPage")
                    || resource.equals("createAccount")
                    || resource.equals("createNewAccountJSP"))) {
                resource = "studentPage";
            }

            for (String key : map.getItems().keySet()) {
                if (resource.equals(key)) {
                    url = map.getItems().get(key);
                }
            }

            if (url != null) {
                RequestDispatcher rd = req.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                chain.doFilter(request, response);
            }
        } catch (Throwable e) {
            BasicConfigurator.configure();
            LOGGER.error("FilterDispatcher_Throwable: " + e.getMessage());
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                BasicConfigurator.configure();
                LOGGER.debug("FilterDispatcher:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FilterDispatcher()");
        }
        StringBuffer sb = new StringBuffer("FilterDispatcher(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
