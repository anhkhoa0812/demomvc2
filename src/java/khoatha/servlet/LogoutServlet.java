 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoatha.servlet;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khoatha.utils.MyApplicationConstants;

/**
 *
 * @author tahoa
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/LogoutServlet"})

public class LogoutServlet extends HttpServlet {

//    private final String LOGIN_PAGE = "login.html";

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
        String url = siteMaps.getProperty(MyApplicationConstants.LogoutFeature.LOGIN_PAGE);

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie ck : cookies) {
                    if (ck.getName().equals("username")) {
                        ck.setMaxAge(0);
                        response.addCookie(ck);
                        break;
                    }
                }
                for (Cookie ck : cookies) {
                    if (ck.getName().equals("password")) {
                        ck.setMaxAge(0); // Xoá Cookie
                        response.addCookie(ck); //Gửi browser vì setMaxAge chỉ thay đổi mà ko gửi
                        break;
                    }
                }
            }

        } finally {
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
