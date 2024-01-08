/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoatha.product;

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
public class ProductDAO implements Serializable {

    private List<ProductDTO> productList;

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public void showProductList() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Create connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "SELECT id, name, quantity, unitPrice, status "
                        + "FROM Product";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                //4. Execute querry
                rs = stm.executeQuery();
                //5.Process result
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    float unitPrice = rs.getFloat("unitPrice");
                    boolean status = rs.getBoolean("status");
                    if (this.productList == null) {
                        this.productList = new ArrayList<>();
                    }
                    ProductDTO dto = new ProductDTO(id, name, quantity, unitPrice, status);

                    this.productList.add(dto);
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
    }

    public ProductDTO searchProductById(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ProductDTO result = null;
        try {
            //Make connection
            con = DBHelper.createConnection();
            if (con != null) {
                //Create SQL String
                String sql = "SELECT name, quantity, unitPrice, status "
                        + "FROM Product "
                        + "WHERE id = ? ";
                //Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                //Execute querry
                rs = stm.executeQuery();
                //Process result
                if (rs.next()) {
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    float unitPrice = rs.getFloat("unitPrice");
                    boolean status = rs.getBoolean("status");

                    result = new ProductDTO(id, name, quantity, unitPrice, status);
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
        return result;
    }

    public boolean updateQuantity(String id, int quantity) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //Make connection
            con = DBHelper.createConnection();
            if (con != null) {
                String sql = "UPDATE Product "
                        + "SET quantity = ? "
                        + "WHERE id = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setString(2, id);
                int effectRow = stm.executeUpdate();
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
}
