/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoatha.orderDetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import khoatha.utils.DBHelper;

/**
 *
 * @author tahoa
 */
public class OrderDetailDAO implements Serializable{
    public boolean crateOrderDetail(OrderDetailDTO orderDetail) throws NamingException, SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //Create connection
            con = DBHelper.createConnection();
            if(con != null) {
                //Create SQL String
                String sql = "INSERT INTO OrderDetail "
                        + "("
                        + "id, productID, quantity, price, total, orderID"
                        + ") Values ("
                        + "?, ?, ?, ?, ?, ?"
                        + ")";
                //Create statement 
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderDetail.getId());
                stm.setString(2, orderDetail.getProductID());
                stm.setInt(3, orderDetail.getQuantity());
                stm.setFloat(4, orderDetail.getPrice());
                stm.setFloat(5, orderDetail.getTotal());
                stm.setString(6, orderDetail.getOrderID());
                
                int effectRow = stm.executeUpdate();
                if(effectRow > 0) {
                    result = true;
                }
            }
        } finally {
            if(stm != null) {
                stm.close();
            }
            if(con != null) {
                con.close();
            }
        }
        return result;   
    }
    
    private List<OrderDetailDTO> orderDetails;

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }
    
    public void showOrderDetailByOrderID(String orderID) throws NamingException, SQLException, ClassNotFoundException {
        Connection con= null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //Create connection
            con = DBHelper.createConnection();
            if(con != null) {
                //Create SQL String
                String sql = "SELECT id, productID, quantity, price, total "
                        + "FROM OrderDetail "
                        + "WHERE orderID = ?";
                //Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                //Execute querry
                rs = stm.executeQuery();
                //Process result
                while (rs.next()) {                    
                    int id = rs.getInt("id");
                    String productID = rs.getString("productID");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");
                    float total = rs.getFloat("total");
                    
                    OrderDetailDTO dto = new OrderDetailDTO(id, productID, quantity, price, total, orderID);
                    
                    if(this.orderDetails == null) {
                        this.orderDetails = new ArrayList<>();
                    }
                    this.orderDetails.add(dto);
                }
            }
            
        } finally {
            if(rs != null) {
                rs.close();
            }
            if(stm != null) {
                stm.close();
            }
            if(con != null) {
                con.close();
            }
        }
    }
}
