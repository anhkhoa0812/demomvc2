/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoatha.order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import khoatha.utils.DBHelper;

/**
 *
 * @author tahoa
 */
public class OrderDAO implements Serializable {

    public int countOrder() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            //Make connection
            con = DBHelper.createConnection();
            if (con != null) {
                //Create SQL String
                String sql = "SELECT id, oDate, total "
                        + "FROM tbl_Order";
                //Create statement
                stm = con.prepareStatement(sql);
                //Execute querry
                rs = stm.executeQuery();
                //Process result
                while (rs.next()) {
                    count++;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return count;

    }

    public boolean insertOrder(String orderID, float total) throws NamingException, SQLException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {

            //Make connection
            con = DBHelper.createConnection();
            if (con != null) {
                //Create SQL String
                String sql = "INSERT INTO tbl_Order "
                        + "("
                        + "id, oDate, total"
                        + ") Values ("
                        + "?, GETDATE(), ?"
                        + ")";
                //Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                stm.setFloat(2, total);

                //Execute querry
                int effectRow = stm.executeUpdate();
                //Process result
                if (effectRow > 0) {
                    result = true;
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }
    public OrderDTO showOrderByID(String id) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        OrderDTO order = null;
        try {
            //Create connection
            con = DBHelper.createConnection();
            if(con !=null) {
                //Create SQL String
                String sql = "SELECT oDate, total "
                        + "FROM tbl_Order "
                        + "WHERE id = ?";
                //Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                
                //Execute querry
                rs = stm.executeQuery();
                //Process result
                if(rs.next()) {
                    Date date = rs.getDate("oDate");
                    float total = rs.getFloat("total");
                    order = new OrderDTO(id, date, total);
                    
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
        return order;
    }
}
