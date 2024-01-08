/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoatha.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khoatha.registration.RegistrationDAO;
import khoatha.registration.RegistrationDTO;
import khoatha.utils.MyApplicationConstants;

/**
 *
 * @author tahoa
 */
@WebServlet(name = "StarUpServlet", urlPatterns = {"/StarUpServlet"})
public class StarUpServlet extends HttpServlet {

//    private final String LOGIN_PAGE = "login.html";
//    private final String SEARCH_PAGE = "search.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //0. get current context
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(MyApplicationConstants.StarUpFeature.LOGIN_PAGE);
        try {
            // DOC COOKIE
            //1. Get all cookies with ReqObj
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                //2. Get username and password from newest cookie
                Cookie newestCookie = cookies[cookies.length - 1]; //Get newest cookie
                String username = newestCookie.getName(); // Get key
                String password = newestCookie.getValue(); // Get value
                //3. call DAO
                //3.1 new DAO
                RegistrationDAO dao = new RegistrationDAO();
                //3.2 call method of DAO
//                boolean result = dao.checkLogin(username, password);
                RegistrationDTO result = dao.checkLogin(username, password);
                //4. Process result
                if (result != null) {
                    url = siteMaps.getProperty(MyApplicationConstants.StarUpFeature.SEARCH_PAGE);
                } //user 
            } //end cookies has existed
        } catch (NamingException ex) {
            log("StarUpServlet _ Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("StarUpServlet _ SQL: " + ex.getMessage());
        } finally {
            //Forward hay sendRedirect cũng đc vì khi reponse trả về nó ko mất
            response.sendRedirect(url);
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
