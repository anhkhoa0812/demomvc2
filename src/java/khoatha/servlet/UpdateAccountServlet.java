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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khoatha.registration.RegistrationDAO;
import khoatha.utils.MyApplicationConstants;

/**
 *
 * @author tahoa
 */
@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/UpdateAccountServlet"})
public class UpdateAccountServlet extends HttpServlet {


//    private final String ERROR_PAGE = "errors.html";

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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String chkAdmin = request.getParameter("chkAdmin");
        boolean role = false;
        if (chkAdmin != null) {
            role = true;
        }
        String searchValue = request.getParameter("lastSearchValue");
        String url = siteMaps.getProperty(MyApplicationConstants.UpdateAccountFeature.ERROR_PAGE);
        try {
            if (password.trim().length() < 6 || password.trim().length() > 20) {
                request.setAttribute("PASSWORD_ERROR", MyApplicationConstants.UpdateAccountFeature.PASSWORD_LENGTH_ERROR);
                 url = siteMaps.getProperty("searchLastNameController")
                            + "?btAction=Search"
                            + "&txtSearch=" + searchValue; //URL Rewriting
            } else {
                //Call DAO
                //2.1 new DAO
                RegistrationDAO dao = new RegistrationDAO();
                //2.2 DAO Method
                boolean result = dao.updateAccount(username, password, role);
                if (result) {
//                url = "DispatchServlet"
//                        + "?btAction=Search"
//                        + "&txtSearch=" + searchValue; //URL Rewriting
                    url = siteMaps.getProperty("searchLastNameController")
                            + "?btAction=Search"
                            + "&txtSearch=" + searchValue; //URL Rewriting
                    request.setAttribute("SUCCESS_NOTI", MyApplicationConstants.UpdateAccountFeature.SUCCESS_NOTI);
                }
            }
        } catch (SQLException ex) {
            log("UpdateAccountServlet _ SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("UpdateAccountServlet _ Naming: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("UpdateAccountServlet _ ClassNotFound: " + ex.getMessage());
        } 
        finally {
//            response.sendRedirect(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
