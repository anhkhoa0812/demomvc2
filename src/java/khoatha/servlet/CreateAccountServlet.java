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
import khoatha.registration.RegistrationCreateError;
import khoatha.registration.RegistrationDAO;
import khoatha.registration.RegistrationDTO;
import khoatha.utils.MyApplicationConstants;

/**
 *
 * @author tahoa
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {
//    private final String USER_LENGTH_ERROR = "Username is required typing from 6 to 30 characters";
//    private final String PASSWORD_LENGTH_ERROR = "Password is required typing from 6 to 20 characters";
//    private final String CONFIRM_ERROR = "Confirm must be match Password";
//    private final String FULLNAME_LENGTH_ERROR = "Full name is required typing from 2 to 50 characters";
//    private final String USER_EXISTED_ERROR = " Username have existed";
//    private final String CREATE_ERROR_PAGE = "createAccount.jsp";
//    private final String LOGIN_PAGE  = "login.html";
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
        //1. Get all parameters
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullName");
        
        RegistrationCreateError errors = new RegistrationCreateError();
        boolean foundErr  = false;
        String url = siteMaps.getProperty(MyApplicationConstants.CreateAccountFeature.CREATE_ERROR_PAGE);
        try  {
            //2. Verify all user's errors
            if(username.trim().length() < 6 || username.trim().length() > 30) {
                foundErr = true;
                errors.setUsernameLengthErr(MyApplicationConstants.RegistrationErrorFeature.USER_LENGTH_ERROR);
            }
            if(password.trim().length() < 6 || password.trim().length() > 20) {
                foundErr = true;
                errors.setPasswordLengthErr(MyApplicationConstants.RegistrationErrorFeature.PASSWORD_LENGTH_ERROR);
            }
            else if(!confirm.trim().equals(password)) {
                foundErr = true;
                errors.setConfirmNotMached(MyApplicationConstants.RegistrationErrorFeature.CONFIRM_ERROR);
            }
            if(fullName.trim().length() < 2 || fullName.trim().length() > 50) {
                foundErr = true;
                errors.setFullNameLengthErr(MyApplicationConstants.RegistrationErrorFeature.FULLNAME_LENGTH_ERROR);
            }
            if(foundErr) {//errors occur
                //Cache to attribute then forward page to display
                request.setAttribute("CREATE_ERRORS", errors);
                
            } else { //no error
                //3. call DAO
                //3.1 new DAO
                RegistrationDAO dao = new RegistrationDAO();
                //3.2 call product of DAO
                RegistrationDTO dto = new RegistrationDTO(username, password, fullName, false);
                boolean result = dao.createAccount(dto);
                //4. Process Result
                if(result) {
                    url = siteMaps.getProperty(MyApplicationConstants.CreateAccountFeature.LOGIN_PAGE);
                }
            } //end no error
        } catch(NamingException ex) {
            log("CreateAccountServlet _ Naming: " + ex.getMessage());
        } catch(SQLException ex) {
            String errMsg = ex.getMessage();
            log("CreateAccountServlet _ SQL: " + errMsg);
            if(errMsg.contains("duplicate")) {
                errors.setUsernameisExisted(username + MyApplicationConstants.RegistrationErrorFeature.USER_EXISTED_ERROR);
                request.setAttribute("CREATE_ERRORS", errors);
            } //username is duplicated
        }
        finally {
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
