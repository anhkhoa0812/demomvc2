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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khoatha.cart.CartObject;
import khoatha.order.OrderDAO;
import khoatha.orderDetail.OrderDetailDAO;
import khoatha.orderDetail.OrderDetailDTO;
import khoatha.product.ProductDTO;
import khoatha.utils.MyApplicationConstants;

/**
 *
 * @author tahoa
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

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
        String url = siteMaps.getProperty(MyApplicationConstants.CheckOutFeature.ERROR_PAGE);

        try {
            //1.Customer goes to the cart place
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. Customer takes his/her cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. Customer get all items (ngăn chứa đồ)
                    Map<ProductDTO, Integer> items = cart.getItems();
                    if (items != null) {
                        float orderTotal = 0;
                        for (ProductDTO item : items.keySet()) {
                            orderTotal += item.getUnitPrice() * items.get(item);
                        }
                        OrderDAO orderDao = new OrderDAO();
                        String orderID = "HD" + String.format("%03d", orderDao.countOrder() + 1);
                        session.setAttribute("OrderID", orderID);
                        boolean result = orderDao.insertOrder(orderID, orderTotal);
                        if (result) {
                            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                            int count = 0;
                            for (ProductDTO item : items.keySet()) {

                                String productID = item.getId();
                                int quantity = items.get(item);
                                float orderDetailPrice = item.getUnitPrice();
                                float orderDetailTotal = quantity * orderDetailPrice;
                                OrderDetailDTO orderDetailDto = new OrderDetailDTO(count, productID, quantity,
                                        orderDetailPrice, orderDetailTotal, orderID);
                                boolean orderDetailResult = orderDetailDAO.crateOrderDetail(orderDetailDto);
                                if (orderDetailResult) {
                                    count++;
                                    url = siteMaps.getProperty(MyApplicationConstants.CheckOutFeature.SHOW_CHECK_OUT_CONTROLLER);
                                    
                                    session.removeAttribute("CART");
                                }

                            }
                        }
                    }
                } //end items have existed
            } //end cart has existed
        } catch (NamingException ex) {
            log("CheckOutServlet _ Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("CheckOutServlet _ SQL: " + ex.getMessage());
        }catch (ClassNotFoundException ex) {
            log("CheckOutServlet _ ClassNotFound: " + ex.getMessage());
        } 
        finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
//            response.sendRedirect(url);
        }
    } //end cart place must exist

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
