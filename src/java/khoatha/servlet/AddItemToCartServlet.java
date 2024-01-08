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
import javax.servlet.http.HttpSession;
import khoatha.cart.CartObject;
import khoatha.product.ProductDAO;
import khoatha.product.ProductDTO;
import khoatha.utils.MyApplicationConstants;

/**
 *
 * @author tahoa
 */
@WebServlet(name = "AddItemToCartServlet", urlPatterns = {"/AddItemToCartServlet"})
public class AddItemToCartServlet extends HttpServlet {
//    private final String BOOKSTORE_PAGE = "bookStore.html";

//    private final String BOOKSTORE_CONTROLLER = "BookStoreServlet";
//    private final String BOOKSTORE_CONTROLLER = "BookStoreServlet";
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
        String url = siteMaps.getProperty(MyApplicationConstants.AddItemToCartFeature.BOOKSTORE_CONTROLLER);

        try {
            //1. Customer goes to the cart place
            HttpSession session = request.getSession();
            //2. Customer takes his/her cart
            CartObject cart = (CartObject) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObject();
            } //cart has initalized
            //3. Customer drops item to cart
//            String itemId = request.getParameter("bookName");
            String itemId = request.getParameter("bookID");
            ProductDAO dao = new ProductDAO();
            ProductDTO dto = dao.searchProductById(itemId);

            String quantityString = request.getParameter("bookQuantity");

            if (!quantityString.trim().isEmpty()) {
                int quantity = Integer.parseInt(quantityString);
                if (quantity > dto.getQuantity()) {
                    request.setAttribute("ERROR_QUANTITY", "The quantity don't enough");
                } else {
                    boolean check = dao.updateQuantity(itemId, dto.getQuantity() - quantity);
                    if (check) {
                        cart.addItemToCart(itemId, quantity);
                    }

                }
            }

            //giỏ đang trong tay mình vì bỏ đồ trong giỏ nên phải setAttribute
            session.setAttribute("CART", cart);
            //4. Custome goes to shopping by returning bookStore, html

        } catch (NamingException ex) {
            log("AddItemToCartServlet _ Naming: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            log("AddItemToCartServlet _ NumberFormat: " + ex.getMessage());
            request.setAttribute("ERROR_NUMBERFORMAT", MyApplicationConstants.AddItemToCartFeature.ERROR_NUMBERFORMAT);
        } catch (SQLException ex) {
            log("AddItemToCartServlet _ SQL: " + ex.getMessage());
        } finally {
            //Vì nó là attribute, reponse trả về ko mất nên thích dùng forward hay send j cũng đc
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
