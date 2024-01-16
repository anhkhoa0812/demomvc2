/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoatha.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khoatha.cart.CartObject;
import khoatha.product.ProductDTO;

/**
 *
 * @author tahoa
 */
@WebServlet(name = "RemoveItemsFromCartServlet", urlPatterns = {"/RemoveItemsFromCartServlet"})
public class RemoveItemsFromCartServlet extends HttpServlet {

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
        try {
            //1.Customer goes to the cart place
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. Customer takes his/her cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. Customer get items (ngăn chứa đồ)
                    Map<ProductDTO, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. Customer choose all removing items
                        String[] selectedItems = request.getParameterValues("chkItem");
                        if (selectedItems != null) {
                            //5. remove all items
                            for (String item : selectedItems) {
                                cart.removeItemFromCart(item);
                            }
                            session.setAttribute("CART", cart);

                        }// cust must be choosen
                    } //end items have existed
                } //end cart has existed
            } //end cart place must exist
        } catch (NamingException ex) {
            log("RemoveItemsFromCartServlet _ Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("RemoveItemsFromCartServlet  _ SQL: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("RemoveItemsFromCartServlet  _ ClassNotFound: " + ex.getMessage());
        }
        finally {
            //6. refresh --> Gọi là chức năng trước đó (View Your Cart)
            // --> using url writing technique
            String urlRewriting = siteMaps.getProperty("viewCartPage")
                    + "?btAction=View Your Cart";
            response.sendRedirect(urlRewriting); //Dùng forward thì sẽ bị trùng tên btAction 
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
