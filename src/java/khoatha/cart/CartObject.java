/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoatha.cart;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import khoatha.product.ProductDAO;
import khoatha.product.ProductDTO;

/**
 *
 * @author tahoa
 */
//Đây là cái giỏ, ko chứa đc đồ, mún chứa đc đồ cầ giỏ đồ
public class CartObject implements Serializable {

    private Map<ProductDTO, Integer> items;

    public CartObject() {
    }

    public Map<ProductDTO, Integer> getItems() {
        return items;
    }

    public void addItemToCart(String itemID, int quantity) throws SQLException, NamingException {
        //1. Check item existed
        if (itemID == null) {
            return;
        }
        if (itemID.trim().isEmpty()) {
            return;
        }
        if (quantity < 0) {
            return;
        }
        //2. check items existed
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        ProductDAO dao = new ProductDAO();
        ProductDTO dto = dao.searchProductById(itemID);
//        boolean checkDTO = false;
        //3. check item existed
        for (ProductDTO key : this.items.keySet()) {
            if (key.getId().equals(dto.getId())) {
                int newQuantity = this.items.get(key) + quantity;
                this.items.put(key, newQuantity);
                return; // Exit the loop, as the update is done
            }
        }
//            if (checkDTO) {
//                quantity = quantity + this.items.get(dto)   ;
//            } //end item has already existed 
//            else {
//                System.out.println("NOT CONTAINS");
//                System.out.println(items.keySet());
//                System.out.println(dto);
//    }
        //4. Put item to items
        this.items.put(dto, quantity);
    }

    public void removeItemFromCart(String itemID) throws SQLException, NamingException {
        //1. Check item existed
        if (itemID == null) {
            return;
        }
        if (itemID.trim().isEmpty()) {
            return;
        }
        //2. check items existed
        if (this.items == null) {
            return;
        }
        ProductDAO dao = new ProductDAO();
        ProductDTO dto = dao.searchProductById(itemID);
        //3. Check item existed

//        if (this.items.containsKey(dto)) {
//            this.items.remove(dto);
//            if (this.items.isEmpty()) {
//                this.items = null;
//            }
//        }
        for (ProductDTO key : this.items.keySet()) {
            if (key.getId().equals(dto.getId())) {
                this.items.remove(key);
                if (this.items.isEmpty()) {
                    this.items = null;
                }
                return; 
            }
        }
    }
    
//    public boolean checkOut(String orderID, float total) throws NamingException, SQLException {
//        if(this.items == null) {
//            return false;
//        }
//        
//        OrderDAO dao = new OrderDAO();
//
//         boolean result = dao.insertOrder(orderID, total); 
//        
//        return result;
//    }
//    private String getOrderID() throws SQLException, NamingException {
//
//        OrderDAO dao = new OrderDAO();
//        String orderID = "HD" + String.format("%03d", dao.countOrder() + 1);
//        
//        return orderID;
//    }
}
