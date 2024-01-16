/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoatha.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khoatha.registration.RegistrationDAO;
import khoatha.registration.RegistrationDTO;
import khoatha.utils.MyApplicationConstants;

/**
 *
 * @author tahoa
 */
public class LoginServlet extends HttpServlet {

//    private final String SEARCH_PAGE = "searchPage";
//    private final String INVALID_PAGE = "invalid.html";
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
        PrintWriter out = response.getWriter();

        //0. get current context
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        //1. Get all parameter 
        String username = request.getParameter("txtUser"); //Lay parameter login r moi lay user va pass
        String password = request.getParameter("txtPass");
//        String url = INVALID_PAGE;
        String url = siteMaps.getProperty(MyApplicationConstants.LoginFeature.INVALID_PAGE);
        try {
            //2. call DAO
            //2.1 new DAO object
            RegistrationDAO dao = new RegistrationDAO();
            //2.2 call method of DAO
//            boolean result = dao.checkLogin(username, password);
            RegistrationDTO result = dao.checkLogin(username, password);
            //3. Process result
            if (result != null) {
//                url = SEARCH_PAGE;
                url = siteMaps.getProperty(MyApplicationConstants.LoginFeature.SEARCH_PAGE);
                HttpSession session = request.getSession(); //true vì đã login thành công, phải tạo vùng nhớ cho nó để lưu thông tin ng dùng
                session.setAttribute("USER_INFO", result);
                //Ghi cookie
//                Cookie cookie = new Cookie(username, password); //Trong đời thực, thì password phải được mã hoá
////                cookie.setMaxAge(60 * 10);
//                cookie.setMaxAge(60);
//                response.addCookie(cookie);
            } //end username and password are verified

        } catch (SQLException ex) {
            log("LoginServlet _ SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("LoginServlet _ Naming: " + ex.getMessage());
        }catch (ClassNotFoundException ex) {
            log("LoginServlet _ ClassNotFound: " + ex.getMessage());
        } 
        finally {
            response.sendRedirect(url);
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
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
